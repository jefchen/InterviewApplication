package com.jeffrey.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class AmericanFlagView extends View {

    Paint starPaint;
    Paint bluePaint;
    Paint redPaint;
    Path starPath;

    public AmericanFlagView(Context context) {
        super(context);
        init();
    }

    public AmericanFlagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AmericanFlagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        starPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        starPaint.setColor(getResources().getColor(android.R.color.white));
        starPaint.setStyle(Paint.Style.FILL);

        bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        bluePaint.setStyle(Paint.Style.FILL);

        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setColor(getResources().getColor(android.R.color.holo_red_light));
        redPaint.setStyle(Paint.Style.FILL);

        starPath = new Path();
    }

    /**
     * Calculate view when size is changed, don't calculate every time we draw
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw red lines
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        final int BAR_HEIGHT = 40;
        for (int i = 0; i < height - BAR_HEIGHT; i+=BAR_HEIGHT*2) {
            canvas.drawRect(0, i, width, i + BAR_HEIGHT, redPaint);
        }

        // draw blue box
        int boxRight = (int) (width * 0.4f);
        int boxBottom = (int) (height * 0.4f);
        canvas.drawRect(0, 0, boxRight, boxBottom, bluePaint);

        final int STAR_WIDTH = 50;
        final int STAR_HEIGHT = 50;
        final int PADDING = 20;
        final int GUTTER = 50;
        final int[] Y_START = {PADDING, PADDING + (STAR_HEIGHT + GUTTER)/2};
        int yStart = 0;
        for (int x = PADDING; x + STAR_WIDTH < boxRight - PADDING; x += STAR_WIDTH + GUTTER) {
            for (int y = Y_START[yStart]; y + STAR_HEIGHT < boxBottom - PADDING; y += STAR_HEIGHT + GUTTER) {
                drawStar(canvas, x, y, STAR_WIDTH, STAR_HEIGHT);
            }
            yStart = (yStart + 1)%2;
        }
    }

    private void drawStar(Canvas canvas, int x, int y, int width, int height) {
        float x2 = x + (width / 2);
        float y2 = y + (height / 3);
        float bottom = y + height;
        float right = x + width;

        starPath.reset();
        starPath.moveTo(x2, y);
        starPath.lineTo(right, bottom);
        starPath.lineTo(x, y2);
        starPath.lineTo(right, y2);
        starPath.lineTo(x, bottom);
        starPath.close();

        canvas.drawPath(starPath, starPaint);
    }
}
