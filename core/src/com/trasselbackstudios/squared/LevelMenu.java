package com.trasselbackstudios.squared;

import com.badlogic.gdx.graphics.Color;

public class LevelMenu extends Menu {
    private int unlocked = 1;

    public LevelMenu(Squared game) {
        super(game);
        // Add one for current level.
        unlocked = Stats.load() + 1;
    }

    @Override
    protected void setup() {
        menuMap = new int[Level.max + 2][5];
        int index = 1;
        for (int i = menuMap.length - 1; i > 0; i--) {
            if (i < menuMap.length - 1 && index <= Level.max) {
                // draw levels in middle.
                menuMap[i][2] = index;
                index++;
            }
        }
        background = new Color(0.4f, 0.4f, 0.4f, 1);
    }

    protected void drawMenu() {
        game.staticBatch.begin();
        game.font.setScale(0.5f);
        float padding = 50;
        for (int i = 0; i < menuMap.length; i++) {
            for (int j = 0; j < menuMap[0].length; j++) {
                game.font.setColor(0.1f, 0.1f, 0.1f, 1);
                if (menuMap[i][j] != 0) {
                    String sym = "+";
                    if (menuMap[i][j] > unlocked) {
                        game.font.setColor(0.5f, 0.1f, 0.1f, 1);
                        sym = "-";
                    }
                    game.font.draw(game.staticBatch, sym + menuMap[i][j], column * j + padding, row * i + padding);
                }
            }
        }
        game.staticBatch.end();
    }

    @Override
    protected void back() {
        game.setScreen(new MainMenu(game));
    }

    @Override
    protected void processSelection(int selection) {
        if (selection > 0) {
            if (selection <= unlocked) {
                Level.setLevel(selection);
                Level.reset();
                game.setScreen(new Engine(game));
                dispose();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
