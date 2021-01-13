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
            val bind = DataBindingUtil.bind<T>(view)
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