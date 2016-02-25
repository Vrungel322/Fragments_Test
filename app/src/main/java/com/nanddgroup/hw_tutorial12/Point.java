package com.nanddgroup.hw_tutorial12;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Nikita on 29.01.2016.
 */
public class Point extends View{
    private Paint paint;
    float x;
    float y;
    public Point(Context context, float x, float y) {
        super(context);
        this.x = x;
        this.y = y;
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, 25, paint);
    }
}
