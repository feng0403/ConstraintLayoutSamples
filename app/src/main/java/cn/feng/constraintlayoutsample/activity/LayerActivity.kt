package cn.feng.constraintLayout2.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.feng.constraintlayoutsample.R
import kotlinx.android.synthetic.main.layout_layer.*

class LayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_layer)

        button8.setOnClickListener {

            val anim = ValueAnimator.ofFloat(0f, 360f)
            anim.duration = 300
            anim.addUpdateListener { animation ->
                val angle = animation.animatedValue as Float
                layer.rotation = angle
                layer.scaleX = 1 + (180 - Math.abs(angle - 180)) / 20f
                layer.scaleY = 1 + (180 - Math.abs(angle - 180)) / 20f


                var shift_x = 500 * Math.sin(Math.toRadians((angle * 5).toDouble())).toFloat()
                var shift_y = 500 * Math.sin(Math.toRadians((angle * 7).toDouble())).toFloat()
                layer.translationX = shift_x
                layer.translationY = shift_y
            }
            anim.duration = 4000
            anim.start()
        }


    }
}
