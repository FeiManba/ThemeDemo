package com.huifeideyema.themedemo

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

class ThemeUtils {
    companion object {
        /**
         * 检测build 版本 处理浅色状态栏 font 显示问题
         * @param activity
         * @param color
         */
        fun changeBuildStatusBarMode(activity: Activity, color: Int) {
            if (isLightColor(color)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            } else {
                activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }

        /**
         * 判断颜色是不是亮色
         *
         * @param color
         * @return
         * @from
         */
        private fun isLightColor(@ColorInt color: Int): Boolean {
            return ColorUtils.calculateLuminance(color) >= 0.5
        }

        /**
         * 获取状态栏高度
         */
        fun getStatusBarHeight(context: Context): Int {
            val resources = context.resources
            val identifier = resources.getIdentifier("status_bar_height", "dimen", "android")
            return if (identifier > 0) {
                resources.getDimensionPixelSize(identifier)
            } else 0
        }
    }
}