package com.shenlanbao.item10;


import java.awt.*;
import java.util.Objects;
import java.util.Set;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = ((Point) o);
        return p.x == x && p.y == y;
    }

/*    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Point point = (Point) o;
        return point.x == x && point.y == y;
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    private static final Set<Point> unitCircle = Set.of(
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1)
    );

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }

    public static void main(String[] args) {
        ColorPointNew point = new ColorPointNew(1, 0, Color.RED);
        System.out.println(onUnitCircle(point.asPoint()));

        System.out.println("-----------------------");
        System.out.println(null instanceof Object);
    }
}
