package com.example.demoprojectforrnd.customviews.ribbon

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.demoprojectforrnd.R

class RibbonLayout : FrameLayout,
    RibbonInterface {

    var ribbonHeaderAlign: Int = Gravity.START
        set(value) {
            field = value
            updateRibbon()
        }
    var ribbonBottomAlign: Int = Gravity.CENTER
        set(value) {
            field = value
            updateRibbon()
        }
    var ribbonHeader: RibbonInterface =
        RibbonView(context)
        set(value) {
            field = value
            updateRibbon()
        }
    var ribbonBottom: RibbonInterface =
        RibbonView(context)
        set(value) {
            field = value
            updateRibbon()
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttrs(attrs, defStyleAttr)
    }

    private fun getAttrs(attributeSet: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attributeSet,
            R.styleable.RibbonLayout
        )
        try {
            setTypeArray(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun getAttrs(attributeSet: AttributeSet, defStyleAttr: Int) {
        val typedArray =
            context.obtainStyledAttributes(attributeSet,
                R.styleable.RibbonLayout, defStyleAttr, 0)
        try {
            setTypeArray(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setTypeArray(typeArray: TypedArray) {
        this.ribbonHeaderAlign =
            typeArray.getInteger(R.styleable.RibbonLayout_ribbonLayout_header_align, ribbonHeaderAlign)
        this.ribbonBottomAlign =
            typeArray.getInteger(R.styleable.RibbonLayout_ribbonLayout_bottom_align, ribbonBottomAlign)
    }

    /** update [RibbonLayout] after finishing inflate. */
    override fun onFinishInflate() {
        super.onFinishInflate()
        updateRibbon()
    }

    /** update [RibbonLayout] by attributes. */
    override fun updateRibbon() {
        val headerParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        headerParams.gravity = ribbonHeaderAlign or Gravity.TOP
        addRibbonInterface(ribbonHeader, headerParams)

        val bottomParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        bottomParams.gravity = ribbonBottomAlign or Gravity.BOTTOM
        addRibbonInterface(ribbonBottom, bottomParams)

        invalidate()
    }

    private fun addRibbonInterface(ribbonInterface: RibbonInterface, params: LayoutParams) {
        ribbonInterface.apply {
            if (this is RibbonView) {
                this.layoutParams = params
                addRibbonView(this)
            } else if (this is ShimmerRibbonView) {
                this.layoutParams = params
                addRibbonView(this)
            }
        }
    }

    private fun addRibbonView(view: View) {
        removeView(view)
        addView(view)
    }
}
