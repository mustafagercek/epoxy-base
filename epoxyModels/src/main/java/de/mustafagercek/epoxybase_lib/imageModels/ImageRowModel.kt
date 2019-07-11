package de.mustafagercek.epoxybase_lib.imageModels

import android.annotation.SuppressLint
import android.content.Context
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import de.mustafagercek.epoxybase_lib.R
import de.mustafagercek.epoxybase_lib.R2
import de.mustafagercek.epoxybase_lib.util.KotlinEpoxyHolder
import de.mustafagercek.epoxybase_lib.util.dpToPx


fun Context.imageModel(
    res: Int,
    t: Int = 0,
    b: Int = 0,
    l: Int = 0,
    r: Int = 0,
    h: Int = 48,
    w: Int = 48,
    click: View.OnClickListener? = null,
    tint: Int? = null,
    gravity: Int = Gravity.CENTER,
    id: Int = res
): ImageRowModel_ {
    val imageParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(dpToPx(w), dpToPx(h))
    val layoutParams: FrameLayout.LayoutParams =
        FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    imageParams.gravity = gravity
    return ImageRowModel_().listener(click).imageResource(res).imageLayoutParams(imageParams).frameLayoutParams(layoutParams).imageViewTint(tint).id(id)
}


@EpoxyModelClass(layout = R2.layout.image_row_model)
abstract class ImageRowModel : EpoxyModelWithHolder<ImageRowModel.Holder>() {

    @DrawableRes
    @EpoxyAttribute
    var imageResource: Int? = null

    @ColorRes
    @EpoxyAttribute
    var imageViewTint: Int? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var imageLayoutParams: FrameLayout.LayoutParams? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var frameLayoutParams: FrameLayout.LayoutParams? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var listener: View.OnClickListener? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)
        val context = holder.imageView.context

        imageResource?.let {
            holder.imageView.setImageResource(it)
        }

        imageViewTint?.let {
            holder.imageView.setColorFilter(ContextCompat.getColor(context, it))
        }

        imageLayoutParams?.let {
                        holder.imageView.layoutParams = it
        }

        frameLayoutParams?.let {
            holder.frame.layoutParams = it
        }

        listener?.let {
            holder.imageView.isFocusable = true
            holder.imageView.isClickable = true
            holder.imageView.isEnabled = true
            holder.imageView.setOnClickListener(it)
        } ?: run {
            holder.imageView.isFocusable = false
            holder.imageView.isClickable = false
            holder.imageView.isEnabled = false
            holder.imageView.setOnClickListener(null)
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val imageView by bind<ImageView>(R.id.imageView)
        val frame by bind<FrameLayout>(R.id.frame)
    }

}
