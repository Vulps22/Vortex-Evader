package com.vulps.main;

import com.vulps.main.Game.Game;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Window extends Canvas {

    @Serial
    private static final long serialVersionUID = -552287688775572810L;

    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}
