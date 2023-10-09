package com.example.lr6;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class DrawThread extends Thread{
    private boolean running = false;
    private SurfaceHolder surfaceHolder;

    public DrawThread(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    @Override
    public void run(){
        Canvas canvas = null;

        while (running){
            try {
                canvas = surfaceHolder.lockCanvas(null);

                if (canvas == null)
                    continue;
                canvas.drawColor(Color.GREEN);
            }
            finally {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
