package com.trasselbackstudios.squared;

import com.badlogic.gdx.graphics.Color;

public class HelpMenu extends Menu {

    public HelpMenu(Squared game) {
        super(game);
    }

    @Override
    protected void setup() {
        background = new Color(0.1f, 0.5f, 0.5f, 1);
    }

    @Override
    protected void back() {
        game.setScreen(new MainMenu(game));
    }

    @Override
    protected void processSelection(int selection) {
        if (selection == 0) {
            game.setScreen(new MainMenu(game));
            dispose();
        }
    }

    @Override
    protected void drawMenu() {
        game.staticBatch.begin();
        game.font.setScale(1, 1);
        game.font.setColor(0, 0, 0, 1);
        game.font.draw(game.staticBatch, "help", 50, 430);
        game.font.setScale(0.5f, 0.5f);
        game.font.draw(game.staticBatch, "tap left to move left, tap right to move right", 50, 360);
        game.font.draw(game.staticBatch, "while moving, tap anywhere on the screen to jump", 50, 320);
        game.font.draw(game.staticBatch, "while not moving, tap in the middle to jump", 50, 280);
        game.font.draw(game.staticBatch, "if a keyboard is available, WASD can be used to move as well", 50, 240);
        game.font.setScale(0.4f, 0.4f);
        game.font.draw(game.staticBatch, "(tap anywhere to go back)", 50, 50);
        game.staticBatch.end();
    }
}
