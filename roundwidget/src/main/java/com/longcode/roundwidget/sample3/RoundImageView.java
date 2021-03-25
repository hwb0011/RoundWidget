package com.longcode.roundwidget.sample3;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.evil.rlayout.helper.RoundAttrs;
import com.evil.rlayout.helper.RoundHelper;


/**
 * 作用：圆角图片
 */
public class RoundImageView extends ImageView implements RoundAttrs {

    RoundHelper mRCHelper;

    public RoundImageView(@NonNull Context context) {
        super(context);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, null);
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, attrs);
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr,
            int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        mRCHelper = new RoundHelper();
        mRCHelper.initAttrs(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRCHelper.onSizeChanged(this, w, h);
    }


    @Override
    public void draw(Canvas canvas) {
        mRCHelper.refreshRegion(this);
        if (mRCHelper.mClipBackground) {
            canvas.save();
            canvas.clipPath(mRCHelper.mClipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(mRCHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        mRCHelper.openHardware(this);
        mRCHelper.onClipDraw(canvas, false);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //        int action = ev.getAction();
        //        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
        //            refreshDrawableState();
        //        } else if (action == MotionEvent.ACTION_CANCEL) {
        //            setPressed(false);
        //            refreshDrawableState();
        //        }
        //        if (!mRCHelper.mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
        //            setPressed(false);
        //            refreshDrawableState();
        //            return false;
        //        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();
        mRCHelper.drawableStateChanged(this);
    }

    //--- 公开接口 ----------------------------------------------------------------------------------

    public void setClipBackground(boolean clipBackground) {
        mRCHelper.mClipBackground = clipBackground;
        invalidate();
    }

    public void setRoundAsCircle(boolean roundAsCircle) {
        mRCHelper.mRoundAsCircle = roundAsCircle;
        invalidate();
    }

    public void setRadius(int radius) {
        for (int i = 0; i < mRCHelper.radii.length; i++) {
            mRCHelper.radii[i] = radius;
        }
        invalidate();
    }

    public void setTopLeftRadius(int topLeftRadius) {
        mRCHelper.radii[0] = topLeftRadius;
        mRCHelper.radii[1] = topLeftRadius;
        invalidate();
    }

    public void setTopRightRadius(int topRightRadius) {
        mRCHelper.radii[2] = topRightRadius;
        mRCHelper.radii[3] = topRightRadius;
        invalidate();
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        mRCHelper.radii[4] = bottomLeftRadius;
        mRCHelper.radii[5] = bottomLeftRadius;
        invalidate();
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        mRCHelper.radii[6] = bottomRightRadius;
        mRCHelper.radii[7] = bottomRightRadius;
        invalidate();
    }

    public void setStrokeWidth(int strokeWidth) {
        mRCHelper.mStrokeWidth = strokeWidth;
        invalidate();
    }

    public void setStrokeColor(int strokeColor) {
        mRCHelper.mStrokeColor = strokeColor;
        invalidate();
    }

    public boolean isClipBackground() {
        return mRCHelper.mClipBackground;
    }

    public boolean isRoundAsCircle() {
        return mRCHelper.mRoundAsCircle;
    }

    public float getTopLeftRadius() {
        return mRCHelper.radii[0];
    }

    public float getTopRightRadius() {
        return mRCHelper.radii[2];
    }

    public float getBottomLeftRadius() {
        return mRCHelper.radii[4];
    }

    public float getBottomRightRadius() {
        return mRCHelper.radii[6];
    }

    public int getStrokeWidth() {
        return mRCHelper.mStrokeWidth;
    }

    public int getStrokeColor() {
        return mRCHelper.mStrokeColor;
    }


    //--- Selector 支持 ----------------------------------------------------------------------------

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        mRCHelper.drawableStateChanged(this);
    }

    //    @Override
    //    public void setChecked(boolean checked) {
    //        if (mRCHelper.mChecked != checked) {
    //            mRCHelper.mChecked = checked;
    //            refreshDrawableState();
    //            if (mRCHelper.mOnCheckedChangeListener != null) {
    //                mRCHelper.mOnCheckedChangeListener.onCheckedChanged(this, mRCHelper.mChecked);
    //            }
    //        }
    //    }
    //
    //    @Override
    //    public boolean isChecked() {
    //        return mRCHelper.mChecked;
    //    }
    //
    //    @Override
    //    public void toggle() {
    //        setChecked(!mRCHelper.mChecked);
    //    }
    //
    //    public void setOnCheckedChangeListener(RoundHelper.OnCheckedChangeListener listener) {
    //        mRCHelper.mOnCheckedChangeListener = listener;
    //    }
}