package com.vulps.main.ui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

public class Menu {

    private final int WIDTH, HEIGHT;
    private final Component parent;

    private final ArrayList<MenuItem> menuItems;

    private int score;
    public Boolean visible = true;

    public Menu(int WIDTH, int HEIGHT, Component parent) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.parent = parent;

        this.menuItems = new ArrayList<>();
        this.setup();
    }

    public void setup() {
        // Set the bounds of the "PLAY" text
        Font font = new Font("Arial", Font.BOLD, 48);
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth("PLAY");
        int textHeight = fontMetrics.getHeight();
        int x = (WIDTH - textWidth) / 2;
        int y = (HEIGHT - textHeight) / 2;
        MenuItem playItem = new MenuItem("play", "PLAY", font, x, y, textWidth, textHeight);
        playItem.setHoverColor(Color.RED);


        // Set up the copyright text
        font = new Font("Arial", Font.ITALIC, 12);
        MenuItem copyrightItem = new MenuItem("copyright", "© 2023 Vulps23. All Rights Reserved", font, 10, HEIGHT - 10);

        //set up the Last Score text
        font = new Font("Arial", Font.ITALIC, 25);
        fontMetrics = new Canvas().getFontMetrics(font);
        textWidth = fontMetrics.stringWidth("Last Score: " + score);
        x = (WIDTH - textWidth) / 2;
        MenuItem scoreItem = new MenuItem("score", "Last Score: " + score, font, x, y - 150);

        //set up the level text which shows the player's level in the last playthrough since launching the game
        textWidth = fontMetrics.stringWidth("Level: " + score / 1000);
        x = (WIDTH - textWidth) / 2;
        MenuItem levelItem = new MenuItem("level", "Level: " + score / 1000, font, x, y - 100);

        menuItems.add(playItem);
        menuItems.add(copyrightItem);
        menuItems.add(scoreItem);
        menuItems.add(levelItem);

    }

    public void tick() {
        // Check for mouse hover and update the menu state if needed
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

        SwingUtilities.convertPointFromScreen(mouseLocation, parent);

        int mouseX = (int) mouseLocation.getX();
        int mouseY = (int) mouseLocation.getY();

        this.updateMouseHover(mouseX, mouseY);

    }


    public void render(Graphics graphics) {
        if (visible) {
            // Render each menu item using its own render method.
            if (!menuItems.isEmpty()) {
                for (MenuItem item : menuItems) {
                    item.render(graphics);
                }
            }
        }
    }

    public void setVisible(boolean visible, int score) {
        this.score = score;

        MenuItem levelItem = this.getMenuItemByName("level");
        levelItem.setText("Level: " + score / 1000);

        MenuItem scoreItem = this.getMenuItemByName("score");
        scoreItem.setText("Score: " + score);

        this.visible = visible;
    }

    /**
     * Determine if the mouse is hovering over a MenuItem with a bounding box and trigger the corresponding event
     * @param mouseX - Mouse X co-ordinate
     * @param mouseY - Mouse Y Co-ordinate
     */
    public void updateMouseHover(int mouseX, int mouseY) {
        for (MenuItem item : menuItems) {
            if (item.hasBounds()) {
                boolean contains = item.getBounds().contains(mouseX, mouseY);
                if (contains) {
                    if (!item.isHovering()) {
                        item.onMouseOver();
                    }
                } else {
                    if (item.isHovering()) {
                        item.onMouseOut();
                    }
                }
            }
        }
    }

    public MenuItem getHoveredItem(MouseEvent e) {
        for (MenuItem item : menuItems) {
            if (item.isHovering()) {
                if (item.getBounds().contains(e.getPoint())) {
                    return item;
                }
            }
        }
        return null;
    }

    public MenuItem getMenuItemByName(String name) {
        return menuItems.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
