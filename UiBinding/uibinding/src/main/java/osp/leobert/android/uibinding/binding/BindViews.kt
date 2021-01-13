/*
 *    Copyright [2021-2031] [Leobert]
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

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