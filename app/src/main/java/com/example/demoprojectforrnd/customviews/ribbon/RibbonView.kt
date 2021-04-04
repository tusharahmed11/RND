package com.example.demoprojectforrnd.customviews.ribbon

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.demoprojectforrnd.R


@DslMarker
annotation class RibbonDsl

/** create a [RibbonView] by [RibbonView.Builder] using dsl. */
fun ribbonView(context: Context, block: RibbonView.Builder.() -> Unit): RibbonView =
    RibbonView.Builder(context).apply(block).build()

/** RibbonView is advanced of [AppCompatTextView] for implement ribbon. */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class RibbonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.textViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr),
    RibbonInterface {

    private var _ribbonDrawable: Drawable? = null

    @ColorInt
    private var _ribbonBackgroundColor: Int = context.accentColor()

    private var _ribbonRotation: Int = 0

    @Dp
    private var _ribbonRadius: Float = 10f

    @Dp
    private var _paddingLeft: Float = 8f

    @Dp
    private var _paddingTop: Float = 4f

    @Dp
    private var _paddingRight: Float = 8f

    @Dp
    private var _paddingBottom: Float = 4f

    var ribbonDrawable: Drawable?
        get() = _ribbonDrawable
        set(value) {
            _ribbonDrawable = value
            updateRibbon()
        }

    var ribbonBackgroundColor: Int
        @ColorInt get() = _ribbonBackgroundColor
        set(@ColorInt value) {
            _ribbonBackgroundColor = value
            updateRibbon()
        }

    var ribbonRotation: Int
        get() = _ribbonRotation
        set(value) {
            _ribbonRotation = value
            updateRibbon()
        }

    var ribbonRadius: Float
        @Dp get() = _ribbonRadius
        set(@Dp value) {
            _ribbonRadius = value
            updateRibbon()
        }

    var paddingLeft: Float
        @Dp get() = _paddingLeft
        set(@Dp value) {
            _paddingLeft = value
            updateRibbon()
        }

    var paddingTop: Float
        @Dp get() = _paddingTop
        set(@Dp value) {
            _paddingTop = value
            updateRibbon()
        }

    var paddingRight: Float
        @Dp get() = _paddingRight
        set(@Dp value) {
            _paddingRight = value
            updateRibbon()
        }

    var paddingBottom: Float
        @Dp get() = _paddingBottom
        set(@Dp value) {
            _paddingBottom = value
            updateRibbon()
        }

    init {
        onCreate()
        when {
            attrs != null && defStyleAttr != android.R.attr.textViewStyle ->
                getAttrs(attrs, defStyleAttr)
            attrs != null -> getAttrs(attrs)
        }
    }

    private fun onCreate() {
        this.gravity = Gravity.CENTER
        this.background = GradientDrawable().apply {
            setColor(ribbonBackgroundColor)
            cornerRadius = ribbonRadius
        }
    }

    private fun getAttrs(attributeSet: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attributeSet,
            R.styleable.RibbonView
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
                R.styleable.RibbonView, defStyleAttr, 0)
        try {
            setTypeArray(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setTypeArray(typeArray: TypedArray) {
        _ribbonDrawable = typeArray.getDrawable(R.styleable.RibbonView_ribbon_drawable)
        _ribbonBackgroundColor =
            typeArray.getColor(R.styleable.RibbonView_ribbon_background_color, ribbonBackgroundColor)
        _ribbonRadius =
            typeArray.getDimension(R.styleable.RibbonView_ribbon_ribbonRadius, ribbonRadius)
        _ribbonRotation = typeArray.getInt(R.styleable.RibbonView_ribbon_rotation, ribbonRotation)
        _paddingLeft =
            typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_left, paddingLeft)
        _paddingTop = typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_top, paddingTop)
        _paddingRight =
            typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_right, paddingRight)
        _paddingBottom =
            typeArray.getDimension(R.styleable.RibbonView_ribbon_padding_bottom, paddingBottom)
    }

    /** initialize attributes by [RibbonView.Builder] */
    private fun onCreateByBuilder(builder: Builder) {
        _ribbonDrawable = builder.ribbonDrawable
        _ribbonBackgroundColor = builder.ribbonBackgroundColor
        _ribbonRadius = builder.ribbonRadius
        _ribbonRotation = builder.ribbonRotation
        _paddingLeft = builder.paddingLeft
        _paddingTop = builder.paddingTop
        _paddingRight = builder.paddingRight
        _paddingBottom = builder.paddingBottom
        text = builder.text
        textSize = builder.textSize
        setTextColor(builder.textColor)
        setTypeface(typeface, builder.textStyle)
        updateRibbon()
    }

    /** update [RibbonView] after finishing inflate. */
    override fun onFinishInflate() {
        super.onFinishInflate()
        updateRibbon()
    }

    /** update [RibbonView] by attributes. */
    override fun updateRibbon() {
        setPadding(
            paddingLeft.dp2px(resources),
            paddingTop.dp2px(resources),
            paddingRight.dp2px(resources),
            paddingBottom.dp2px(resources)
        )
        background = ribbonDrawable ?: GradientDrawable().apply {
            cornerRadius = ribbonRadius.dp2px(resources).toFloat()
            setColor(ribbonBackgroundColor)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            rotation(ribbonRotation)
        }
    }

    /** Builder class for creating [RibbonView]. */
    @RibbonDsl
    class Builder(val context: Context) {

        @JvmField
        var ribbonDrawable: Drawable? = null

        @JvmField @ColorInt
        var ribbonBackgroundColor: Int = ContextCompat.getColor(context, android.R.color.transparent)

        @JvmField
        var ribbonRotation: Int = 0

        @JvmField @Dp
        var ribbonRadius: Float = 10f

        @JvmField @Dp
        var paddingLeft: Float = 8f

        @JvmField @Dp
        var paddingTop: Float = 4f

        @JvmField @Dp
        var paddingRight: Float = 8f

        @JvmField @Dp
        var paddingBottom: Float = 4f

        @JvmField
        var text: CharSequence = ""

        @JvmField @ColorInt
        var textColor = Color.WHITE

        @JvmField @Sp
        var textSize = 12f

        @JvmField
        var textStyle = Typeface.NORMAL

        fun setRibbonDrawable(RibbonDrawable: Drawable?): Builder = apply {
            this.ribbonDrawable = RibbonDrawable
        }

        fun setRibbonDrawableResource(@DrawableRes drawable: Int): Builder = apply {
            this.ribbonDrawable = ContextCompat.getDrawable(this.context, drawable)
        }

        fun setRibbonBackgroundColor(@ColorInt color: Int): Builder = apply {
            this.ribbonBackgroundColor = color
        }

        fun setRibbonBackgroundColorResource(@ColorRes color: Int): Builder = apply {
            this.ribbonBackgroundColor = ContextCompat.getColor(this.context, color)
        }

        fun setRibbonRotation(value: Int): Builder = apply { this.ribbonRotation = value }
        fun setRibbonRadius(@Dp value: Float): Builder = apply { this.ribbonRadius = value }
        fun setPaddingLeft(@Dp value: Float): Builder = apply { this.paddingLeft = value }
        fun setPaddingTop(@Dp value: Float): Builder = apply { this.paddingTop = value }
        fun setPaddingRight(@Dp value: Float): Builder = apply { this.paddingRight = value }
        fun setPaddingBottom(@Dp value: Float): Builder = apply { this.paddingBottom = value }
        fun setText(value: CharSequence): Builder = apply { this.text = value }
        fun setTextColor(@ColorInt value: Int): Builder = apply { this.textColor = value }
        fun setTextSize(@Sp value: Float): Builder = apply { this.textSize = value }
        fun setTextStyle(value: Int): Builder = apply { this.textStyle = value }
        fun build(): RibbonView = RibbonView(
            context
        ).apply { onCreateByBuilder(this@Builder) }
    }
}
