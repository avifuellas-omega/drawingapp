package com.gabriel.draw.service;

import com.gabriel.drawfx.service.RendererService;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.draw.model.Ellipse;

import java.awt.*;

public class EllipseRendererService implements RendererService {

    @Override
    public void render(Graphics g, Shape shape, boolean xor) {
        Ellipse ellipse = (Ellipse) shape;
        Point start = ellipse.getLocation();
        Point end = ellipse.getEnd();

        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);

        g.setXORMode(ellipse.getColor());
        g.drawOval(x, y, width, height);
    }
}
