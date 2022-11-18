//Liav Burger 208277871
package arkanoid.game;

import animation.Animation;
import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import arkanoid.levels.LevelInformation;
import arkanoid.listeners.BallRemover;
import arkanoid.listeners.BlockRemover;
import arkanoid.listeners.Counter;
import arkanoid.listeners.HitListener;
import arkanoid.listeners.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import java.awt.Color;

/**
 * This class will hold the sprites and the collidables, and will be in charge of the animation.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int WALL_SIZE = 20;
    private final Counter remainingBlocks;
    private final Counter numberOfBalls;
    private final Counter score;
    private final Counter lives;
    private final AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation level;
    private final HitListener deathRegion;
    private final HitListener blockRemover;
    private final HitListener scoreListener;
    private Paddle paddle;
    /**
     * Constructor.
     * @param level level information
     * @param keyboard keyboard
     * @param runner animation runner
     * @param score current score
     * @param lives lives left
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, int score, int lives) {
        this.level = level;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.numberOfBalls = new Counter();
        this.score = new Counter(score);
        this.runner = runner;
        this.running = false;
        this.keyboard = keyboard;
        this.deathRegion = new BallRemover(this, this.numberOfBalls);
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.scoreListener = new ScoreTrackingListener(this.score);
        this.lives = new Counter(lives);
    }

    /**
     * This function adds a collidable object to the list of all collidable objects in the game environment.
     * @param c The collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This function removes a collidable object from the list of all collidable objects in the game environment.
     * @param c The collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * This function adds a sprite object to the list of all sprite objects.
     * @param s The collidable object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * This function removes a sprite object from the list of all sprite objects.
     * @param s The collidable object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Ball and Paddle and add them to the game.
     */
    public void initialize() {
        initializeBackground();
        initializeWalls();
        initializeBlocks();
        initializeBalls();
        initializePaddle();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        livesIndicator.addToGame(this);
        NameIndicator nameIndicator = new NameIndicator(level.levelName());
        nameIndicator.addToGame(this);
    }

    private void initializeBackground() {
        if (level.getBackground() != null) {
            level.getBackground().addToGame(this);
        }
    }

    private void initializeBalls() {
        Ball ball;
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            ball = new Ball(397, 500, 6, Color.red);
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.numberOfBalls.increase(1);
        }
    }

    private void initializeBlocks() {
        for (Block block : this.level.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            this.remainingBlocks.increase(1);
        }
    }

    private void initializePaddle() {
        if (this.paddle != null) {
            this.paddle.removeFromGame(this);
        }
        this.paddle = new Paddle(this.keyboard);
        this.paddle.setPaddleWidth(this.level.paddleWidth());
        this.paddle.setPaddleSpeed(this.level.paddleSpeed());
        this.paddle.setColor(Color.gray);
        this.paddle.addToGame(this);
    }

    private void initializeWalls() {
        Point upperLeft = new Point(0, 20);
        Block leftWall = new Block(upperLeft, WALL_SIZE, FRAME_HEIGHT);
        Block rightWall = new Block(new Point(FRAME_WIDTH - WALL_SIZE, upperLeft.getY()), WALL_SIZE, FRAME_HEIGHT);
        Block topWall = new Block(upperLeft, FRAME_WIDTH, 20);
        Block bottomWall = new Block(new Point(upperLeft.getX(), FRAME_HEIGHT), FRAME_WIDTH, WALL_SIZE);
        leftWall.setColor(Color.gray);
        leftWall.addToGame(this);
        rightWall.setColor(Color.gray);
        rightWall.addToGame(this);
        topWall.setColor(Color.gray);
        topWall.addToGame(this);
        bottomWall.setColor(Color.gray);
        bottomWall.addToGame(this);
        bottomWall.addHitListener(deathRegion);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //blocks removed so far:
        int blocksRemoved = this.level.blocks().size() - this.remainingBlocks.getValue();
        if (this.level.numberOfBlocksToRemove() <= blocksRemoved) {
            this.score.increase(100);
            this.running = false;
            return;
        }
        if (this.numberOfBalls.getValue() == 0) {
            this.lives.decrease(1);
            if (this.lives.getValue() == 0) {
                this.running = false;
                return;
            }
            initializeBalls();
            initializePaddle();
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }
        if (this.level.numberOfBlocksToRemove()
                < (this.level.blocks().size() - this.remainingBlocks.getValue())) {
            this.score.increase(100);
            this.running = false;
            return;
        }
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", pauseScreen));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }
    }

    /**
     * This function is used to begin playing the level.
     */
    public void play() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * returns int value of lives remaining.
     * @return number of lives remaining.
     */
    public int getLivesRemaining() {
        return this.lives.getValue();
    }

    /**
     * returns score counter.
     * @return score counter
     */
    public Counter getScore() {
        return this.score;
    }
}