package com.trasselbackstudios.squared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Stats {
    private static int levelsUnlocked = 1;

    public Stats() {
    }

    public static int load() {
        FileHandle file = Gdx.files.local("save");
        try {
            levelsUnlocked = Integer.parseInt(file.readString());
        } catch (GdxRuntimeException ex) {
            if (ex.toString().equals("com.badlogic.gdx.utils.GdxRuntimeException: File not found: save (Local)"))
                file.writeString("1", false);
        }
        return levelsUnlocked;
    }

    public static void save(int level) {
        if(level < levelsUnlocked) return;
        if (Gdx.files.isLocalStorageAvailable()) {
            FileHandle file = Gdx.files.local("save");
            file.writeString(level + "", false);
        }
    }

}
