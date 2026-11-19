package com.example.myrecipestinorua

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.math.abs

// default values of the XML attributes
private const val DEFAULT_SCORE_MAX = 100f
private const val DEFAULT_SCORE_VALUE = 75f
private const val DEFAULT_SCORE_LABEL = ""
private const val DEFAULT_SCORE_COLOR = Color.GREEN

class NutriScoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var scoreMax: Float = DEFAULT_SCORE_MAX
    var scoreValue: Float = DEFAULT_SCORE_VALUE
    var scoreLabel: String = DEFAULT_SCORE_LABEL
    var scoreColor: Int = DEFAULT_SCORE_COLOR

    // created once, since onDraw() is called at each refresh
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = context.resources.getDimension(R.dimen.nutri_score_view_label)
        typeface = Typeface.create(null as String?, Typeface.BOLD)
    }
    // stroke width of the frame
    private val so = 4f
    private val fontMetrics = paint.fontMetrics
    private val textHeight = abs(fontMetrics.ascent + fontMetrics.descent)

    init {
        context.withStyledAttributes(attrs, R.styleable.NutriScoreView) {
            scoreMax = getFloat(R.styleable.NutriScoreView_scoreMax, DEFAULT_SCORE_MAX)
            scoreValue = getFloat(R.styleable.NutriScoreView_scoreValue, DEFAULT_SCORE_VALUE)
            scoreColor = getColor(R.styleable.NutriScoreView_scoreColor, DEFAULT_SCORE_COLOR)
            scoreLabel = getString(R.styleable.NutriScoreView_scoreLabel) ?: DEFAULT_SCORE_LABEL
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val margin = 4 * so

        // the bar takes the whole width, unless the parent gives no constraint
        val desiredWidth = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            paddingLeft + paddingRight + margin +
                    paint.measureText(" $scoreLabel") + paint.measureText("100 % ")
        } else {
            MeasureSpec.getSize(widthMeasureSpec).toFloat()
        }
        // one line of text
        val desiredHeight = paddingTop + paddingBottom + margin +
                fontMetrics.descent - fontMetrics.ascent

        setMeasuredDimension(
            resolveSize(desiredWidth.toInt(), widthMeasureSpec),
            resolveSize(desiredHeight.toInt(), heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val percent = if (scoreMax > 0) (scoreValue / scoreMax).coerceIn(0f, 1f) else 0f

        // colored bar
        paint.style = Paint.Style.FILL
        paint.color = scoreColor
        canvas.drawRect(
            paddingLeft.toFloat(),
            paddingTop.toFloat(),
            paddingLeft + (width - paddingLeft - paddingRight) * percent,
            height.toFloat() - paddingBottom,
            paint
        )

        // frame
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = so * 1.5f
        paint.color = Color.BLACK
        canvas.drawRect(
            paddingLeft + so,
            paddingTop + so,
            width - paddingRight - so,
            height - paddingBottom - so,
            paint
        )

        // label on the left, percentage on the right
        val textY = (paddingTop + height - paddingBottom + textHeight) * 0.5f
        paint.style = Paint.Style.FILL
        paint.color = Color.DKGRAY
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText(" $scoreLabel", paddingLeft.toFloat(), textY, paint)
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText("${(100 * percent).toInt()} % ", width - paddingRight.toFloat(), textY, paint)
    }
}
