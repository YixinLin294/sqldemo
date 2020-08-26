package com.shenlanbao.item10;

import java.awt.*;
import java.util.Objects;

public class ColorPointNew {
    private final Point point;
    private final Color color;

    public ColorPointNew(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint)) {
            return false;
        }

        ColorPointNew colorPoint = (ColorPointNew) o;
        return colorPoint.point.equals(point) && colorPoint.point.equals(color);
    }
}
