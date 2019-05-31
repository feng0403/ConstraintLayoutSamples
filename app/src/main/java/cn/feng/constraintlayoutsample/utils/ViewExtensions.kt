package cn.feng.constraintLayout2.utils

import android.content.res.Resources
import android.text.Spannable
import android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE
import android.util.Log
import android.util.TypedValue
import android.view.View


inline fun View.onLayoutChange(crossinline action: () -> Unit) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            removeOnLayoutChangeListener(this)
            action()
        }

    })
}

inline fun <reified T> T.logi(message: String) = Log.i(T::class.java.simpleName, message)


inline operator fun Spannable.plusAssign(span: Any) =
        setSpan(span, 0, length, SPAN_INCLUSIVE_EXCLUSIVE)


inline fun <reified T>  T.dp2Px(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)

}

