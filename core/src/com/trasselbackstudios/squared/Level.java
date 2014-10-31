package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

public class Level {
    public static final String PLATFORM = "1";
    public static final String DOOR = "2";
    public static final int max = 3;
    private static int current = 1;
    private static int maxTime = 20;
    private static int time = maxTime;
    private static boolean timerStarted = false;
    private static Timer timer = new Timer();
    private String name = "";
    private String[][] tileMap;
    private Array<Block> blocks = new Array<Block>();
    private final Squared game;
    private final Engine currentEngine;

    public Level(Squared game, Engine currentEngine) {
        this.game = game;
        this.currentEngine = currentEngine;
        loadLevel();
    }

    private void loadLevel() {
        startTimer();
        FileHandle file = Gdx.files.internal("levels/map_0" + current);
        String[] rows = file.readString().split("\\n");
        name = rows[0];
        // Subtract one for name line in file.
        tileMap = new String[rows.length - 1][rows[1].split(", ").length];
        for (int i = 0, j = 1; i < tileMap.length; i++, j++) {
            tileMap[i] = rows[j].split(", ");
        }
        setupLevel();
    }

    private void setupLevel(){
        float x = 0, y = 0;
        float blockSize = 50;
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j].equals(PLATFORM))
                    blocks.add(new Block(x, y, blockSize, blockSize, Block.PLATFORM));
                else if (tileMap[i][j].equals(DOOR))
                    blocks.add(new Block(x, y, blockSize, blockSize, Block.DOOR));
                x += blockSize;
            }
            x = 0;
            y += blockSize;
        }
    }

    public void draw(OrthographicCamera camera, ShapeRenderer shapeRenderer) {
        float playerWidth = 50;
        float cameraOffsetLeft = camera.position.x - camera.viewportWidth / 2 - playerWidth;
        for (Block block : blocks) {
            boolean onScreen = camera.position.x + 150 > block.x && block.x > cameraOffsetLeft;
            if (onScreen)
                block.draw(shapeRenderer);
        }
    }

    // TODO enemies need updating.
    public void update() {
        if(time <= 0)
            game.setScreen(new TransitionScreen(game, new GameOverMenu(game)));
    }

    public void dispose() {
        blocks.clear();
        tileMap = null;
    }

    public String[][] getTileMap() {
        return tileMap;
    }

    public void changeLevel() {
        // TODO Better end game.
        if(current >= max) {
            game.setScreen(new TransitionScreen(game, new Congratulations(game)));
            return;
        }
        time = maxTime;
        current++;
        dispose();
        loadLevel();
        game.setScreen(new TransitionScreen(game, currentEngine));
    }

    public void drawText(Squared game) {
        game.font.setColor(0.3f, 0.3f, 0.3f, 1);
        game.font.setScale(1);
        game.font.draw(game.batch, name, 0, 300);
    }

    public void drawTime(Squared game) {
        game.font.setColor(0.0f, 0.0f, 0.0f, 1);
        game.font.setScale(0.25f);
        game.font.draw(game.staticBatch, time + "", 10, 470);
    }

    public static void setLevel(int level) {
        if(level > max) return;
        current = level;
    }

    public static void startTimer() {
        // Resume timer if it already started.
        if (timerStarted) {
            timer.start();
            return;
        }
        timerStarted = true;
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (time >= 0) {
                    time--;
                }
            }
        }, 1, 1);
    }

    public static void pauseTimer() {
        if (!timerStarted) return;
        timer.stop();
    }

    public static void reset(){
        time = maxTime;
    }
}