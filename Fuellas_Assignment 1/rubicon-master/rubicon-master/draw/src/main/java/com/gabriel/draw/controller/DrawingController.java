package com.gabriel.draw.controller;

import com.gabriel.draw.model.Rectangle;
import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class DrawingController implements MouseListener, MouseMotionListener, KeyListener {
    private Point end;
    final private DrawingView drawingView;

    Shape currentShape;
    AppService appService;

    public DrawingController(AppService appService, DrawingView drawingView) {
        this.appService = appService;
        this.drawingView = drawingView;
        drawingView.addMouseListener(this);
        drawingView.addMouseMotionListener(this);
        appService.setDrawMode(DrawMode.Idle);
        appService.setShapeMode(ShapeMode.Line);
        drawingView.addKeyListener(this);
        drawingView.setFocusable(true);
        drawingView.requestFocusInWindow();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point start;
        if (appService.getDrawMode() == DrawMode.Idle) {
            start = e.getPoint();
            switch (appService.getShapeMode()) {
                case Line:
                    currentShape = new Line(start, start);
                    break;
                case Rectangle:
                    currentShape = new Rectangle(start, start);
                    break;
                case Ellipse:
                    currentShape = new Ellipse(start, start);
                    break;
            }
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, false);
            appService.setDrawMode(DrawMode.MousePressed);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.MousePressed) {
            end = e.getPoint();
            appService.create(currentShape);
            appService.setDrawMode(DrawMode.Idle);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(appService.getDrawMode() == DrawMode.MousePressed) {
            end = e.getPoint();
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
            appService.scale(currentShape, end);
            currentShape.getRendererService().render(drawingView.getGraphics(), currentShape, true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'l':
                appService.setShapeMode(ShapeMode.Line);
                System.out.println("ShapeMode changed to Line");
                break;
            case 'r':
                appService.setShapeMode(ShapeMode.Rectangle);
                System.out.println("ShapeMode changed to Rectangle");
                break;
            case 'e':
                appService.setShapeMode(ShapeMode.Ellipse);
                System.out.println("ShapeMode changed to Ellipse");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}
