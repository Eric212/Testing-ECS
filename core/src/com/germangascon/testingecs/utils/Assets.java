package com.germangascon.testingecs.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static TextureRegion[][] shipsRegion;
    public static TextureRegion[][] tilesRegion;

    public static TextureRegion player;
    public static BitmapFont font;
    private final static AssetManager assetManager = new AssetManager();

    public static void load() {
        assetManager.load("ships_packed_rotated.png", Texture.class);
        assetManager.load("tiles_packed.png", Texture.class);
        // Añadir aquí la carga de todos los assets.

        font = new BitmapFont();
    }

    public static void update() {
        assetManager.update();
        if(assetManager.isFinished()) {
            shipsRegion = loadTexturePack("ships_packed_rotated.png", 4, 6, 32, 32);
            player = shipsRegion[0][4];
            tilesRegion = loadTexturePack("tiles_packed.png", 10, 12, 32, 32);
        }
    }

    public static boolean isFinished() {
        return assetManager.isFinished();
    }

    private static TextureRegion[][] loadTexturePack(String fileName, int rows, int cols, int width, int height) {
        Texture texture = loadTexture(fileName);
        TextureRegion[][] regions = new TextureRegion[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                regions[i][j] = new TextureRegion(texture, j * width, i * height, width, height);
            }
        }
        return regions;
    }

    private static Texture loadTexture(String fileName) {
        return assetManager.get(fileName, Texture.class);
    }
}
