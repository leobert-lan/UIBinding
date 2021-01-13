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

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import osp.leobert.android.uibinding.ViewProvider
import osp.leobert.android.uibinding.bindClick
import osp.leobert.android.uibinding.dataBinding
import osp.leobert.android.uibindingsample.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() ,ViewProvider{
    val binding by dataBinding<ActivityMainBinding>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.hello.text = "fragment"
        binding.hello.bindClick {

        }
    }

    override fun provide(): View {
        return window.decorView.findViewById(R.id.ll_root)
    }
}