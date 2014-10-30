package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Congratulations implements Screen{
    private final Squared game;

    public Congratulations(Squared game){
       this.game = game;
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.justTouched()) {
            game.setScreen(new MainMenu(game));
            dispose();
        }

        Gdx.gl.glClearColor(0.3f, 0.3f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.staticBatch.begin();
        game.font.setScale(1f);
        game.font.setColor(0.9f, 0.9f, 0.9f, 1);
        game.font.draw(game.staticBatch, "congrats", 30, 70);
        game.font.setScale(0.5f);
        game.font.draw(game.staticBatch, "tap to continue", 240, 55);
        game.staticBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
