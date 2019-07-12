package de.mustafagercek.epoxybase_lib.lottie

import android.animation.ValueAnimator
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
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import de.mustafagercek.epoxybase_lib.R
import de.mustafagercek.epoxybase_lib.R2
import de.mustafagercek.epoxybase_lib.util.KotlinEpoxyHolder
import de.mustafagercek.epoxybase_lib.util.dpToPx


fun Context.lottieRowModel(
    res: Int,
    t: Int = 0,
    b: Int = 0,
    l: Int = 0,
    r: Int = 0,
    h: Int = 48,
    w: Int = 48,
    speed: Float = 1f,
    repeatCount: Int? = null,
    repeatMode: Int? = null,
    click: View.OnClickListener? = null,
    tint: Int? = null,
    gravity: Int = Gravity.CENTER,
    id: Int = res
): LottieRowModel_ {
    val imageParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(dpToPx(w), dpToPx(h))
    val layoutParams: FrameLayout.LayoutParams =
        FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    imageParams.gravity = gravity
    return LottieRowModel_().repeatCount(repeatCount).listener(click).rawRes(res).repeatMode(repeatMode).speed(speed)
        .imageLayoutParams(imageParams)
        .frameLayoutParams(layoutParams).imageViewTint(tint).id(id)
}


@EpoxyModelClass(layout = R2.layout.lottie_row_model)
abstract class LottieRowModel : EpoxyModelWithHolder<LottieRowModel.Holder>() {

    @EpoxyAttribute
    var rawRes: Int = 0

    @ColorRes
    @EpoxyAttribute
    var imageViewTint: Int? = null

    @EpoxyAttribute
    var repeatMode: Int? = null

    @EpoxyAttribute
    var repeatCount: Int? = null

    @EpoxyAttribute
    var speed: Float = 1f

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var imageLayoutParams: FrameLayout.LayoutParams? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var frameLayoutParams: FrameLayout.LayoutParams? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var listener: View.OnClickListener? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)
        val context = holder.lottieView.context

        holder.lottieView.setAnimation(rawRes)

        holder.lottieView.speed = speed


        repeatCount?.let {
            holder.lottieView.repeatCount = it
        }
        repeatMode?.let {
            holder.lottieView.repeatMode = it
        }

        imageViewTint?.let {
            holder.lottieView.setColorFilter(ContextCompat.getColor(context, it))
        }

        imageLayoutParams?.let {
            holder.lottieView.layoutParams = it
        }

        frameLayoutParams?.let {
            holder.frame.layoutParams = it
        }

        listener?.let {
            holder.lottieView.isFocusable = true
            holder.lottieView.isClickable = true
            holder.lottieView.isEnabled = true
            holder.lottieView.setOnClickListener(it)
        } ?: run {
            holder.lottieView.isFocusable = false
            holder.lottieView.isClickable = false
            holder.lottieView.isEnabled = false
            holder.lottieView.setOnClickListener(null)
        }

    }

    override fun onViewAttachedToWindow(holder: Holder) {
        super.onViewAttachedToWindow(holder)
        holder.lottieView.playAnimation()
    }

    class Holder : KotlinEpoxyHolder() {
        val lottieView by bind<LottieAnimationView>(R.id.lottie_view)
        val frame by bind<FrameLayout>(R.id.frame)
    }

}
