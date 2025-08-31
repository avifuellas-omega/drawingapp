package com.gabriel.draw.service;

import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.draw.model.Rectangle;

import java.awt.*;

public class RectangleRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Rectangle rect = (Rectangle) shape;
        Point start = rect.getLocation();
        Point end = rect.getEnd();

        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);

        g.setXORMode(rect.getColor());
        g.drawRect(x, y, width, height);
    }
}
