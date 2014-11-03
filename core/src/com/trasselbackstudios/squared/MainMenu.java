package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainMenu extends Menu {
    private float color = 1.0f;
    private float increment = -0.05f;
    private Texture bg;
    private ShapeRenderer shapeRenderer;
    private int animationPosX = 0;
    private int animationSpeed = 5;

    public MainMenu(Squared game) {
        super(game);
        shapeRenderer = new ShapeRenderer();
        bg = new Texture(Gdx.files.internal("bg.png"));
    }

    @Override
    protected void setup() {
        menuMap = new int[][]{
                {0, 0, 0},
                {0, 4, 0},
                {0, 3, 0},
                {0, 2, 0},
                {0, 1, 0},
                {0, 0, 0},
        };
    }

    @Override
    protected void drawMenu() {
        float padding = 0.26f;
        game.staticBatch.setProjectionMatrix(game.staticCamera.combined);
        game.staticBatch.begin();
        game.staticBatch.draw(bg, 0, 0);
        game.font.setScale(1);
        game.font.setColor(0.2f, 0.2f, 0.2f, 1);
        game.font.draw(game.staticBatch, "play", column * (1 + padding), row * 5);
        game.font.draw(game.staticBatch, "levels", column * (1 + padding), row * 4);
        game.font.draw(game.staticBatch, "help", column * (1 + padding), row * 3);
        game.font.draw(game.staticBatch, "story", column * (1 + padding), row * 2);
        game.staticBatch.end();

        updateAnimation();
        shapeRenderer.setProjectionMatrix(game.staticCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawAnimation();
        shapeRenderer.end();

        game.staticBatch.begin();
        game.font.setScale(0.75f);
        game.font.setColor(color, color, color, 1);
        animateColor();
        game.font.draw(game.staticBatch, "[squared]", column * 0 + 10, row * 0 + 40);
        game.staticBatch.end();
    }

    private void animateColor() {
        color += increment;
        if (color < 0.0f) {
            increment = 0.05f;
            color = 0.0f;
        } else if (color > 1.0f) {
            increment = -0.05f;
            color = 1.0f;
        }
    }

    private void drawAnimation() {
        shapeRenderer.setColor(0.3f, 0.3f, 0.3f, 1);
        shapeRenderer.rect(0, 0, 800, 50);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
        shapeRenderer.rect(animationPosX, 50, 50, 50);
    }

    private void updateAnimation() {
        animationPosX += animationSpeed;
        if (animationPosX > game.staticCamera.viewportWidth - 50) animationSpeed = -animationSpeed;
        else if (animationPosX < 0) animationSpeed = -animationSpeed;
    }

    @Override
    protected void back() {
        Gdx.app.exit();
    }

    @Override
    protected void processSelection(int selection) {
        switch (selection) {
            // TODO Set up help and story.
            case 1:
                Level.reset();
                game.setScreen(new TransitionScreen(game, new Engine(game)));
                dispose();
                break;
            case 2:
                game.setScreen(new LevelMenu(game));
                dispose();
                break;
            case 3:
                game.setScreen(new HelpMenu(game));
                dispose();
                break;
            case 4:
                game.setScreen(new StoryMenu(game));
                dispose();
                break;
            default:
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
    }
}
