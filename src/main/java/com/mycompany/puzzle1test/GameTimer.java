/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle1test;

import javax.swing.*;
import java.awt.*;

public class GameTimer {
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining;
    private JFrame frame;

    public GameTimer(JFrame frame, JPanel panel, int minutes) {
        this.frame = frame;
        this.timeRemaining = minutes * 60;

        timerLabel = new JLabel(formatTime(timeRemaining), SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 36));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setOpaque(true);
        timerLabel.setBackground(Color.BLACK);
        timerLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        timerLabel.setBounds(20, 20, 140, 50);
        panel.add(timerLabel);
    }

    public void start() {
        timer = new Timer(1000, e -> {
            timeRemaining--;
            timerLabel.setText(formatTime(timeRemaining));

            if (timeRemaining <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(frame, "Time's up!", "Game Over", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        });
        timer.start();
    }

    private String formatTime(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}

