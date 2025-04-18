/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle1test;

import javax.swing.*;
import java.awt.*;

public class Rooms {
    private final BackgroundPanel contentPane;
    private final JLabel[] room5Indicators;
    private final JButton winButton;
    private final JLabel[] imageSquares;
    private final RotatingSquare[] rotatingSquares;
    private final JLabel square;
    private final JLabel fourthSquare;

    public Rooms(BackgroundPanel contentPane, JLabel[] room5Indicators, JButton winButton,
                 JLabel[] imageSquares, RotatingSquare[] rotatingSquares, JLabel square, JLabel fourthSquare) {
        this.contentPane = contentPane;
        this.room5Indicators = room5Indicators;
        this.winButton = winButton;
        this.imageSquares = imageSquares;
        this.rotatingSquares = rotatingSquares;
        this.square = square;
        this.fourthSquare = fourthSquare;
    }

    public void switchRoom(String roomName) {
        ImageIcon icon = new ImageIcon(Puzzle1Test.class.getResource("/" + roomName + ".png"));
        Image newBackground = icon.getImage();
        contentPane.setBackgroundImage(newBackground);

        winButton.setVisible(roomName.equals("javaRoom5"));
        square.setVisible(roomName.equals("javaRoom4"));
        fourthSquare.setVisible(roomName.equals("javaRoom3"));

        for (JLabel indicator : room5Indicators)
            indicator.setVisible(roomName.equals("javaRoom5"));

        for (JLabel imageSquare : imageSquares)
            imageSquare.setVisible(roomName.equals("javaRoom2"));

        for (RotatingSquare rs : rotatingSquares)
            rs.setVisible(roomName.equals("javaRoom3"));
    }
}

