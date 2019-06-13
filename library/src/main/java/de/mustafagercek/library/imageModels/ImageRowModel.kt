package de.mustafagercek.library.imageModels

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import de.mustafagercek.library.R
import de.mustafagercek.library.R2
import de.mustafagercek.library.util.KotlinEpoxyHolder
import de.mustafagercek.library.util.dpToPx


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
    val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(dpToPx(w), dpToPx(h))
    params.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    params.gravity = gravity
    return ImageRowModel_().listener(click).imageResource(res).imageLayoutParams(params).imageViewTint(tint).id(id)
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
    }

}
