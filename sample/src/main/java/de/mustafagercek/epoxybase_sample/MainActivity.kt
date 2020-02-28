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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerView.withModels {
            textModel("Test", type = Type.TITLE).addTo(this)
            textModel("Test", type = Type.REGULAR, gravity = Gravity.CENTER_HORIZONTAL).addTo(this)
            textModel("Test", type = Type.LIGHT, gravity = Gravity.CENTER_HORIZONTAL).addTo(this)

            buttonModel("Button", click = View.OnClickListener {
                isLoading = !isLoading
                requestModelBuild()
            }).addTo(this)

            buttonModel("LoadingButton", click = View.OnClickListener {

            }, isLoading = isLoading).addTo(this)

            imageModel(R.drawable.ic_no_appointments, w = 48, h = 48).addIf(!isLoading,this)

            heightSpacing(12)

            imageModel(R.drawable.ic_no_appointments, w = 96, h = 96, gravity = Gravity.END).addTo(this)

            loadingModel().addTo(this)


        }

    }

}