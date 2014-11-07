package com.trasselbackstudios.squared;

import com.badlogic.gdx.graphics.Color;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class LevelMenu extends Menu {
    private int unlocked = 1;

    public LevelMenu(Squared game) {
        super(game);
        unlocked = Stats.load();
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
        menuMap[0][0] = -1;
        background = new Color(0.2f, 0.1f, 0.5f, 1);
    }

    protected void drawMenu() {
        game.staticBatch.begin();
        float padding = 50;
        game.font.setScale(1f);
        game.font.draw(game.staticBatch, "levels", column * 0 + padding, row * (Level.max + 1) + padding);
        game.font.setScale(0.5f);
        for (int i = 0; i < menuMap.length; i++) {
            game.font.setColor(0.7f, 0.7f, 0.7f, 1);
            if (menuMap[i][2] != 0) {
                String sym = "+";
                if (menuMap[i][2] > unlocked) {
                    game.font.setColor(0.7f, 0.7f, 0.1f, 1);
                    sym = "-";
                }
                game.font.draw(game.staticBatch, "[" + sym + menuMap[i][2] + "]", column * 2 + padding / 2, row * i + padding);
            }
        }
        game.font.draw(game.staticBatch, "back", column * 0 + padding, row * 0 + padding);
        game.staticBatch.end();
    }

    @Override
    protected void back() {
        game.setScreen(new MainMenu(game));
    }

    @Override
    protected void processSelection(int selection) {
        if(selection == -1){
            game.setScreen(new MainMenu(game));
            dispose();
        }
        if (selection > 0) {
            if (selection <= unlocked) {
                Level.setLevel(selection);
                Level.reset();
                game.setScreen(new TransitionScreen(game, new Engine(game)));
                dispose();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
