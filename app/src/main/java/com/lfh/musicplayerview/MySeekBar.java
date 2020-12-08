package com.lfh.musicplayerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.ContextCompat;

/**
 * TODO: document your custom view class.
 */


//UNDONE


public class MySeekBar extends View {
//    private String mExampleString; // TODO: use a default from R.string...
//    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
//    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
//    private Drawable mExampleDrawable;

//    private TextPaint mTextPaint;
//    private float mTextWidth;
//    private float mTextHeight;

    private int mStart;
    private int mEnd;
    private int mTop;
    public int mHeight;
    public int mRadius;
    public float mProgress;

    private int mBgColor;
    private int mCircleColor;
    private int mStartColor;
    private int mEndColor;
    private int mCenterColor;
    private Paint mBgPaint;
    public Paint mProgressPaint;

    private int maxProgress;

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public MySeekBar(Context context) {
//        super(context);
        this(context, null);
    }

    public MySeekBar(Context context, AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public MySeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        init(attrs, defStyle);
        initParams(context, attrs, defStyle);
    }

//    private void init(AttributeSet attrs, int defStyle) {
//        // Load attributes
//        final TypedArray a = getContext().obtainStyledAttributes(
//                attrs, R.styleable.MySeekBar, defStyle, 0);
//
//        mExampleString = a.getString(
//                R.styleable.MySeekBar_exampleString);
//        mExampleColor = a.getColor(
//                R.styleable.MySeekBar_exampleColor,
//                mExampleColor);
//
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        mExampleDimension = a.getDimension(
//                R.styleable.MySeekBar_exampleDimension,
//                mExampleDimension);
//
//        if (a.hasValue(R.styleable.MySeekBar_exampleDrawable)) {
//            mExampleDrawable = a.getDrawable(
//                    R.styleable.MySeekBar_exampleDrawable);
//            mExampleDrawable.setCallback(this);
//        }
//
//        a.recycle();

//        // Set up a default TextPaint object
//        mTextPaint = new TextPaint();
//        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
//        mTextPaint.setTextAlign(Paint.Align.LEFT);
//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements();
//    }

    private void initPaint() {
        mBgPaint = new Paint();
        mBgPaint.setAntiAlias(true);

        mBgPaint.setStrokeCap(Paint.Cap.ROUND);
        mBgPaint.setStrokeWidth((float) mHeight);
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressPaint.setStrokeWidth((float) mHeight);
    }

    public void initParams(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray seekArray = context.obtainStyledAttributes(attrs, R.styleable.MySeekBar, defStyleAttr, 0);
        mStartColor = seekArray.getColor(R.styleable.MySeekBar_startColor, ContextCompat.getColor(context, R.color.startColor));
        mCenterColor = seekArray.getColor(R.styleable.MySeekBar_centerColor, ContextCompat.getColor(context, R.color.centerColor));
        mEndColor = seekArray.getColor(R.styleable.MySeekBar_endColor, ContextCompat.getColor(context, R.color.endColor));
        mBgColor = seekArray.getColor(R.styleable.MySeekBar_backgroundColor, ContextCompat.getColor(context, R.color.bgColor));
        mCircleColor = seekArray.getColor(R.styleable.MySeekBar_circleColor, ContextCompat.getColor(context, R.color.circleColor));

        mHeight = (int) seekArray.getDimension(R.styleable.MySeekBar_height_b, dpToPx(10));
//        mRadius = (int)seekArray.getDimension(R.styleable.MySeekBar_radius,dpToPx(8));
        mRadius = dpToPx(8);
//        mMinRadio = (int)seekArray.getDimension(R.styleable.BaseBar_minRadio,dpToPx(40));
//        mTop = (int)seekArray.getDimension(R.styleable.BaseBar_top,dp2px(15));
        mTop = dpToPx(15);
        seekArray.recycle();
    }

    private void invalidateTextPaintAndMeasurements() {
//        mTextPaint.setTextSize(mExampleDimension);
//        mTextPaint.setColor(mExampleColor);
//        mTextWidth = mTextPaint.measureText(mExampleString);
//
//        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
//        mTextHeight = fontMetrics.bottom;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
//        int paddingLeft = getPaddingLeft();
//        int paddingTop = getPaddingTop();
//        int paddingRight = getPaddingRight();
//        int paddingBottom = getPaddingBottom();
//
//        int contentWidth = getWidth() - paddingLeft - paddingRight;
//        int contentHeight = getHeight() - paddingTop - paddingBottom;


        // Draw the text.
//        canvas.drawText(mExampleString,
//                paddingLeft + (contentWidth - mTextWidth) / 2,
//                paddingTop + (contentHeight + mTextHeight) / 2,
//                mTextPaint);
        canvas.drawLine(mStart, mTop, mEnd, mTop, mBgPaint);
        // Draw the example drawable on top of the text.
//        if (mExampleDrawable != null) {
//            mExampleDrawable.setBounds(paddingLeft, paddingTop,
//                    paddingLeft + contentWidth, paddingTop + contentHeight);
//            mExampleDrawable.draw(canvas);
//        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Gets the example string attribute value.
     *
     * @return The example string attribute value.
     */
//    public String getExampleString() {
//        return mExampleString;
//    }
//
//    /**
//     * Sets the view"s example string attribute value. In the example view, this string
//     * is the text to draw.
//     *
//     * @param exampleString The example string attribute value to use.
//     */
//    public void setExampleString(String exampleString) {
//        mExampleString = exampleString;
//        invalidateTextPaintAndMeasurements();
//    }
//
//    /**
//     * Gets the example color attribute value.
//     *
//     * @return The example color attribute value.
//     */
//    public int getExampleColor() {
//        return mExampleColor;
//    }
//
//    /**
//     * Sets the view"s example color attribute value. In the example view, this color
//     * is the font color.
//     *
//     * @param exampleColor The example color attribute value to use.
//     */
//    public void setExampleColor(int exampleColor) {
//        mExampleColor = exampleColor;
//        invalidateTextPaintAndMeasurements();
//    }
//
//    /**
//     * Gets the example dimension attribute value.
//     *
//     * @return The example dimension attribute value.
//     */
//    public float getExampleDimension() {
//        return mExampleDimension;
//    }
//
//    /**
//     * Sets the view"s example dimension attribute value. In the example view, this dimension
//     * is the font size.
//     *
//     * @param exampleDimension The example dimension attribute value to use.
//     */
//    public void setExampleDimension(float exampleDimension) {
////        mExampleDimension = exampleDimension;
////        invalidateTextPaintAndMeasurements();
//    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
//    public Drawable getExampleDrawable() {
//        return mExampleDrawable;
//    }
//
//    /**
//     * Sets the view"s example drawable attribute value. In the example view, this drawable is
//     * drawn above the text.
//     *
//     * @param exampleDrawable The example drawable attribute value to use.
//     */
//    public void setExampleDrawable(Drawable exampleDrawable) {
//        mExampleDrawable = exampleDrawable;
//    }
    public void drawBg(Canvas canvas) {
        mBgPaint.setColor(mBgColor);
        canvas.drawLine(mStart, mTop, mEnd, mTop, mBgPaint);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        switch (event.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                mProgress = getProgress(event) / 360;
//                mRadius *= 1.5;
//                invalidate();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mProgress = getProgress(event) / 360;
//                if (progressChange != null) {
//                    progressChange.onProgressChange(mProgress);
//                }
//                invalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                getRootView().performClick();
//                mRadius /= 1.5;
//                invalidate();
//                break;
//        }
//        return true;
//    }

//    private float getProgress(MotionEvent event) {
//        float x = event.getX() - mCenterX;
//        float y = event.getY() - mCenterY;
//        if (x > 0 && y > 0) {
//            return (float) Math.toDegrees(Math.atan(y / x));
//        } else if (x < 0 && y > 0) {
//            return 180 + (float) Math.toDegrees(Math.atan(y / x));
//        } else if (x < 0 && y < 0) {
//            return 180 + (float) Math.toDegrees(Math.atan(y / x));
//        } else {
//            return 360 + (float) Math.toDegrees(Math.atan(y / x));
//        }
//    }

//    public void drawCircle(Canvas canvas) {
//        int centerX = (int)getProgress();
//        int centerY =
//        mBgPaint.setColor(mCircleColor);
//        canvas.drawCircle(centerX, centerY, mRadius, mBgPaint);
//    }

    int dpToPx(int dps) {
        return Math.round(getResources().getDisplayMetrics().density * dps);
    }
}