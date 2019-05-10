package cn.wj.base.android.ui.popup

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

/**
 * PopupWindow 基类，封装了一些兼容性处理
 */
abstract class CommonBasePopupWindow<DB : ViewDataBinding>(open val mContext: AppCompatActivity) : PopupWindow() {

    protected lateinit var mBinding: DB

    override fun showAsDropDown(anchor: View?) {
        if (anchor == null) {
            return
        }
        // 处理高版本显示问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val rect = Rect()
            anchor.getGlobalVisibleRect(rect)
            val h = anchor.resources.displayMetrics.heightPixels - rect.bottom
            height = h
        }
        super.showAsDropDown(anchor)
    }

    /**
     * 设置显示在 View 的上方(以 View 的左边距为开始位置)
     *
     * @param v 要显示在上方的 View
     */
    fun showAsUp(v: View) {
        //获取需要在其上方显示的控件的位置信息
        val location = IntArray(2)
        v.getLocationOnScreen(location)
        //获取自身的长宽高
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val popupHeight = contentView.measuredHeight
        //在控件上方显示
        showAtLocation(v, Gravity.NO_GRAVITY, 0, location[1] - popupHeight)
    }

    /**
     * 在屏幕下方显示
     *
     * @param activity [AppCompatActivity] 对象
     */
    open fun showAtBottom(activity: AppCompatActivity = mContext) {

        var height = 0

        val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        val realSize = Point()
        display.getSize(size)
        display.getRealSize(realSize)
        val isSupportNavBar = realSize.y != size.y || realSize.x != size.x

        if (isSupportNavBar) {
            // 支持虚拟按键
            val visibility = activity.window.decorView.systemUiVisibility
            val isNavBarVisible = visibility and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION == 0
            if (isNavBarVisible) {
                // 显示导航栏
                val res = Resources.getSystem()
                val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
                height = if (resourceId != 0) {
                    res.getDimensionPixelSize(resourceId)
                } else {
                    0
                }
            }
        }
        showAtLocation(activity.window.decorView, Gravity.BOTTOM, 0, height)
    }
}