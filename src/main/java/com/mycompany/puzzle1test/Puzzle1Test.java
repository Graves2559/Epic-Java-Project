// Game.java (was Puzzle1Test.java originally)
package com.mycompany.puzzle1test;

import javax.swing.*;
import java.awt.*;

public class Puzzle1Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Puzzle Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setResizable(false);

        ImageIcon bg = new ImageIcon(Puzzle1Test.class.getResource("/javaRoom1.png"));
        BackgroundPanel contentPane = new BackgroundPanel(bg.getImage());
        frame.setContentPane(contentPane);

        JLabel[] room5Indicators = new JLabel[3];
        for (int i = 0; i < 3; i++) {
            room5Indicators[i] = new JLabel(new ImageIcon(Puzzle1Test.class.getResource("/emptyKey.png")));
            room5Indicators[i].setBounds(500 + (i * 200), 300, 100, 100);
            room5Indicators[i].setVisible(false);
            contentPane.add(room5Indicators[i]);
        }

        JButton winButton = new JButton("Exit");
        winButton.setBounds(650, 500, 200, 60);
        winButton.setVisible(false);
        winButton.setEnabled(false);
        winButton.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "You escaped the tower!", "Congratulations", JOptionPane.INFORMATION_MESSAGE)
        );
        contentPane.add(winButton);

        JLabel square = new JLabel(new ImageIcon(Puzzle1Test.class.getResource("/painting1.png")));
        square.setBounds(950, 500, 100, 100);
        square.setVisible(false);
        contentPane.add(square);

        // Ensure click listener is active
        square.addMouseListener(new java.awt.event.MouseAdapter() {
            private int clickCount = 0;
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (clickCount == 0) {
                    square.setIcon(new ImageIcon(Puzzle1Test.class.getResource("/painting2.png")));
                    clickCount++;
                } else if (clickCount == 1) {
                    square.setIcon(new ImageIcon(Puzzle1Test.class.getResource("/painting3.png")));
                    room5Indicators[0].setIcon(new ImageIcon(Puzzle1Test.class.getResource("/unlockedKey.png")));
                    winButton.setEnabled(checkWin(room5Indicators));
                    clickCount++;
                }
            }
        });

        JLabel fourthSquare = new JLabel(new ImageIcon(Puzzle1Test.class.getResource("/lockedKey.png")));
        fourthSquare.setBounds(750, 525, 100, 100);
        fourthSquare.setVisible(false);
        contentPane.add(fourthSquare);

        JLabel[] imageSquares = new JLabel[5];
        ImageIcon[][] squareImages = new ImageIcon[4][];
        for (int i = 0; i < 4; i++) {
            squareImages[i] = new ImageIcon[] {
                    new ImageIcon(Puzzle1Test.class.getResource("/blueCrystal.png")),
                    new ImageIcon(Puzzle1Test.class.getResource("/redCrystal.png")),
                    new ImageIcon(Puzzle1Test.class.getResource("/greenCrystal.png")),
                    new ImageIcon(Puzzle1Test.class.getResource("/purpleCrystal.png"))
            };

            imageSquares[i] = new JLabel(squareImages[i][0]);
            imageSquares[i].setBounds(195 + (i * 250), 505, 100, 100);
            imageSquares[i].setVisible(false);
            final int index = i;
            int[] imgIndex = {0};
            imageSquares[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    imgIndex[0] = (imgIndex[0] + 1) % squareImages[index].length;
                    imageSquares[index].setIcon(squareImages[index][imgIndex[0]]);
                    if (checkImages(imageSquares, squareImages)) {
                        imageSquares[4].setIcon(new ImageIcon(Puzzle1Test.class.getResource("/emptyKey.png")));
                        room5Indicators[1].setIcon(new ImageIcon(Puzzle1Test.class.getResource("/unlockedKey.png")));
                        winButton.setEnabled(checkWin(room5Indicators));
                    }
                }
            });
            contentPane.add(imageSquares[i]);
        }

        imageSquares[4] = new JLabel(new ImageIcon(Puzzle1Test.class.getResource("/lockedKey.png")));
        imageSquares[4].setBounds(550, 355, 100, 100);
        imageSquares[4].setVisible(false);
        contentPane.add(imageSquares[4]);

        RotatingSquare[] rotatingSquares = new RotatingSquare[3];
        Image rotatingImage = new ImageIcon(Puzzle1Test.class.getResource("/rotatingArrow.png")).getImage();

        for (int i = 0; i < 3; i++) {
            final int idx = i;
            rotatingSquares[i] = new RotatingSquare(() -> {
                int a0 = (int) rotatingSquares[0].getAngle() % 360;
                int a1 = (int) rotatingSquares[1].getAngle() % 360;
                int a2 = (int) rotatingSquares[2].getAngle() % 360;
                if (a0 == 180 && a1 == 315 && a2 == 90) {
                    fourthSquare.setIcon(new ImageIcon(Puzzle1Test.class.getResource("/emptyKey.png")));
                    room5Indicators[2].setIcon(new ImageIcon(Puzzle1Test.class.getResource("/unlockedKey.png")));
                    winButton.setEnabled(checkWin(room5Indicators));
                    for (RotatingSquare rs : rotatingSquares) rs.setClickable(false);
                } else {
                    fourthSquare.setIcon(new ImageIcon(Puzzle1Test.class.getResource("/lockedKey.png")));
                }
            }, rotatingImage);
            rotatingSquares[i].setBounds(600 + (i * 150), 350, 100, 100);
            rotatingSquares[i].setVisible(false);
            contentPane.add(rotatingSquares[i]);
        }

        Rooms roomManager = new Rooms(contentPane, room5Indicators, winButton, imageSquares, rotatingSquares, square, fourthSquare);

        String[] roomNames = {"javaRoom1", "javaRoom2", "javaRoom3", "javaRoom4", "javaRoom5"};
        int buttonWidth = frame.getWidth() / roomNames.length;
        for (int i = 0; i < roomNames.length; i++) {
            JButton button = new JButton("Room " + (i + 1));
            button.setBounds(i * buttonWidth, 803, buttonWidth, 60);
            final String room = roomNames[i];
            button.addActionListener(e -> roomManager.switchRoom(room));
            contentPane.add(button);
        }

        GameTimer gameTimer = new GameTimer(frame, contentPane, 5);
        gameTimer.start();

        frame.setVisible(true);
    }

    private static boolean checkImages(JLabel[] squares, ImageIcon[][] targets) {
        int[] correct = {1, 0, 2, 3};
        for (int i = 0; i < 4; i++) {
            if (!squares[i].getIcon().equals(targets[i][correct[i]])) return false;
        }
        return true;
    }

    private static boolean checkWin(JLabel[] indicators) {
        ImageIcon solved = new ImageIcon(Puzzle1Test.class.getResource("/unlockedKey.png"));
        for (JLabel label : indicators) {
            if (!label.getIcon().toString().equals(solved.toString())) return false;
        }
        return true;
    }
}
