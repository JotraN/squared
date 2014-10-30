package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;

public abstract class Menu implements Screen {
    protected final Squared game;
    protected int[][] menuMap = new int[][]{
            {0, 0, 0},
    };
    private Vector3 touchPos;
    protected Color background = Color.WHITE;
    protected float width;
    protected float height;
    protected float column = width / menuMap[0].length;
    protected float row = height / menuMap.length;

    public Menu(Squared game) {
        this.game = game;
        touchPos = new Vector3();
        setup();
        width = this.game.staticCamera.viewportWidth;
        height = this.game.staticCamera.viewportHeight;
        column = width / menuMap[0].length;
        row = height / menuMap.length;
    }

    // Setup menuMap and background.
    protected abstract void setup();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        processEvents();
        drawMenu();
    }

    private void processEvents() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            back();
            dispose();
        }
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.staticCamera.unproject(touchPos);
            int row = (int) Math.floor(touchPos.y / (480 / menuMap.length));
            int col = (int) Math.floor(touchPos.x / (800 / menuMap[0].length));
            int selection = menuMap[row][col];
            processSelection(selection);
        }
    }

    protected abstract void back();

    protected abstract void processSelection(int selection);

    protected abstract void drawMenu();

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
