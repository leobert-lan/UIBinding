package osp.leobert.android.uibinding.binding

import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.ViewProvider
import osp.leobert.android.uibinding.delegate.LifeCycledBindingDelegate
import kotlin.reflect.KProperty

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding.binding </p>
 * <p><b>Classname:</b> BindView </p>
 * Created by leobert on 2021/1/11.
 */
class BindView<T:View>(
    private val targetClazz: Class<T>,
    private val rootViewProvider: ViewProvider,
    @IdRes val resId: Int,
    lifecycle: Lifecycle,
    private var onBind: (T.() -> Unit)?
):LifeCycledBindingDelegate<Any,T>(lifecycle) {


    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return this.property ?: let {

            val rootView = rootViewProvider.provide()
            val v = rootView.findViewById<T>(resId)
                ?: throw IllegalStateException(
                    "could not findViewById by id $resId," +
                            " given name: ${rootView.context.resources.getResourceEntryName(resId)}"
                )
            return v.apply {
                this@BindView.property = this
                onBind?.invoke(this)
                onBind = null
            }
        }
    }
}