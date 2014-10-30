package com.trasselbackstudios.squared;

import com.badlogic.gdx.graphics.Color;

public class GameOverMenu extends Menu {

    public GameOverMenu(Squared game) {
        super(game);
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
        background = new Color(0.5f, 0.1f, 0.1f, 1);
    }

    @Override
    protected void back() {
        game.setScreen(new TransitionScreen(game, new MainMenu(game)));
    }

    @Override
    protected void processSelection(int selection) {
        switch(selection){
            case 1:
                game.setScreen(new TransitionScreen(game, new MainMenu(game)));
                dispose();
                break;
            case 2:
                Level.reset();
                game.setScreen(new TransitionScreen(game, new Engine(game)));
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
        game.font.draw(game.staticBatch, "gameover", column * 0 + 50, row * 1);
        game.font.setScale(0.5f);
        game.font.draw(game.staticBatch, "restart:", column * 4, row * 0 + padding);
        game.font.draw(game.staticBatch, "no", column * 5, row * 0 + padding);
        game.font.draw(game.staticBatch, "yes", column * 6, row * 0 + padding);
        game.staticBatch.end();
    }
}
