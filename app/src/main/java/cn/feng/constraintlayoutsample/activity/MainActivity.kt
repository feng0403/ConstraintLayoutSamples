package cn.feng.constraintLayout2.activity

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintProperties
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import cn.feng.constraintlayoutsample.R
import cn.feng.constraintlayoutsample.adapter.BaseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mData = arrayOf("LayerActivity", "CircularRevealHelper", "FlyInHelper", "ComposeMultipleHelper")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv.adapter = object : BaseRecyclerAdapter<String>(mData.toList()) {
            override fun getItemLayoutId(viewType: Int): Int {
                return android.R.layout.simple_list_item_1
            }

            override fun convert(viewHolder: BaseVH, viewType: Int, data: String, position: Int) {
                viewHolder.setText(android.R.id.text1, data)
                viewHolder.itemView.setOnClickListener {
                    when (position) {
                        0 -> startActivity(Intent(this@MainActivity, LayerActivity::class.java))
                        1 -> startActivity(Intent(this@MainActivity, CircularRevealHelperActivity::class.java))
                        2 -> startActivity(Intent(this@MainActivity, FlyInHelperActivity::class.java))
                        3 -> startActivity(Intent(this@MainActivity, ComposeMultipleHelperActivity::class.java))
                    }
                }
            }

        }
    }
}
