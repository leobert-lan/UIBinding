package osp.leobert.android.uibinding

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding </p>
 * <p><b>Classname:</b> LifecycleExts </p>
 * Created by leobert on 2021/1/11.
 */


internal class OnDestroyObserver(var lifecycle: Lifecycle?, val destroyed: () -> Unit) :
    LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        val lifecycleState = source.lifecycle.currentState
        if (lifecycleState == Lifecycle.State.DESTROYED) {
            destroyed()
            lifecycle?.apply {
                removeObserver(this@OnDestroyObserver)
                lifecycle = null
            }
        }
    }
}

fun Lifecycle.onDestroyOnce(destroyed: () -> Unit) {
    addObserver(OnDestroyObserver(this, destroyed))
}