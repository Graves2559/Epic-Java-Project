/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.escaperoom;

/**
 *
 * @author Jackson Bullard
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

class RotatingSquare extends JPanel {
    private double angle = 0;
    private final Runnable onRotate;

    public RotatingSquare(Runnable onRotate) {
        this.onRotate = onRotate;
        setPreferredSize(new Dimension(100, 100));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                angle += 25;
                repaint();
                onRotate.run();
            }
        });
    }

    public double getAngle() {
        return angle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        AffineTransform oldTransform = g2d.getTransform();
        g2d.setColor(Color.ORANGE);
        g2d.rotate(Math.toRadians(angle), w / 2.0, h / 2.0);
        g2d.fillRect(10, 10, w - 20, h - 20);
        g2d.setTransform(oldTransform);
    }
}

public class EscapeRoom {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLayout(null);
        frame.setResizable(false);
        
        // Label for Room 1 objective
        JLabel objectiveLabel = new JLabel("Your objective is to escape (WIP).", SwingConstants.CENTER);
        objectiveLabel.setBounds(400, 200, 400, 50);
        objectiveLabel.setFont(new Font("Arial", Font.BOLD, 20));
        objectiveLabel.setVisible(true);
        frame.add(objectiveLabel);
        
        

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, new Color(128, 0, 128)};
        JPanel[] room5Indicators = new JPanel[3];
        for (int i = 0; i < 3; i++) {
            room5Indicators[i] = new JPanel();
            room5Indicators[i].setBackground(Color.BLUE);
            room5Indicators[i].setBounds(500 + (i * 120), 350, 100, 100);
            room5Indicators[i].setVisible(false);
            frame.add(room5Indicators[i]);
        }

        final boolean[] puzzle1Solved = {false};
        final boolean[] puzzle2Solved = {false};
        final boolean[] puzzle3Solved = {false};

        JPanel square = new JPanel();
        square.setBackground(Color.BLUE);
        square.setBounds(550, 350, 100, 100);
        square.setVisible(false);
        frame.add(square);

        square.addMouseListener(new MouseAdapter() {
            private int clickCount = 0;
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!puzzle3Solved[0]) {
                    if (clickCount == 0) {
                        square.setBackground(Color.RED);
                        clickCount++;
                    } else if (clickCount == 1) {
                        square.setBackground(Color.YELLOW);
                        puzzle3Solved[0] = true;
                        room5Indicators[0].setBackground(Color.YELLOW);
                    }
                }
            }
        });

        JPanel[] blueSquares = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            blueSquares[i] = new JPanel();
            blueSquares[i].setBackground(Color.BLUE);
            blueSquares[i].setBounds(400 + (i * 110), 350, 100, 100);
            blueSquares[i].setVisible(false);
            frame.add(blueSquares[i]);
        }

        for (int i = 0; i < 4; i++) {
            final int index = i;
            blueSquares[i].addMouseListener(new MouseAdapter() {
                private int colorIndex = 1;
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (blueSquares[index].isVisible() && !puzzle1Solved[0]) {
                        colorIndex = (colorIndex + 1) % colors.length;
                        blueSquares[index].setBackground(colors[colorIndex]);
                        checkFifthSquare(blueSquares, puzzle1Solved, room5Indicators);
                    }
                }
            });
        }

        RotatingSquare[] rotatingSquares = new RotatingSquare[3];
        JPanel fourthSquare = new JPanel();
        fourthSquare.setBackground(Color.BLUE);
        fourthSquare.setBounds(700, 500, 100, 100);
        fourthSquare.setVisible(false);
        frame.add(fourthSquare);

        Runnable checkRotation = () -> {
            if (rotatingSquares[0].getAngle() == 75 &&
                rotatingSquares[1].getAngle() == 75 &&
                rotatingSquares[2].getAngle() == 75) {
                fourthSquare.setBackground(Color.YELLOW);
                puzzle2Solved[0] = true;
                room5Indicators[2].setBackground(Color.YELLOW);
            } else {
                fourthSquare.setBackground(Color.BLUE);
                puzzle2Solved[0] = false;
            }
        };

        for (int i = 0; i < 3; i++) {
            rotatingSquares[i] = new RotatingSquare(checkRotation);
            rotatingSquares[i].setBounds(400 + (i * 150), 350, 100, 100);
            rotatingSquares[i].setVisible(false);
            frame.add(rotatingSquares[i]);
        }

        String[] roomNames = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5"};
        int buttonWidth = frame.getWidth() / 5;
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton(roomNames[i]);
            button.setBounds(i * buttonWidth, 803, buttonWidth, 60);
            final int roomIndex = i;
            button.addActionListener(e -> {
                boolean isRoom5 = roomNames[roomIndex].equals("Room 5");
                for (JPanel indicator : room5Indicators) {
                    indicator.setVisible(isRoom5);
                }
                boolean isRoom4 = roomNames[roomIndex].equals("Room 4");
                square.setVisible(isRoom4);
                boolean isRoom3 = roomNames[roomIndex].equals("Room 3");
                for (RotatingSquare rotatingSquare : rotatingSquares) {
                    rotatingSquare.setVisible(isRoom3);
                }
                fourthSquare.setVisible(isRoom3);
                boolean isRoom2 = roomNames[roomIndex].equals("Room 2");
                for (JPanel blueSquare : blueSquares) {
                    blueSquare.setVisible(isRoom2);
                }
                if (roomNames[roomIndex].equals("Room 1")) {
                    objectiveLabel.setVisible(true);
                } else {
                    objectiveLabel.setVisible(false);
                }
            });
            frame.add(button);
        }
        frame.setVisible(true);
        
    }
    
    

    private static void checkFifthSquare(JPanel[] blueSquares, boolean[] puzzle2Solved, JPanel[] room5Indicators) {
        Color purple = new Color(128, 0, 128);
        if (blueSquares[0].getBackground().equals(Color.RED) &&
            blueSquares[1].getBackground().equals(Color.BLUE) &&
            blueSquares[2].getBackground().equals(Color.GREEN) &&
            blueSquares[3].getBackground().equals(purple)) {
            blueSquares[4].setBackground(Color.YELLOW);
            blueSquares[4].setVisible(true);
            puzzle2Solved[0] = true;
            room5Indicators[1].setBackground(Color.YELLOW);
        } else {
            blueSquares[4].setBackground(Color.BLUE);
        }
    }
}