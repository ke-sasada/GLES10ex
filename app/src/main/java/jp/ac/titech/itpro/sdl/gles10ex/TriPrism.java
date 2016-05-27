package jp.ac.titech.itpro.sdl.gles10ex;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class TriPrism implements SimpleRenderer.Obj{

    private FloatBuffer vbuf;
    private float x,y,z;

    public TriPrism(float s, float x, float y, float z) {

        float[] vertices = {
                //bottom
                -s,-s,-s,
                s,-s,-s,
                0,-s,s*0.73f,
                //top
                -s,s,-s,
                s,s,-s,
                0,s,s*0.73f,
                //left
                0,-s,s*0.73f,
                0,s,s*0.73f,
                -s,-s,-s,
                -s,s,-s,
                //right
                s,-s,-s,
                s,s,-s,
                0,-s,s*0.73f,
                0,s,s*0.73f,
                //front
                -s,-s,-s,
                -s,s,-s,
                s,-s,-s,
                s,s,-s
        };

        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);

        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vbuf);

        //bottom
        gl.glNormal3f(0,-1,0);
        gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);

        //top
        gl.glNormal3f(0,1,0);
        gl.glDrawArrays(GL10.GL_TRIANGLES,3,3);

        //left
        gl.glNormal3f(-1,0,1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,6,4);

        //right
        gl.glNormal3f(1,0,1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,10,4);

        //front
        gl.glNormal3f(0,0,-1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,14,4);


    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}
