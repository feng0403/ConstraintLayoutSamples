package cn.feng.constraintLayout2.helps

import android.content.Context
import android.os.Build
import android.support.constraint.ConstraintLayout
import android.support.constraint.helper.Layer
import android.util.AttributeSet
import android.view.ViewAnimationUtils

class CircularRevealHelper2 @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Layer(context, attrs, defStyleAttr) {

//    override fun updatePostLayout(container: ConstraintLayout) {
//        super.updatePostLayout(container)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            var centerPoint = calculateCenterPoint(container)
//            val anim = ViewAnimationUtils.createCircularReveal(this, centerPoint.x.toInt(),
//                    centerPoint.y.toInt(), 0f,
//                    Math.hypot(centerPoint.x.toDouble(), centerPoint.y.toDouble()).toFloat())
//            anim.duration = 3000
//            anim.start()
//        }
//    }


    override fun updatePostLayout(container: ConstraintLayout) {
        super.updatePostLayout(container)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val anim = ViewAnimationUtils.createCircularReveal(this, (left + right) / 2,
                    (top + bottom) / 2, 0f,
                    Math.hypot(((left + right) / 2 - left).toDouble(), ((top + bottom) / 2 - top).toDouble()).toFloat())
            anim.duration = 3000
            anim.start()
        }
    }


//    private fun calculateCenterPoint(container: ConstraintLayout?): PointF {
//        var leftMin: Float = Float.MAX_VALUE
//        var topMin = Float.MAX_VALUE
//        var rightMax = Float.MIN_VALUE
//        var bottomMax = Float.MIN_VALUE
//
//
//        val views = getViews(container)
//        for (view in views) {
//            leftMin = if (view.left < leftMin) view.left.toFloat() else leftMin
//            topMin = if (view.top < topMin) view.top.toFloat() else topMin
//            rightMax = if (view.right > rightMax) view.right.toFloat() else rightMax
//            bottomMax = if (view.bottom > bottomMax) view.bottom.toFloat() else bottomMax
//        }
//        Log.d("FlyinHelper2", "leftMin:$leftMin topMin:$topMin rightMax:$rightMax bottomMax:$bottomMax")
//
//        return PointF((rightMax + leftMin) / 2, (bottomMax + topMin) / 2)
//    }

}