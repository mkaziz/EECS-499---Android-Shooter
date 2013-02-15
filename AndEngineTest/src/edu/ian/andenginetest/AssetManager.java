package edu.ian.andenginetest;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

public class AssetManager {

    private static AssetManager instance;

    private MainActivity mainActivity;
    public VertexBufferObjectManager vbom;

    private AssetManager() {
        mainActivity = MainActivity.getInstance();
    }

    public static AssetManager getInstance() {
        if (instance == null)
            instance = new AssetManager();

        return instance;
    }

    public ITextureRegion loadTexture(final String filename) {
        try {
            ITexture backgroundTexture = new BitmapTexture(
                    mainActivity.getTextureManager(), new IInputStreamOpener() {
                        @Override
                        public InputStream open() throws IOException {
                            return mainActivity.getAssets().open(filename);
                        }
                    });
            backgroundTexture.load();
            return TextureRegionFactory.extractFromTexture(backgroundTexture);
        } catch (IOException e) {
            Debug.e(e);
        }
        return null;
    }

}
