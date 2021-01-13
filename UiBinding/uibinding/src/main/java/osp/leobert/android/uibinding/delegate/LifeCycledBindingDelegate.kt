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