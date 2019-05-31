package cn.feng.constraintLayout2.helps

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.constraint.ConstraintHelper
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import cn.feng.constraintLayout2.utils.dp2Px
import cn.feng.constraintlayoutsample.R

class MetaballsDecorator @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {
    private lateinit var mContainer: ConstraintLayout
    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mPaint2: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL_AND_STROKE


        mPaint2.color = Color.BLUE
        mPaint2.style = Paint.Style.FILL_AND_STROKE
        mPaint2.strokeWidth = 10f

    }


    override fun updatePostLayout(container: ConstraintLayout) {
        mContainer = container
    }


    fun startAnim() {
        val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(5000)
        animator.addUpdateListener { animation ->
            val animatedFraction = animation.animatedFraction
            update(animatedFraction)
        }
        animator.start()
    }

    private fun update(fraction: Float) {
        val views = getViews(mContainer)
        var centerView = mContainer.findViewById<View>(R.id.img_directions_bike)
        for (referencedView in views) {
            if (referencedView.tag == "centerView") {
                continue
            }
            var translationXDistance = (centerView.left + centerView.width / 2) - (referencedView.left + referencedView.width / 2)
            var translationYDistance = (centerView.top + centerView.height / 2) - (referencedView.top + referencedView.height / 2)

            referencedView.translationX = translationXDistance * fraction
            referencedView.translationY = translationYDistance * fraction

            referencedView.scaleX = 1 - fraction
            referencedView.scaleY = 1 - fraction

            invalidate()
        }
    }


    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        val views = getViews(mContainer)
        for (referencedView in views) {
            canvas.drawCircle(((referencedView.left + referencedView.right) / 2 + referencedView.translationX),
                    ((referencedView.top + referencedView.bottom) / 2 + referencedView.translationY),
                    Math.hypot((referencedView.width * referencedView.scaleX / 2).toDouble(), (referencedView.height * referencedView.scaleY / 2).toDouble()).toFloat()+dp2Px(10f),
                    mPaint)
        }
    }


}