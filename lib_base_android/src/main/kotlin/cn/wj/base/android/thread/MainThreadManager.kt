package cn.wj.base.android.thread

import android.os.Handler
import android.os.Looper

/**
 * 主线程管理类
 */
internal object MainThreadManager {

    private val mLock = Any()

    @Volatile
    private var mMainHandler: Handler? = null

    /** 是否是主线程 */
//    val isMainThread: Boolean
//        get() = Looper.getMainLooper().thread === Thread.currentThread()

    /**
     * 在主线程执行
     */
    fun postToMainThread(runnable: Runnable) {
        if (mMainHandler == null) {
            synchronized(mLock) {
                if (mMainHandler == null) {
                    mMainHandler = Handler(Looper.getMainLooper())
                }
            }
        }
        mMainHandler!!.post(runnable)
    }

    /**
     * 在主线程执行
     */
    fun postToMainThread(runnable: () -> Unit) {
        postToMainThread(Runnable { runnable.invoke() })
    }
}

/**
 * 在主线程执行
 */
fun runOnMainThread(runnable: Runnable) {
    MainThreadManager.postToMainThread(runnable)
}

/**
 * 在主线程执行
 */
fun runOnMainThread(runnable: () -> Unit) {
    MainThreadManager.postToMainThread(runnable)
}
