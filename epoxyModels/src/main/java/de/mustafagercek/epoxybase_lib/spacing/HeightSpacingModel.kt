package de.mustafagercek.epoxybase_lib.spacing

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import de.mustafagercek.epoxybase_lib.R
import de.mustafagercek.epoxybase_lib.R2
import de.mustafagercek.epoxybase_lib.util.KotlinEpoxyHolder
import de.mustafagercek.epoxybase_lib.util.dpToPx

fun Context.heightSpacing(
    height: Int,
    id: String
): HeightSpacingModel_ {
    val params: FrameLayout.LayoutParams =
        FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(height))
    return HeightSpacingModel_().id(id)
        .layoutParams(params)
}

@EpoxyModelClass(layout = R2.layout.height_spacing_model)
abstract class HeightSpacingModel: EpoxyModelWithHolder<HeightSpacingModel.Holder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var layoutParams: FrameLayout.LayoutParams? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.layout.layoutParams = layoutParams

    }

    class Holder : KotlinEpoxyHolder() {
        val layout by bind<FrameLayout>(R.id.layout)
    }
}
