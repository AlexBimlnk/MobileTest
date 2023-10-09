package com.example.lr6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class DrawView extends View {
    private Paint p;
    private RectF rectF;

    float points[];
    float points1[];

    public DrawView(Context context){
        super(context);
        p = new Paint();
        rectF = new RectF(700, 100, 800, 150);
        points = new float[] { 100, 50, 150, 100, 150, 200, 50, 200, 50, 100 };
        points1 = new float[] { 300, 200, 600, 200, 300, 300, 600, 300, 400, 100, 400, 400, 500, 100, 500, 400 };
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawARGB(80, 102, 204, 255);

        p.setColor(Color.RED);
        p.setStrokeWidth(15);

        drawPolygon(canvas, 5 , 150, 600, 400);

        p.setColor(Color.MAGENTA);

        drawPolygon(canvas, 6, 150, 600, 1500);
    }
    private void drawPolygon(Canvas canvas, int sides, int size, float xPos, float yPos){
        for (int i = 1; i <= sides; i++){

            float xEnd = (float)(xPos + size * Math.cos(i * 2 * Math.PI / sides));
            float yEnd = (float) (yPos + size * Math.sin(i * 2 * Math.PI / sides));

            canvas.drawLine(xPos, yPos, (float) xEnd, (float) yEnd,p);
            xPos = xEnd;
            yPos = yEnd;
        }
    }

}
