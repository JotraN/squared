package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Engine implements Screen {
    private final Squared game;
    private OrthographicCamera camera;
    private Texture bg;
    private Player player;
    private Level level;
    private ShapeRenderer shapeRenderer;

    public Engine(Squared game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        bg = new Texture(Gdx.files.internal("bg.png"));
        player = new Player();
        level = new Level(game, this);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.BACK) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseMenu(game, this));
            Level.pauseTimer();
        }

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.x + player.width / 2, player.y + 100, 0);
        camera.update();

        level.update();
        player.processEvents(camera);
        player.update(level);

        game.staticBatch.begin();
        game.staticBatch.draw(bg, 0, 0);
        level.drawTime(game);
        game.staticBatch.end();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        level.drawText(game);
        game.batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        level.draw(camera, shapeRenderer);
        player.draw(shapeRenderer);
        shapeRenderer.end();
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
        game.setScreen(new PauseMenu(game, this));
        Level.pauseTimer();
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        game.dispose();
        level.dispose();
        bg.dispose();
        shapeRenderer.dispose();
    }
}
