package com.vulps.main.ui.menu;

import java.awt.*;

public class MenuItem {

    private String name;
    private String text;
    private int x;
    private int y;
    private Font font;
    private Color textColor = Color.white;

    private boolean hovering;
    private Color hoverColor = null;

    private Rectangle bounds;

    /**
     *
     * @param name - A unique internal identifier for handling events
     * @param text - The text to display in the menu item
     * @param font - The font used to format the text
     * @param x - The menu item's x co-ordinate
     * @param y - The menu item's y co-ordinate
     **/
    public MenuItem(String name, String text, Font font, int x, int y) {
        this.name = name;
        this.text = text;
        this.font = font;

        this.x = x;
        this.y = y;

        this.bounds = null;
    }

    /**
     *
     * @param name - A unique internal identifier for handling events
     * @param text - The text to display in the menu item
     * @param font - The font used to format the text
     * @param x - The menu item's x co-ordinate
     * @param y - The menu item's y co-ordinate
     * @param width - The menu item's bounding box width
     * @param height - The menu item's bounding box height
     */
    public MenuItem(String name, String text, Font font, int x, int y, int width, int height) {

        this.name = name;
        this.text = text;
        this.font = font;

        this.x = x;
        this.y = y;

        // we need to subtract the height from y because the point of origin (0, 0) is the top left of the object
        this.bounds = new Rectangle(x, y - height, width, height);
    }

    public void render(Graphics graphics) {
        // Set the font for rendering
        graphics.setFont(font);
        graphics.setColor(this.textColor);
        FontMetrics metrics = graphics.getFontMetrics(font);

        // Optionally, you could recalculate the width/height if needed
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        if(this.hoverColor != null) {
            // Set the text color based on hover state
            if (this.hovering) {
                graphics.setColor(this.hoverColor);
            } else {
                graphics.setColor(Color.WHITE);
            }
        }

        // Draw the text at the defined (x, y) coordinates
        // Note: (x, y) should represent the baseline position where you want the text to appear.
        graphics.drawString(text, x, y);
    }

    public void onMouseOver(){
        this.hovering = true;
    }

    public void onMouseOut(){
        this.hovering = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setIsHovering(boolean isHovering) {
        this.hovering = isHovering;
    }

    public boolean isHovering(){
        return this.hovering;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * Bounding box of the menu Item used to detect mouseOver events
     * @return Rectangle bounding box of the menu item
     */
    public Rectangle getBounds() {
        return bounds;
    }

    public boolean hasBounds() {
        return this.bounds != null;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
