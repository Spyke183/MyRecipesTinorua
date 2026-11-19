package com.example.myrecipestinorua

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes

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

    init {
        context.withStyledAttributes(attrs, R.styleable.NutriScoreView) {
            scoreMax = getFloat(R.styleable.NutriScoreView_scoreMax, DEFAULT_SCORE_MAX)
            scoreValue = getFloat(R.styleable.NutriScoreView_scoreValue, DEFAULT_SCORE_VALUE)
            scoreColor = getColor(R.styleable.NutriScoreView_scoreColor, DEFAULT_SCORE_COLOR)
            scoreLabel = getString(R.styleable.NutriScoreView_scoreLabel) ?: DEFAULT_SCORE_LABEL
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}
