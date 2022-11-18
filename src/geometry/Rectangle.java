//Liav Burger 208277871
package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * Classname: Rectangle.
 * Description:
 */
public class Rectangle {

    private final int x;
    private final int y;
    private final double width;
    private final double height;

    /**
     * Constructor.
     * @param upperLeft upper left corner of the rectangle
     * @param width width
     * @param height height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.x = (int) upperLeft.getX();
        this.y = (int) upperLeft.getY();
        this.width = width;
        this.height = height;
    }
    /**
     * Constructor.
     * @param x upper left x value
     * @param y upper left y value
     * @param width width
     * @param height height
     */
    public Rectangle(int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line line
     * @return list
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line left = new Line(getUpperLeft(), getBottomLeft());
        Line right = new Line(getUpperRight(), getBottomRight());
        Line top = new Line(getUpperLeft(), getUpperRight());
        Line bottom = new Line(getBottomLeft(), getBottomRight());

        //return the corners if a line is one of the walls
        if (line.equals(left) || line.equals(right) || line.equals(top) || line.equals(bottom)) {
            list.add(line.start());
            list.add(line.end());
            return list;
        }

        //if a line is diagonal from corner to corner
        if ((line.start().equals(getUpperLeft()) && line.end().equals(getBottomRight()))
                || (line.end().equals(getUpperLeft()) && line.start().equals(getBottomRight()))) {
            list.add(line.start());
            list.add(line.end());
            return list;
        }
        if ((line.start().equals(getUpperRight()) && line.end().equals(getBottomLeft()))
                || (line.end().equals(getUpperRight()) && line.start().equals(getBottomLeft()))) {
            list.add(line.start());
            list.add(line.end());
            return list;
        }

        //if a line starts on one corner and finishes before the other corner, return one corner
        if (line.start().equals(left.start()) || line.start().equals(left.end())
                || line.start().equals(right.start()) || line.start().equals(right.end())
                || line.start().equals(top.start()) || line.start().equals(top.end())
                || line.start().equals(bottom.start()) || line.start().equals(bottom.end())) {
            list.add(line.start());
            return list;
        }

        //if a line starts on one corner and finishes before the other corner, return one corner
        if (line.end().equals(left.start()) || line.end().equals(left.end())
                || line.end().equals(right.start()) || line.end().equals(right.end())
                || line.end().equals(top.start()) || line.end().equals(top.end())
                || line.end().equals(bottom.start()) || line.end().equals(bottom.end())) {
            list.add(line.end());
            return list;
        }

        if (line.intersectionWith(left) == null && line.intersectionWith(right) == null
                && line.intersectionWith(top) == null && line.intersectionWith(bottom) == null) {
            return list;
        }

        if (line.isIntersecting(left)) {
            list.add(line.intersectionWith(left));
        }
        if (line.isIntersecting(right)) {
            list.add(line.intersectionWith(right));
        }
        if (line.isIntersecting(top)) {
            list.add(line.intersectionWith(top));
        }
        if (line.isIntersecting(bottom)) {
            list.add(line.intersectionWith(bottom));
        }

        //remove null instances
        removeNullsFromList(list);
        return list;
    }

    /**
     * This function removes nulls from list if any exist.
     * @param list list
     */
    private void removeNullsFromList(List<Point> list) {
        for (int i = 0; i < list.size(); i++) {
            list.remove(null);
        }
    }

    /**
     * Get width of the rectangle.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Get height of the rectangle.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Returns the upper-left point of the rectangle.
     * @return upper-left point
     */
    public Point getUpperLeft() {
        return new Point(this.x, this.y);
    }
    /**
     * Returns the upper-right point of the rectangle.
     * @return upper-right point
     */
    public Point getUpperRight() {
        return new Point(this.x + this.width, this.y);
    }
    /**
     * Returns the bottom-left point of the rectangle.
     * @return bottom-left point
     */
    public Point getBottomLeft() {
        return new Point(this.x, this.y + this.height);
    }
    /**
     * Returns the bottom-right point of the rectangle.
     * @return bottom-right point
     */
    public Point getBottomRight() {
        return new Point(this.x + this.width, this.y + this.height);
    }
}
