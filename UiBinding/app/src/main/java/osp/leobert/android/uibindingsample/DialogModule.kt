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