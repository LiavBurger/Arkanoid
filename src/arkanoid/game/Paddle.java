//Liav Burger 208277871
package arkanoid.game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;

/**
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Block paddleBlock;
    private static final int WALL_SIZE = 20;
    private static final double FRAME_WIDTH = 800;
    private static final double FRAME_HEIGHT = 600;
    private static final double PADDLE_HEIGHT = 15;
    private static final double FLOATING_HEIGHT = 0;
    private double paddleWidth = 100;
    private double speed = 10;
    private Point upperLeft;

    /**
     * Constructor.
     * @param keyboard keyboard object
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.upperLeft = new Point(FRAME_WIDTH / 2 - paddleWidth / 2, FRAME_HEIGHT - PADDLE_HEIGHT - FLOATING_HEIGHT);
        this.paddleBlock = new Block(this.upperLeft, paddleWidth, PADDLE_HEIGHT);
        this.paddleBlock.setColor(Color.black);
    }
    /**
     * Set color.
     * @param color color
     */
    public void setColor(Color color) {
        this.paddleBlock.setColor(color);
    }

    /**
     * Set speed.
     * @param speed speed
     */
    public void setPaddleSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Set paddle width.
     * @param paddleWidth paddle width
     */
    public void setPaddleWidth(double paddleWidth) {
        this.paddleWidth = paddleWidth;
        //fix paddle middle according to the new width.
        this.upperLeft = new Point(FRAME_WIDTH / 2 - paddleWidth / 2, FRAME_HEIGHT - PADDLE_HEIGHT - FLOATING_HEIGHT);
        //fix paddle block according to new width.
        this.paddleBlock = new Block(this.upperLeft, paddleWidth, PADDLE_HEIGHT);
    }

    /**
     * Move the paddle to the left.
     */
    public void moveLeft() {
        //if the paddle can't move any more left
        if (this.upperLeft.getX() == WALL_SIZE) {
            return;
        }
        //if the next step will be more than the screen
        if (this.upperLeft.getX() - speed < WALL_SIZE) {
            this.upperLeft = new Point(0, this.upperLeft.getY());
            updatePaddle(this.upperLeft);
            return;
        }
        this.upperLeft = new Point(this.upperLeft.getX() - speed, this.upperLeft.getY());
        updatePaddle(this.upperLeft);
    }

    /**
     * Move the paddle to the right.
     */
    public void moveRight() {
        //if the paddle can't move any more right
        if (this.upperLeft.getX() + paddleWidth == FRAME_WIDTH) {
            return;
        }
        //if the next step will be more than the screen
        if (this.upperLeft.getX() + paddleWidth + speed > FRAME_WIDTH - WALL_SIZE) {
            this.upperLeft = new Point(FRAME_WIDTH - paddleWidth - WALL_SIZE, this.upperLeft.getY());
            updatePaddle(this.upperLeft);
            return;
        }
        this.upperLeft = new Point(this.upperLeft.getX() + speed, this.upperLeft.getY());
        updatePaddle(this.upperLeft);
    }

    /**
     * Update the paddle with the new location.
     * @param upperLeft upper left point of the paddle
     */
    public void updatePaddle(Point upperLeft) {
        java.awt.Color temp = this.paddleBlock.getColor();
        this.paddleBlock = new Block(upperLeft, paddleWidth, PADDLE_HEIGHT);
        this.paddleBlock.setColor(temp);
    }

    /**
     * Draw the paddle on the surface.
     * @param d draw surface
     */
    public void drawOn(DrawSurface d) {
        paddleBlock.drawOn(d);
    }

    // Sprite
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return paddleBlock.getCollisionRectangle();
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //If the collision point is not on the paddle, velocity stays the same.
        if (!isPointOnThePaddle(collisionPoint)) {
            return currentVelocity;
        }

        //If the collision point is a corner, both velocity vectors change
        if (intersectWithCorner(collisionPoint, this.paddleBlock.getCollisionRectangle())) {
            return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }

        //If the intersection is with the side of the paddle, only change x value
        if (collisionPoint.getX() == this.upperLeft.getX()
                || collisionPoint.getX() == this.upperLeft.getX() + paddleWidth) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }

        double speed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
            ArrayList<Double> angles = new ArrayList<>();
        for (int i = 1; i <= paddleWidth; i++) {
            angles.add(300 + ((120 / paddleWidth) * i));
        }
        double region = collisionPoint.distance(this.upperLeft);
        return Velocity.fromAngleAndSpeed(angles.get((int) region), speed);
    }

    /**
     * This function checks if a point is on the paddle.
     * @param point collision point
     * @return true/false
     */
    public boolean isPointOnThePaddle(Point point) {
        Line left = new Line(this.paddleBlock.getCollisionRectangle().getUpperLeft(),
                             this.paddleBlock.getCollisionRectangle().getBottomLeft());
        Line right = new Line(this.paddleBlock.getCollisionRectangle().getUpperRight(),
                              this.paddleBlock.getCollisionRectangle().getBottomRight());
        Line top = new Line(this.paddleBlock.getCollisionRectangle().getUpperLeft(),
                            this.paddleBlock.getCollisionRectangle().getUpperRight());
        Line bottom = new Line(this.paddleBlock.getCollisionRectangle().getBottomLeft(),
                               this.paddleBlock.getCollisionRectangle().getBottomRight());

        //if the point is not on the paddle at all
        return !(point.getY() > bottom.start().getY()) && !(point.getY() < top.start().getY())
                && !(point.getX() < left.start().getX()) && !(point.getX() > right.start().getX());
    }

    /**
     * This function checks if the intersection point is on one of the corners.
     * @param point Intersection point
     * @param rectangle Block object
     * @return True/false depending on if one of the corners is the intersection point.
     */
    private boolean intersectWithCorner(Point point, Rectangle rectangle) {
        return point.equals(rectangle.getUpperLeft()) || point.equals(rectangle.getUpperRight())
                || point.equals(rectangle.getBottomLeft()) || point.equals(rectangle.getBottomRight());

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove the paddle from the game.
     * @param g game level
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}