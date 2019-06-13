package de.mustafagercek.epoxybase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import de.mustafagercek.epoxybase.databinding.ActivityMainBinding
import de.mustafagercek.library.imageModels.imageModel
import de.mustafagercek.library.textModels.Type
import de.mustafagercek.library.textModels.textModel
import de.mustafagercek.library.util.withModels

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.withModels {
            textModel("Center", type = Type.TITLE, gravity = Gravity.CENTER).addTo(this)
            imageModel(R.drawable.ic_android_black_24dp).addTo(this)
            textModel("Start", type = Type.REGULAR, gravity = Gravity.START).addTo(this)
            imageModel(R.drawable.ic_android_black_24dp, click = View.OnClickListener {
                Toast.makeText(this@MainActivity, "Hello", Toast.LENGTH_LONG).show()
            }, gravity = Gravity.END, id = 22).addTo(this)
        }

    }

}