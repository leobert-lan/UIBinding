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

package osp.leobert.android.uibindingsample

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.*
import osp.leobert.android.uibindingsample.databinding.ActivityMainBinding

/**
 * <p><b>Package:</b> osp.leobert.android.uibindingsample </p>
 * <p><b>Classname:</b> Holder </p>
 * Created by leobert on 2021/1/11.
 */
class Holder(context: Context, lifecycle: Lifecycle, viewProvider: ViewProvider) {
    val binding: ActivityMainBinding by dataBinding(
        context.inflaterProvider(),
        R.layout.activity_main,
        lifecycle
    )

    val tvHello by bindView<TextView>(viewProvider, R.id.hello, lifecycle) {
        bindClick { changeText() }
    }

    val tvHellos by bindViews<TextView>(
        viewProvider,
        arrayListOf(R.id.hello1, R.id.hello2),
        lifecycle
    ) {
        this.forEach {
            it.bindClick { tv ->
                Toast.makeText(tv.context, it.text.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeText() {
        tvHello.text = "text changed!"
    }

    fun initAll() {
        binding
        tvHello
        tvHellos
    }


}