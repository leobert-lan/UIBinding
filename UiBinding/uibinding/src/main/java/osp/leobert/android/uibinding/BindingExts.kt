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

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.activity.ComponentActivity
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import osp.leobert.android.uibinding.binding.BindDataBinding
import osp.leobert.android.uibinding.binding.BindView
import osp.leobert.android.uibinding.binding.BindViews
import osp.leobert.android.uibinding.binding.FindDataBinding

/**
 * <p><b>Package:</b> osp.leobert.android.uibinding </p>
 * <p><b>Classname:</b> BindingExts </p>
 * Created by leobert on 2021/1/11.
 */
fun Context.inflaterProvider() = object : LayoutInflaterProvider {
    override fun provide(): LayoutInflater {
        return LayoutInflater.from(this@inflaterProvider)
    }
}

fun View.bindClick(listener: ((View) -> Unit)?) =
    OnClickSetterDelegate.setOnClickListener(this, listener)

//region dataBinding

inline fun <reified T : ViewDataBinding> ComponentActivity.dataBinding(viewProvider: ViewProvider) =
    FindDataBinding<T>(
        targetClazz = T::class.java,
        viewProvider = viewProvider,
        lifecycle = this.lifecycle,
        onBind = null
    )

inline fun <reified T : ViewDataBinding> ComponentActivity.dataBinding(
    viewProvider: ViewProvider,
    noinline onBind: (T.() -> Unit)?
) =
    FindDataBinding<T>(
        targetClazz = T::class.java,
        viewProvider = viewProvider,
        lifecycle = this.lifecycle,
        onBind = onBind
    )

inline fun <reified T : ViewDataBinding> Fragment.dataBinding(viewProvider: ViewProvider) =
    FindDataBinding<T>(
        targetClazz = T::class.java,
        viewProvider = viewProvider,
        lifecycle = this.lifecycle,
        onBind = null
    )

inline fun <reified T : ViewDataBinding> Fragment.dataBinding(
    viewProvider: ViewProvider,
    noinline onBind: (T.() -> Unit)?
) =
    FindDataBinding<T>(
        targetClazz = T::class.java,
        viewProvider = viewProvider,
        lifecycle = this.lifecycle,
        onBind = onBind
    )

inline fun <reified T : ViewDataBinding> Any.dataBinding(
    viewProvider: ViewProvider,
    lifecycle: Lifecycle
) =
    FindDataBinding<T>(
        targetClazz = T::class.java,
        viewProvider = viewProvider,
        lifecycle = lifecycle,
        onBind = null
    )

inline fun <reified T : ViewDataBinding> Any.dataBinding(
    viewProvider: ViewProvider,
    lifecycle: Lifecycle,
    noinline onBind: (T.() -> Unit)?
) =
    FindDataBinding<T>(
        targetClazz = T::class.java,
        viewProvider = viewProvider,
        lifecycle = lifecycle,
        onBind = onBind
    )
//endregion

//region dataBinding

inline fun <reified T : ViewDataBinding> ComponentActivity.dataBinding(@LayoutRes resId: Int) =
    BindDataBinding<T>(
        targetClazz = T::class.java,
        inflaterProvider = object : LayoutInflaterProvider {
            override fun provide(): LayoutInflater {
                return LayoutInflater.from(this@dataBinding)
            }
        },
        resId = resId,
        lifecycle = this.lifecycle,
        onBind = null
    )

inline fun <reified T : ViewDataBinding> ComponentActivity.dataBinding(
    @LayoutRes resId: Int,
    noinline onBind: (T.() -> Unit)?
) =
    BindDataBinding<T>(
        targetClazz = T::class.java,
        inflaterProvider = object : LayoutInflaterProvider {
            override fun provide(): LayoutInflater {
                return LayoutInflater.from(this@dataBinding)
            }
        },
        resId = resId,
        lifecycle = this.lifecycle,
        onBind = onBind
    )

inline fun <reified T : ViewDataBinding> Fragment.dataBinding(@LayoutRes resId: Int) =
    BindDataBinding<T>(
        targetClazz = T::class.java,
        inflaterProvider = object : LayoutInflaterProvider {
            override fun provide(): LayoutInflater {
                return LayoutInflater.from(this@dataBinding.requireContext())
            }
        },
        resId = resId,
        lifecycle = this.lifecycle,
        onBind = null
    )

