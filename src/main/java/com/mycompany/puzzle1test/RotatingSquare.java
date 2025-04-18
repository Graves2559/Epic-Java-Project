/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle1test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class RotatingSquare extends JPanel {
    private double angle = 0;
    private final Runnable onRotate;
    private final Image image;
    private boolean clickable = true;

    public RotatingSquare(Runnable onRotate, Image image) {
        this.onRotate = onRotate;
        this.image = image;
        setPreferredSize(new Dimension(100, 100));
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (clickable) {
                    angle += 45;
                    repaint();
                    onRotate.run();
                }
            }
        });
    }

    public double getAngle() {
        return angle;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public boolean isClickable() {
        return clickable;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            AffineTransform oldTransform = g2d.getTransform();
            g2d.rotate(Math.toRadians(angle), w / 2.0, h / 2.0);
            g2d.drawImage(image, (w - image.getWidth(this)) / 2, (h - image.getHeight(this)) / 2, this);
            g2d.setTransform(oldTransform);
        }
    }
}

