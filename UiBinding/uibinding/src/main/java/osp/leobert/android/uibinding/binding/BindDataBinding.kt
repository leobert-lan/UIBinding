package osp.leobert.android.uibinding.binding

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.LayoutInflaterProvider
import osp.leobert.android.uibinding.delegate.LifeCycledBindingDelegate
import kotlin.reflect.KProperty

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding.binding </p>
 * <p><b>Classname:</b> BindDataBinding </p>
 * Created by leobert on 2021/1/11.
 */
class BindDataBinding<T : ViewDataBinding>(
    private val targetClazz: Class<T>,
    private val inflaterProvider: LayoutInflaterProvider,
    @LayoutRes val resId: Int,
    lifecycle: Lifecycle,
    private var onBind: (T.() -> Unit)?
) : LifeCycledBindingDelegate<Any, T>(lifecycle) {

    override fun getValue(thisRef: Any, property: KProperty<*>): T {

        return this.property ?: let {

            val layoutInflater = inflaterProvider.provide()
            val bind = DataBindingUtil.bind<T>(layoutInflater.inflate(resId, null))
                ?: throw IllegalStateException(
                    "could not create binding ${targetClazz.name} by id $resId," +
                            " given name: ${layoutInflater.context.resources.getResourceEntryName(resId)}"
                )
            return bind.apply {
                this@BindDataBinding.property = this
                onBind?.invoke(this)
                onBind = null
            }
        }

    }
}