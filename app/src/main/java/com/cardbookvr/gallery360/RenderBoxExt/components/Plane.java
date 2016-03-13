package com.cardbookvr.gallery360.RenderBoxExt.components;

import com.cardbookvr.gallery360.RenderBoxExt.materials.BorderMaterial;
import com.cardbookvr.renderbox.components.RenderObject;
import com.cardbookvr.renderbox.materials.DiffuseLightingMaterial;
import com.cardbookvr.renderbox.materials.UnlitTexMaterial;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Plane class
 * Created by Schoen and Jonathan on 3/12/2016.
 */
public class Plane extends RenderObject {

    public static final float[] COORDS = new float[] {
            -1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            -1.0f, -1.0f, 0.0f,
            1.0f, -1.0f, 0.0f
    };
    public static final float[] TEX_COORDS = new float[] {
            0.0f, 1.0f,
            1.0f, 1.0f,
            0f, 0f,
            1.0f, 0f,
    };
    public static final float[] COLORS = new float[] {
            0.5f, 0.5f, 0.5f, 1.0f,
            0.5f, 0.5f, 0.5f, 1.0f,
            0.5f, 0.5f, 0.5f, 1.0f,
            0.5f, 0.5f, 0.5f, 1.0f
    };
    public static final float[] NORMALS = new float[] {
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f
    };
    public static final short[] INDICES = new short[] {
            0, 1, 2,
            1, 3, 2
    };

    private static FloatBuffer vertexBuffer;
    private static FloatBuffer colorBuffer;
    private static FloatBuffer normalBuffer;
    private static FloatBuffer texCoordBuffer;
    private static ShortBuffer indexBuffer;
    static final int numIndices = 6;

    static boolean setup;

    public Plane(){
        super();
        allocateBuffers();
    }

    public Plane(int textureId, boolean lighting) {
        super();
        allocateBuffers();
        if (lighting) {
            createDiffuseMaterial(textureId);
        } else {
            createUnlitTexMaterial(textureId);
        }
    }

    public Plane createDiffuseMaterial(int textureId) {
        DiffuseLightingMaterial mat = new DiffuseLightingMaterial(textureId);
        mat.setBuffers(vertexBuffer, normalBuffer, texCoordBuffer, indexBuffer, numIndices);
        material = mat;
        return this;
    }

    public Plane createUnlitTexMaterial(int textureId) {
        UnlitTexMaterial mat = new UnlitTexMaterial(textureId);
        mat.setBuffers(vertexBuffer, texCoordBuffer, indexBuffer, numIndices);
        material = mat;
        return this;
    }

    public void setupBorderMaterial(BorderMaterial material){
        this.material = material;
        material.setBuffers(vertexBuffer, texCoordBuffer, indexBuffer, numIndices);
    }

    public static void allocateBuffers(){
        if (setup) return;
        setup = true;
        vertexBuffer   = allocateFloatBuffer(COORDS);
        texCoordBuffer = allocateFloatBuffer(TEX_COORDS);
        colorBuffer    = allocateFloatBuffer(COLORS);
        normalBuffer   = allocateFloatBuffer(NORMALS);
        indexBuffer    = allocateShortBuffer(INDICES);
    }

}