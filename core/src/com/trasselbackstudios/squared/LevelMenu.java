package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class LevelMenu extends Menu {
    private Texture levelIcon;

    public LevelMenu(Squared game) {
        super(game);
        levelIcon = new Texture(Gdx.files.internal("bg.png"));
    }

    @Override
    protected void setup() {
        menuMap = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 1, 2, 3, 0},
                {0, 0, 0, 0, 0},
        };

        background = new Color(0.4f, 0.4f, 0.4f, 1);
    }

    protected void drawMenu() {
        float x = game.staticCamera.viewportWidth / 5;
        float y = game.staticCamera.viewportHeight / 3 * 2;
        game.staticBatch.begin();
        game.font.setColor(0.1f, 0.1f, 0.1f, 1);
        game.font.setScale(0.5f);
        for (int i = 0; i < Level.max; i++) {
            game.font.draw(game.staticBatch, "Level " + (i + 1), x, y);
            menuMap[(int) Math.floor(y / (480 / 3))][(int) Math.floor(x / (800 / 5))] = i + 1;
            game.staticBatch.draw(levelIcon, x, y - 130, 100, 100);
            x += 200;
        }
        game.staticBatch.end();
    }

    @Override
    protected void back() {
        game.setScreen(new MainMenu(game));
    }

    @Override
    protected void processSelection(int selection) {
        Level.setLevel(selection);
        if (selection > 0) {
            game.setScreen(new Engine(game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        levelIcon.dispose();
    }
}
