package com.trasselbackstudios.squared;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Squared extends Game {
    public SpriteBatch staticBatch;
    public OrthographicCamera staticCamera;
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        staticCamera = new OrthographicCamera();
        staticCamera.setToOrtho(false, 800, 480);
        staticBatch = new SpriteBatch();
        staticBatch.setProjectionMatrix(staticCamera.combined);
        batch = new SpriteBatch();

        font = new BitmapFont();
        FileHandle fontFile = Gdx.files.internal("Roboto-Regular.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 50;
        font = generator.generateFont(parameter);
        generator.dispose();

        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
