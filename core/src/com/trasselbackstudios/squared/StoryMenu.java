package com.trasselbackstudios.squared;

import com.badlogic.gdx.graphics.Color;

public class StoryMenu extends Menu {
    private String story =
            "Once living a peaceful life in a small fishing village, \n" +
            "the protagonist finds itself on a strange planet.\n" +
            "A voice guides the protagonist though various obstacles \n" +
            "and demons. The protagonist must find its way back home,\n" +
            "if it wishes to see its spouse and child once more.\n" +
            "This is a story about finding oneself.";

    public StoryMenu(Squared game) {
        super(game);
    }

    @Override
    protected void setup() {
        background = new Color(0.5f, 0.5f, 0.1f, 1);
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
        game.font.draw(game.staticBatch, "story", 50, 430);
        game.font.setScale(0.5f, 0.5f);
        float x = 50, y = 360;
        for (String line : story.split("\\n")) {
            game.font.draw(game.staticBatch, line, x, y);
            y -= 40;
        }
//        game.font.draw(game.staticBatch, "Once a human born in a small fishing village, the protagonist finds itself on a strange planet.", 50, 360);
        game.font.setScale(0.4f, 0.4f);
        game.font.draw(game.staticBatch, "(tap anywhere to go back)", 50, 50);
        game.staticBatch.end();
    }
}
