package com.huifeideyema.themedemo

import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val params: FrameLayout.LayoutParams = tool_bar.layoutParams as FrameLayout.LayoutParams
        params.setMargins(0, ThemeUtils.Companion.getStatusBarHeight(this), 0, 0)
        tool_bar.layoutParams = params
    }

    var stateChangeListener: AppBarStateChangeListener = object : AppBarStateChangeListener() {
        override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
            when (state) {
                State.COLLAPSED -> {
                    ThemeUtils.changeBuildStatusBarMode(this@MainActivity, Color.WHITE)
                }
                State.EXPANDED -> {
                    ThemeUtils.changeBuildStatusBarMode(this@MainActivity, Color.TRANSPARENT)
                }
                else -> {
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        app_bar.addOnOffsetChangedListener(stateChangeListener)
    }

    override fun onPause() {
        super.onPause()
        app_bar.removeOnOffsetChangedListener(stateChangeListener)
    }

}
