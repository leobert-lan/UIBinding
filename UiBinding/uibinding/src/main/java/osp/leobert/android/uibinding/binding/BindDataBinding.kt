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