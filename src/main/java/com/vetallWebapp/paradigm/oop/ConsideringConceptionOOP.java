package com.vetallWebapp.paradigm.oop;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

interface Figure {
    void draw(Graphics g);
}

interface Graphics {
    void drawLine(int x0, int y0, int x1, int y1);
}

class Oval implements Figure {
    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException();
    }
}

class Rect implements Figure {
    private int leftBottomX;
    private int leftBottomY;
    private int width;
    private int height;

    public Rect(int leftBottomX, int leftBottomY, int width, int height) {
        this.leftBottomX = leftBottomX;
        this.leftBottomY = leftBottomY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException();
    }
}

class VisualUtils {
    public static void drawAll(List<Figure> figures, Graphics g) {
        Iterator<Figure> iter = figures.iterator();
        while (iter.hasNext()) {
            iter.next().draw(g);
        }
    }
    public static void main(String[] args) {
        drawAll(Arrays.asList(new Rect(0, 0, 0, 0), new Oval()), new Graphics() {
            @Override
            public void drawLine(int x0, int y0, int x1, int y1) {
                System.out.println("something");
            }
        });
    }
}

