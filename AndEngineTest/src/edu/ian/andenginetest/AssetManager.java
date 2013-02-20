package edu.ian.andenginetest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

public class AssetManager {

    private static AssetManager instance;
    private HashMap<String, ITexture> textures;
    private MainActivity mainActivity;

    // private VertexBufferObjectManager vbom;

    private AssetManager() {
        mainActivity = MainActivity.getInstance();
        textures = new HashMap<String, ITexture>();
    }

    public static AssetManager getInstance() {
        if (instance == null)
            instance = new AssetManager();

        return instance;
    }

    public ITextureRegion getTextureRegion(String filename) {

        ITexture texture = textures.get(filename);
        if (texture != null)
            return TextureRegionFactory.extractFromTexture(texture);
        else
            return loadTexture(filename);
    }

    private ITextureRegion loadTexture(final String filename) {

        try {
            ITexture texture = new BitmapTexture(
                    mainActivity.getTextureManager(), new IInputStreamOpener() {
                        @Override
                        public InputStream open() throws IOException {
                            return mainActivity.getAssets().open(filename);
                        }
                    });
            textures.put(filename, texture);
            texture.load();
            return TextureRegionFactory.extractFromTexture(texture);
        } catch (IOException e) {
            Debug.e(e);
        }
        return null;
    }

    public void unloadTexture(String filename) {
        ITexture texture = textures.get(filename);
        texture.unload();
        textures.remove(filename);
    }
}
