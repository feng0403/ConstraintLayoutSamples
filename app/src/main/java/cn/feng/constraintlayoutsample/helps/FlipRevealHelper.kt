package cn.feng.constraintLayout2.helps

import android.animation.ObjectAnimator
import android.content.Context
import android.support.constraint.ConstraintHelper
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet


class FlipRevealHelper @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {


    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)
        val views = getViews(container)
        for (view in views) {
            val animator = ObjectAnimator.ofFloat(view, "rotationY", 90f, 0f).setDuration(3000)
            animator.start()
        }
    }
}