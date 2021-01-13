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

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding </p>
 * <p><b>Classname:</b> LifecycleExts </p>
 * Created by leobert on 2021/1/11.
 */


internal class OnDestroyObserver(var lifecycle: Lifecycle?, val destroyed: () -> Unit) :
    LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        val lifecycleState = source.lifecycle.currentState
        if (lifecycleState == Lifecycle.State.DESTROYED) {
            destroyed()
            lifecycle?.apply {
                removeObserver(this@OnDestroyObserver)
                lifecycle = null
            }
        }
    }
}

fun Lifecycle.onDestroyOnce(destroyed: () -> Unit) {
    addObserver(OnDestroyObserver(this, destroyed))
}