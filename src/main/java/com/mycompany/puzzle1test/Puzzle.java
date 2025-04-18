/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puzzle1test;

import javax.swing.*;

public abstract class Puzzle {
    protected boolean solved = false;

    public boolean isSolved() {
        return solved;
    }

    public abstract void setup(JPanel contentPane);
}

