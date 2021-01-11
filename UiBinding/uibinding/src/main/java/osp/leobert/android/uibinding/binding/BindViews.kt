package osp.leobert.android.uibinding.binding

import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.ViewProvider
import osp.leobert.android.uibinding.delegate.LifeCycledBindingDelegate
import kotlin.reflect.KProperty

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding.binding </p>
 * <p><b>Classname:</b> BindViews </p>
 * Created by leobert on 2021/1/11.
 */
class BindViews<T : View>(
    private val targetClazz: Class<T>,
    private val rootViewProvider: ViewProvider,
    @IdRes val resIds: List<Int>,
    lifecycle: Lifecycle,
    private var onBind: (List<T>.() -> Unit)?
) : LifeCycledBindingDelegate<Any, MutableList<T>>(lifecycle) {

    override fun getValue(thisRef: Any, property: KProperty<*>): MutableList<T> {
        return this.property ?: let {

            val rootView = rootViewProvider.provide()
            val views = arrayListOf<T>()

            resIds.forEach { resId ->

                val v = rootView.findViewById<T>(resId)
                    ?: throw IllegalStateException(
                        "could not findViewById by id $resId," +
                                " given name: ${
                                    rootView.context.resources.getResourceEntryName(
                                        resId
                                    )
                                }"
                    )
                views.add(v)
            }

            return views.apply {
                this@BindViews.property = this
                onBind?.invoke(this)
                onBind = null
            }
        }
    }

    override fun destroy() {
        property?.clear()
        super.destroy()
    }
}