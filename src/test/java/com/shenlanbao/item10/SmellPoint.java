package com.shenlanbao.item10;

import java.awt.*;
import java.util.Objects;

public class SmellPoint extends Point{
    private String smell;


    public SmellPoint(int x, int y, String smell) {
        super(x, y);
        this.smell = smell;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }

        if (!(o instanceof SmellPoint)) {
            return o.equals(this);
        }

        return super.equals(((SmellPoint)o)) && smell.equals(((SmellPoint) o).smell);
    }

    public static void main(String[] args) {
        SmellPoint p1 = new SmellPoint(1, 2, "1");
        Point p2 = new Point(1, 2);
        SmellPoint p3 = new SmellPoint(1, 2, "2");
        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));
        System.out.println(p1.equals(p3));

        System.out.println("-------------------------");

        // different subclass cause recursion (stackoverflow)

        SmellPoint p4 = new SmellPoint(1, 2, "1");
        ColorPoint p5 = new ColorPoint(1, 2, Color.RED);
        System.out.println(p4.equals(p5));
    }
}
