package arkanoid.levels;

import animation.Animation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import arkanoid.game.GameLevel;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow class. This class is responsible for the flow of the game.
 * Will run each level until it's done or end the game if all lives are lost.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private int score;
    private int numberOfLives = 7;

    /**
     * Constructor.
     * @param ar animation runner
     * @param ks keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = 0;
    }

    /**
     * This function will run each level or end the game if the lives = 0.
     * @param levels all the levels in a list of level information.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level =
                    new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score, this.numberOfLives);
            level.initialize();
            level.play();
            this.score = level.getScore().getValue();
            this.numberOfLives = level.getLivesRemaining();
            if (this.numberOfLives == 0) {
                break;
            }
        }
        Animation endScreen = new EndScreen(this.score, this.numberOfLives);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", endScreen));
    }
}