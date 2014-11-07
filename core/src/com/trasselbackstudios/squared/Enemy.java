package com.trasselbackstudios.squared;

import com.badlogic.gdx.graphics.Color;

public class Enemy extends Block {
    public static final Color RED = new Color(0.5f, 0, 0, 1);
    private float velX = 3;

    public Enemy(float x, float y, float width, float height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void update(String[][] tileMap) {
        int blockWidth = 50;
        int rowBottom = (int) Math.floor(y / blockWidth);
        int rowTop = (int) Math.floor((y + this.height - 1) / blockWidth);

        x += velX;
        int col = (int) Math.floor((x + this.width - 1) / blockWidth);
        if (velX < 0)
            col = (int) Math.floor(x / blockWidth);
        if (tileMap[rowBottom][col].equals(Level.PLATFORM) || tileMap[rowTop][col].equals(Level.PLATFORM)) {
            x -= velX;
            velX = -velX;
        }
    }
}
