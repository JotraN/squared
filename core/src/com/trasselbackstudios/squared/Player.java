package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player extends Rectangle {
    private float velX = 0, velY = 0;
    private float speed = 5;
    private float jump = 10;
    private float gravity = 0.75f;
    private boolean inAir = false;
    private Vector3 touchPos = new Vector3();

    public Player() {
        x = 0;
        y = 50;
        width = 50;
        height = 50;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
        shapeRenderer.rect(x, y, this.width, this.height);
    }

    public void processEvents(OrthographicCamera camera) {
        if (Gdx.input.isTouched(0)) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x > x + camera.viewportWidth / 3)
                velX = speed;
            else if (touchPos.x < x - camera.viewportWidth / 3)
                velX = -speed;
            else if (!inAir) {
                velY = jump;
                inAir = true;
            }
        } else {
            velX = 0;
            if (!inAir)
                velY = 0;
        }
        boolean secondTouch = Gdx.input.isTouched(1);
        if (secondTouch) {
            if (!inAir && Gdx.input.isTouched(0)) {
                velY = jump;
                inAir = true;
            }
        } else {
            if (!inAir && velY != jump)
                velY = 0;
        }

        // TODO Update PC controls
        if (Gdx.input.isKeyPressed(Input.Keys.A)) velX = -speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) velX = speed;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) if (!inAir) velY = jump;
    }

    public void update(Level level) {
        // TODO Set jump ceiling
        // TODO Can't apply delta as tileMap collision detection doesn't work well with decimal x values
//        float delta = Math.min(Gdx.graphics.getDeltaTime(), 1.0f/30.0f);
//        velX *= delta;
        x += velX;
        String[][] tileMap = level.getTileMap();
        int blockWidth = 50;
        int mapWidth = tileMap[0].length * blockWidth;
        if (x < 0 || this.x > mapWidth - this.width)
            x -= velX;
        int col = (int) Math.floor((x + this.width - 1) / blockWidth);
        if (velX < 0)
            col = (int) Math.floor(x / blockWidth);
        int rowBottom = (int) Math.floor(y / blockWidth);
        int rowTop = (int) Math.floor((y + this.height - 1) / blockWidth);
        if (tileMap[rowBottom][col].equals(Level.PLATFORM) || tileMap[rowTop][col].equals(Level.PLATFORM))
            x -= velX;
        else if (tileMap[rowBottom][col].equals(Level.DOOR) || tileMap[rowTop][col].equals(Level.DOOR)) {
            x = 0;
            y = 50;
            level.changeLevel();
        }

        y += velY;
        inAir = true;
        int row = (int) Math.floor((y + this.height - 1) / 50);
        int leftCol = (int) Math.floor(x / 50);
        int rightCol = (int) Math.floor((x + this.width - 1) / 50);
        if (velY < 0) row = (int) Math.floor(y / 50);
        if (tileMap[row][leftCol].equals(Level.PLATFORM) || tileMap[row][rightCol].equals(Level.PLATFORM)) {
            y -= velY;
            inAir = false;
        } else if (tileMap[row][leftCol].equals(Level.DOOR) || tileMap[row][rightCol].equals(Level.DOOR)) {
            x = 0;
            y = 50;
            level.changeLevel();
        }

        if (inAir) velY = velY - gravity;
    }
}
