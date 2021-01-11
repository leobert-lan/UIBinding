package osp.leobert.android.uibinding.binding

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.ViewProvider
import osp.leobert.android.uibinding.delegate.LifeCycledBindingDelegate
import kotlin.reflect.KProperty

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding.binding </p>
 * <p><b>Classname:</b> FindDataBinding </p>
 * Created by leobert on 2021/1/11.
 */
class FindDataBinding<T : ViewDataBinding>(
    private val targetClazz: Class<T>,
    private val viewProvider: ViewProvider,
    lifecycle: Lifecycle,
    private var onBind: (T.() -> Unit)?
) : LifeCycledBindingDelegate<Any, T>(lifecycle) {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {

        return this.property ?: let {
            val view = viewProvider.provide()
            val bind = DataBindingUtil.findBinding<T>(view)
                ?: throw IllegalStateException(
                    "could not find binding ${targetClazz.name}"
                )
            return bind.apply {
                this@FindDataBinding.property = this
                onBind?.invoke(this)
                onBind = null
            }
        }
    }
}