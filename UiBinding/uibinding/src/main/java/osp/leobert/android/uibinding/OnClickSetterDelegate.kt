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

package osp.leobert.android.uibinding

import android.view.View

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding </p>
 * <p><b>Classname:</b> OnClickSetterDelegate </p>
 * Created by leobert on 2021/1/11.
 */
object OnClickSetterDelegate {

    var delegate: (View, (View.() -> Unit)?) -> Unit = { view: View, listener: ((View) -> Unit)? ->
        view.setOnClickListener(listener)
    }

    fun setOnClickListener(view: View, listener: ((View) -> Unit)?) {
        delegate.invoke(view, listener)
    }
}