package com.longcode.roundwidget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView

class RoundImageView : AppCompatImageView {

    private var isCircle = false
        set(value) {
            field = value
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                invalidateOutline()
            else invalidate()
        }

    var radius = 0f
        set(value) {
            field = value
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                invalidateOutline()
            else invalidate()
        }

    private val roundOutlineProvider = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object: ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            if (isCircle) {
                val min = width.coerceAtMost(height)
                outline?.setRoundRect(0, 0, min, min, min.toFloat())
            } else {
                outline?.setRoundRect(0, 0, width, height, radius)
            }
        }
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?)  : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.LCRoundWidget)
        radius = a.getDimension(R.styleable.LCRoundWidget_rw_radius, 0f)
        isCircle = a.getBoolean(R.styleable.LCRoundWidget_rw_is_circle, false)
        a.recycle()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            initOutline()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initOutline() {
        outlineProvider = roundOutlineProvider
        clipToOutline = true
    }

}