package osp.leobert.android.uibinding.delegate

import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.onDestroyOnce
import kotlin.properties.ReadOnlyProperty

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding.delegate </p>
 * <p><b>Classname:</b> LifeCycledDelegate </p>
 * Created by leobert on 2021/1/11.
 */
abstract class LifeCycledBindingDelegate<F,T>(lifecycle: Lifecycle): ReadOnlyProperty<F,T> {

    protected var property: T? = null

    init {
        lifecycle.onDestroyOnce { destroy() }
    }

    protected open fun destroy() {
        property = null
    }
}