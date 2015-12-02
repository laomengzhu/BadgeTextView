package com.ns.badgetextview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by xiaolifan on 2015/7/29.
 */
public class BadgeTextView extends TextView {

    private int badgeCount = 0;
    private Paint badgerPaint;
    private int badgerBgColor, badgerTextColor;
    private int badgerMargin = 0, badgerPadding = 0;
    private int textHeight = 0;
    private float strokeWidth = 1;

    public BadgeTextView(Context context) {
        super(context);
        init(context, null);
    }

    public BadgeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BadgeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BadgeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        badgerPaint = new Paint();
        badgerPaint.setAntiAlias(true);
        badgerPaint.setStyle(Paint.Style.FILL);
        badgerPaint.setTextAlign(Paint.Align.CENTER);

        if (isInEditMode()) {
            return;
        }

        //读取徽章属性，徽章文字颜色、徽章文字大小、徽章背景色、徽章的内外边距
        if (attrs == null) {
            badgerPaint.setTextSize(getTextSize());
            badgerTextColor = getCurrentTextColor();
            badgerBgColor = Color.RED;
            badgerMargin = 0;
            badgerPadding = (int) (getResources().getDisplayMetrics().density * 8);
        } else {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeTextView);

            int resID = ta.getResourceId(R.styleable.BadgeTextView_badgeTextSize, -1);
            if (resID == -1) {
                badgerPaint.setTextSize(ta.getDimensionPixelSize(R.styleable.BadgeTextView_badgeTextSize, (int) getTextSize()));
            } else {
                badgerPaint.setTextSize(getResources().getDimensionPixelSize(resID));
            }

            resID = ta.getResourceId(R.styleable.BadgeTextView_badgeTextColor, -1);
            if (resID == -1) {
                badgerTextColor = ta.getColor(R.styleable.BadgeTextView_badgeTextColor, getCurrentTextColor());
            } else {
                badgerTextColor = getResources().getColor(resID);
            }

            resID = ta.getResourceId(R.styleable.BadgeTextView_badgeBackgroundColor, -1);
            if (resID == -1) {
                badgerBgColor = ta.getColor(R.styleable.BadgeTextView_badgeBackgroundColor, Color.RED);
            } else {
                badgerBgColor = getResources().getColor(resID);
            }

            resID = ta.getResourceId(R.styleable.BadgeTextView_badgeMargin, -1);
            if (resID == -1) {
                badgerMargin = ta.getDimensionPixelSize(R.styleable.BadgeTextView_badgeMargin, 0);
            } else {
                badgerMargin = getResources().getDimensionPixelSize(resID);
            }

            resID = ta.getResourceId(R.styleable.BadgeTextView_badgePadding, -1);
            if (resID == -1) {
                badgerPadding = ta.getDimensionPixelSize(R.styleable.BadgeTextView_badgePadding, (int) (getResources().getDisplayMetrics().density * 8));
            } else {
                badgerPadding = getResources().getDimensionPixelSize(resID);
            }

            ta.recycle();
        }

        if (isInEditMode()) {
            badgeCount = 8;
        }

        textHeight = getTextHeight(badgerPaint);
        strokeWidth = getResources().getDisplayMetrics().density * strokeWidth + 0.5f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (badgeCount > 0) {
            badgerPaint.setTypeface(Typeface.DEFAULT);
            String countStr = String.valueOf(badgeCount);
            float textWidth = badgerPaint.measureText("88");
            float bgRadius = badgerPadding + textWidth / 2;

            //画徽章背景圆
            badgerPaint.setColor(badgerBgColor);
            float cx = canvas.getWidth() - badgerMargin - bgRadius;
            float cy = badgerMargin + bgRadius;
            canvas.drawCircle(cx, cy, bgRadius, badgerPaint);
            //画徽章背景圆描边
            badgerPaint.setColor(badgerTextColor);
            badgerPaint.setStyle(Paint.Style.STROKE);
            badgerPaint.setStrokeWidth(strokeWidth);
            canvas.drawCircle(cx, cy, bgRadius, badgerPaint);
            //画徽章数字
            badgerPaint.setStyle(Paint.Style.FILL);
            badgerPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(countStr, cx, cy + textHeight / 3, badgerPaint);
        }

    }

    public void setBadgeCount(int count) {
        this.badgeCount = count;
        invalidate();
    }

    /**
     * 获取指定画笔的文字高度
     */
    private int getTextHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

}
