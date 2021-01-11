package osp.leobert.android.uibindingsample

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
    }
}