package com.trasselbackstudios.squared;

import com.badlogic.gdx.graphics.Color;

public class PauseMenu extends Menu {
    private Engine currentEngine;

    public PauseMenu(Squared game, Engine currentEngine) {
        super(game);
        this.currentEngine = currentEngine;
    }

    @Override
    protected void setup() {
        menuMap = new int[][]{
                {0, 0, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        background = new Color(0.1f, 0.1f, 0.5f, 1);
    }

    @Override
    protected void back() {
        game.setScreen(currentEngine);
        Level.startTimer();
        dispose();
    }

    @Override
    protected void processSelection(int selection) {
        switch(selection){
            case 1:
                game.setScreen(currentEngine);
                Level.startTimer();
                dispose();
                break;
            case 2:
                game.setScreen(new TransitionScreen(game, new MainMenu(game)));
                dispose();
                break;
            default:
                break;
        }
    }

    @Override
    protected void drawMenu() {
        float padding = 70;
        game.staticBatch.begin();
        game.font.setScale(1.3f);
        game.font.setColor(0.7f, 0.7f, 0.7f, 1);
        game.font.draw(game.staticBatch, "pause", column * 0 + 50, row * 1);
        game.font.setScale(0.5f);
        game.font.draw(game.staticBatch, "resume", column * 5, row * 0 + padding);
        game.font.draw(game.staticBatch, "quit", column * 6, row * 0 + padding);
        game.staticBatch.end();
    }
}
