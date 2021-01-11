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