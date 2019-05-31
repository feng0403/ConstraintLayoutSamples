package cn.feng.constraintLayout2.helps

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.PointF
import android.support.constraint.ConstraintLayout
import android.support.constraint.helper.Layer
import android.util.AttributeSet
import android.util.Log

class Flyin2Helper @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Layer(context, attrs, defStyleAttr) {


    override fun updatePostLayout(container: ConstraintLayout) {
        Log.d("FlyinHelper", "updatePostLayout")
        super.updatePostLayout(container)
        val centerPoint = PointF(((left + right) / 2).toFloat(), ((top + bottom) / 2).toFloat())


        val animator = ValueAnimator.ofFloat(0f, 1f).setDuration(1000)
        animator.addUpdateListener { animation ->
            val animatedFraction = animation.animatedFraction
            updateTranslation(centerPoint, animatedFraction, container)
        }
        animator.start()
    }

    private fun updateTranslation(centerPoint: PointF, animatedFraction: Float, container: ConstraintLayout) {

        val views = getViews(container)
        for (view in views) {

            val viewCenterX = (view.left + view.right) / 2
            val viewCenterY = (view.top + view.bottom) / 2


            val startTranslationX = if (viewCenterX < centerPoint.x) -2000f else 2000f
            val startTranslationY = if (viewCenterY < centerPoint.y) -2000f else 2000f


            view.translationX = (1 - animatedFraction) * startTranslationX
            view.translationY = (1 - animatedFraction) * startTranslationY
        }
    }
}