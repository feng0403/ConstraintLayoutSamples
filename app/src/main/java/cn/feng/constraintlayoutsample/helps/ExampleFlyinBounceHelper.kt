package cn.feng.constraintLayout2.helps

import android.animation.ObjectAnimator
import android.content.Context
import android.support.constraint.ConstraintHelper
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class ExampleFlyinBounceHelper @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintHelper(context, attrs, defStyleAttr) {
    private var mContainer: ConstraintLayout? = null

    override fun updatePreLayout(container: ConstraintLayout) {
        if (mContainer !== container) {
            val views = getViews(container)
            for (view in views) {
                val animator = ObjectAnimator.ofFloat(view, "translationX", -2000f, 0f).setDuration(1000)
                animator.start()
            }
        }
        mContainer = container
    }

}