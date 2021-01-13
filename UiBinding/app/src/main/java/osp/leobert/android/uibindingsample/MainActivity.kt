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

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import osp.leobert.android.uibinding.ViewProvider
import osp.leobert.android.uibinding.bindClick
import osp.leobert.android.uibinding.bindView
import osp.leobert.android.uibinding.dataBinding
import osp.leobert.android.uibindingsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by dataBinding(R.layout.activity_main) {
        setContentView(this.root)
    }
    val holder: Holder by lazy {
        Holder(
                context = this,
                lifecycle = this.lifecycle,
                viewProvider = object : ViewProvider {
                    override fun provide(): View {
                        return this@MainActivity.window.decorView
                    }
                }
        )
    }

    val tvHello by bindView<TextView>(R.id.dialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding.hello.text = "hello2"

        //虽然设置了绑定，但是并没有setContentView，所以并不会表现到UI上
        holder.binding.hello.text = "changed?"

        //如果不访问一次属性，就不会被初始化，这是一个需要注意的点
        holder.tvHello
        holder.tvHellos

        tvHello.bindClick { DialogModule.provide(this, this.lifecycle).show() }
        binding.root.bindClick {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }
}