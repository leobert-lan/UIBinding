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

import android.app.Dialog
import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.ViewProvider
import java.lang.IllegalStateException

/**
 * <p><b>Package:</b> osp.leobert.android.uibindingsample </p>
 * <p><b>Classname:</b> DialogModule </p>
 * Created by leobert on 2021/1/11.
 */
object DialogModule {
    fun provide(context: Context, lifecycle: Lifecycle): Dialog {

        val dialog = AlertDialog.Builder(context)
            .create()

        val holder = Holder(
            context, lifecycle, object : ViewProvider {
                override fun provide(): View {
                    return dialog.window?.decorView ?: throw IllegalStateException("")
                }
            }
        )
        dialog.setOnShowListener {
            holder.initAll()
        }

        dialog.setView(holder.binding.root)

        return dialog
    }
}