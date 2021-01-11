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