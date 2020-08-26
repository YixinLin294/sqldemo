package com.shenlanbao.item10;

import java.awt.*;


public class ColorPoint extends Point {
    private final Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }

        // If o is a normal Point, do a color-blind comparison
        if (!(o instanceof ColorPoint)) {
            return o.equals(this);
        }

        return super.equals(o) && ((ColorPoint) o).color == color;
    }

    public static void main(String[] args) {
        Point point = new Point(1, 2);
        ColorPoint colorPoint = new ColorPoint(1, 2, Color.RED);
        System.out.println(point.equals(colorPoint));
        System.out.println(colorPoint.equals(point));

        System.out.println("----------------------------");
        // 牺牲了传递性
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        Point p3 = new ColorPoint(1, 2, Color.BLUE);

        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3));

        System.out.println("-----------------------");
        System.out.println(p1.getClass());
        System.out.println(p2.getClass());
        System.out.println(p3.getClass());
    }
}
