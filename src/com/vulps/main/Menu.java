package com.vulps.main;

import javax.swing.*;
import java.awt.*;

public class Menu {

    private int WIDTH, HEIGHT;
    private Component parent;
    public Rectangle playBounds;
    public Boolean visible = true;
    public boolean isMouseOverPlay = false;

    // Colors for the glowing effect
    private Color glowColor = new Color(255, 0, 0); // Semi-transparent white
    private int glowRadius = 20; // Radius of the glow effect


    public Menu(int WIDTH, int HEIGHT, Component parent) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.parent = parent;

        // Set the bounds of the "PLAY" text
        Font font = new Font("Arial", Font.BOLD, 48);
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth("PLAY");
        int textHeight = fontMetrics.getHeight();
        int x = (WIDTH - textWidth) / 2;
        int y = (HEIGHT - textHeight) / 2;
        playBounds = new Rectangle(x, y - textHeight, textWidth, textHeight);
    }

    public void tick() {
        // Check for mouse hover and update the menu state if needed
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

        SwingUtilities.convertPointFromScreen(mouseLocation, parent);

        int mouseX = (int) mouseLocation.getX();
        int mouseY = (int) mouseLocation.getY();

        if (playBounds.contains(mouseX, mouseY)) {
            isMouseOverPlay = true;
        }else{
            isMouseOverPlay = false;
        }
    }


    public void render(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 48);
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth("PLAY");
        int textHeight = fontMetrics.getHeight();

        int x = (WIDTH - textWidth) / 2;
        int y = (HEIGHT - textHeight) / 2;

        if (visible) {
            // Draw the "PLAY" text
            g.setFont(font);

            if (isMouseOverPlay) {
                // Set the color to red when the mouse hovers over the play button
                g.setColor(Color.RED);
            } else {
                // Set the color to white for normal state
                g.setColor(Color.WHITE);
            }

            // Draw the "PLAY" text
            g.drawString("PLAY", x, y);
        }
    }


}