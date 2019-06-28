package de.mustafagercek.epoxybase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import de.mustafagercek.epoxybase.databinding.ActivityMainBinding
import de.mustafagercek.library.imageModels.imageModel
import de.mustafagercek.library.profile.profileHeader
import de.mustafagercek.library.profile.settingsItem
import de.mustafagercek.library.textModels.Type
import de.mustafagercek.library.textModels.textModel
import de.mustafagercek.library.util.withModels

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.withModels {
            profileHeader(
                "AA",
                "la",
                backgroundImageRes = R.drawable.pattern,
                textColorRes = R.color.colorAccent,
                headerBackgroundColorRes = R.color.colorPrimary,
                iconBackgroundColorRes = R.color.colorPrimary
            ).addTo(this)

            settingsItem(
                settingsText = "Test",
                actionImageRes = R.drawable.ic_android_black_24dp,
                settingsImageRes = R.drawable.ic_android_black_24dp,
                backgroundColorRes = R.color.colorAccent,
                listener = View.OnClickListener {

                }).addTo(this)

            settingsItem(
                settingsText = "Test2",
                settingsImageRes = R.drawable.ic_launcher_foreground,
                listener = View.OnClickListener {

                }).addTo(this)

            settingsItem(
                settingsText = "Test3",
                settingsImageRes = R.drawable.ic_android_black_24dp,
                t = 128,
                listener = View.OnClickListener {

                }).addTo(this)

            textModel("Center", type = Type.TITLE, gravity = Gravity.CENTER).addTo(this)
            imageModel(R.drawable.ic_no_appointments, w = 78, h = 78, t = 32).addTo(this)
            textModel("Start").addTo(this)
            imageModel(R.drawable.ic_android_black_24dp, click = View.OnClickListener {
                Toast.makeText(this@MainActivity, "Hello", Toast.LENGTH_LONG).show()
            }, gravity = Gravity.END, id = 22).addTo(this)
        }

    }

}