package de.mustafagercek.epoxybase_sample

import android.animation.ObjectAnimator
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import de.mustafagercek.epoxybase_lib.button.buttonModel
import de.mustafagercek.epoxybase_lib.imageModels.imageModel
import de.mustafagercek.epoxybase_lib.loading.loadingModel
import de.mustafagercek.epoxybase_lib.lottie.lottieRowModel
import de.mustafagercek.epoxybase_lib.profile.profileHeader
import de.mustafagercek.epoxybase_lib.profile.settingsItem
import de.mustafagercek.epoxybase_lib.spacing.heightSpacing
import de.mustafagercek.epoxybase_lib.textModels.Type
import de.mustafagercek.epoxybase_lib.textModels.textModel
import de.mustafagercek.epoxybase_sample.databinding.ActivityMainBinding

import de.mustafagercek.epoxybase_lib.util.withModels

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var isLoading = false
    var isEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.withModels {
            textModel("Test",type = Type.TITLE).addTo(this)
            textModel("Test",type = Type.REGULAR).addTo(this)
            textModel("Test",type = Type.LIGHT).addTo(this)

//            buttonModel("Loading/notLoading", isLoading = isLoading, click = View.OnClickListener {
//                isLoading = !isLoading
//                binding.recyclerView.requestModelBuild()
//                Handler().postDelayed({
//                        isLoading = !isLoading
//                        binding.recyclerView.requestModelBuild()
//                    }, 500
//                )
//            }).addTo(this)
//
//            buttonModel("enabled/notEnabled", isEnabled = isEnabled, click = View.OnClickListener {
//                isEnabled = !isEnabled
//                binding.recyclerView.requestModelBuild()
//                Handler().postDelayed({
//                    isEnabled = !isEnabled
//                    binding.recyclerView.requestModelBuild()
//                }, 500
//                )
//            }).addTo(this)
//
//            textModel("BOLD",typeface = Typeface.BOLD).addTo(this)
//            textModel("DEFAULT",typeface = Typeface.NORMAL).addTo(this)
//            textModel("ITALIC",typeface = Typeface.ITALIC).addTo(this)
//
//            lottieRowModel(
//                R.raw.no_provider_close_by,
//                w = 152,
//                h = 152,
//                gravity = Gravity.CENTER,
//                speed = 3f,
//                repeatCount = ObjectAnimator.INFINITE,
//                repeatMode = ObjectAnimator.REVERSE
//            ).addTo(this)
//
//            loadingModel().addTo(this)
//
//            buttonModel("Test", click = View.OnClickListener { "Test" }, r = 10, l = 42,buttonColor = ContextCompat.getColor(this@MainActivity,R.color.colorPrimary)).addTo(this)
//
//
//            buttonModel("Test gewrapped", click = View.OnClickListener { "Test" },w = ViewGroup.LayoutParams.WRAP_CONTENT, r = 10, l = 42,buttonColor = ContextCompat.getColor(this@MainActivity,R.color.colorPrimary)).addTo(this)
//
//
//            heightSpacing(77, "h1").addTo(this)
//
//            profileHeader(
//                "AA",
//                "la",
//                backgroundImageRes = R.drawable.pattern,
//                textColorRes = R.color.colorAccent,
//                headerBackgroundColorRes = R.color.colorPrimary,
//                iconBackgroundColorRes = R.color.colorPrimary
//            ).addTo(this)
//
//            settingsItem(
//                settingsText = "Test",
//                actionImageRes = R.drawable.ic_android_black_24dp,
//                settingsImageRes = R.drawable.ic_android_black_24dp,
//                backgroundColorRes = R.color.colorAccent,
//                listener = View.OnClickListener {
//
//                }).addTo(this)
//
//            settingsItem(
//                settingsText = "Test2",
//                settingsImageRes = R.drawable.ic_launcher_foreground,
//                listener = View.OnClickListener {
//
//                }).addTo(this)
//
//            settingsItem(
//                settingsText = "Test3",
//                settingsImageRes = R.drawable.ic_android_black_24dp,
//                t = 128,
//                listener = View.OnClickListener {
//
//                }).addTo(this)
//
//            textModel(
//                "Center",
//                type = Type.TITLE,
//                gravity = Gravity.CENTER,
//                backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
//            ).addTo(this)
//            imageModel(R.drawable.ic_no_appointments, w = 78, h = 78, t = 32).addTo(this)
//            textModel("Start").addTo(this)
//            imageModel(R.drawable.ic_android_black_24dp, click = View.OnClickListener {
//                Toast.makeText(this@MainActivity, "Hello", Toast.LENGTH_LONG).show()
//            }, gravity = Gravity.END, id = 22).addTo(this)
        }

    }

}