inline fun <reified T : ViewDataBinding> Fragment.dataBinding(
    @LayoutRes resId: Int,
    noinline onBind: (T.() -> Unit)?
) =
    BindDataBinding<T>(
        targetClazz = T::class.java,
        inflaterProvider = object : LayoutInflaterProvider {
            override fun provide(): LayoutInflater {
                return LayoutInflater.from(this@dataBinding.requireContext())
            }
        },
        resId = resId,
        lifecycle = this.lifecycle,
        onBind = onBind
    )

inline fun <reified T : ViewDataBinding> Any.dataBinding(
    inflaterProvider: LayoutInflaterProvider,
    @LayoutRes resId: Int,
    lifecycle: Lifecycle
) =
    BindDataBinding<T>(
        targetClazz = T::class.java,
        inflaterProvider = inflaterProvider,
        resId = resId,
        lifecycle = lifecycle,
        onBind = null
    )

inline fun <reified T : ViewDataBinding> Any.dataBinding(
    inflaterProvider: LayoutInflaterProvider,
    @LayoutRes resId: Int,
    lifecycle: Lifecycle,
    noinline onBind: (T.() -> Unit)?
) =
    BindDataBinding<T>(
        targetClazz = T::class.java,
        inflaterProvider = inflaterProvider,
        resId = resId,
        lifecycle = lifecycle,
        onBind = onBind
    )
//endregion

//region bindView

inline fun <reified T : View> ComponentActivity.bindView(@LayoutRes resId: Int) =
    BindView<T>(
        targetClazz = T::class.java,
        rootViewProvider = object : ViewProvider {
            override fun provide(): View {
                return this@bindView.window.decorView
            }
        },
        resId = resId,
        lifecycle = this.lifecycle,
        onBind = null
    )

inline fun <reified T : View> ComponentActivity.bindView(
    @IdRes resId: Int,
    noinline onBind: (T.() -> Unit)?
) =
    BindView<T>(
        targetClazz = T::class.java,
        rootViewProvider = object : ViewProvider {
            override fun provide(): View {
                return this@bindView.window.decorView
            }
        },
        resId = resId,
        lifecycle = this.lifecycle,
        onBind = onBind
    )

inline fun <reified T : View> Any.bindView(
    rootViewProvider: ViewProvider,
    @IdRes resId: Int,
    lifecycle: Lifecycle
) =
    BindView<T>(
        targetClazz = T::class.java,
        rootViewProvider = rootViewProvider,
        resId = resId,
        lifecycle = lifecycle,
        onBind = null
    )

inline fun <reified T : View> Any.bindView(
    rootViewProvider: ViewProvider,
    @LayoutRes resId: Int,
    lifecycle: Lifecycle,
    noinline onBind: (T.() -> Unit)?
) =
    BindView<T>(
        targetClazz = T::class.java,
        rootViewProvider = rootViewProvider,
        resId = resId,
        lifecycle = lifecycle,
        onBind = onBind
    )
//endregion

//region bindViews

inline fun <reified T : View> ComponentActivity.bindViews(resIds: List<Int>) =
    BindViews<T>(
        targetClazz = T::class.java,
        rootViewProvider = object : ViewProvider {
            override fun provide(): View {
                return this@bindViews.window.decorView
            }
        },
        resIds = resIds,
        lifecycle = this.lifecycle,
        onBind = null
    )

inline fun <reified T : View> ComponentActivity.bindViews(
    resIds: List<Int>,
    noinline onBind: (List<T>.() -> Unit)?
) =
    BindViews<T>(
        targetClazz = T::class.java,
        rootViewProvider = object : ViewProvider {
            override fun provide(): View {
                return this@bindViews.window.decorView
            }
        },
        resIds = resIds,
        lifecycle = this.lifecycle,
        onBind = onBind
    )

inline fun <reified T : View> Any.bindViews(
    rootViewProvider: ViewProvider,
    resId: List<Int>,
    lifecycle: Lifecycle
) =
    BindViews<T>(
        targetClazz = T::class.java,
        rootViewProvider = rootViewProvider,
        resIds = resId,
        lifecycle = lifecycle,
        onBind = null
    )

inline fun <reified T : View> Any.bindViews(
    rootViewProvider: ViewProvider,
    resId: List<Int>,
    lifecycle: Lifecycle,
    noinline onBind: (List<T>.() -> Unit)?
) =
    BindViews<T>(
        targetClazz = T::class.java,
        rootViewProvider = rootViewProvider,
        resIds = resId,
        lifecycle = lifecycle,
        onBind = onBind
    )
//endregion

