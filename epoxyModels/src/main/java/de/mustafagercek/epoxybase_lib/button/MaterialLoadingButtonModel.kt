package de.mustafagercek.epoxybase_lib.button

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import de.mustafagercek.epoxybase_lib.R
import de.mustafagercek.epoxybase_lib.R2
import de.mustafagercek.epoxybase_lib.util.KotlinEpoxyHolder
import de.mustafagercek.epoxybase_lib.util.dpToPx
import de.mustafagercek.materialloadingbutton.LoadingButton


fun Context.buttonModel(
    buttonText: String,
    t: Int = 0,
    b: Int = 0,
    l: Int = 0,
    r: Int = 0,
    h: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    w: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    click: View.OnClickListener,
    isEnabled: Boolean = true,
    buttonColor: Int? = null,
    textColor: Int? = null,
    buttonColorRes: Int? = null,
    textColorRes: Int? = null,
    gravity: Int = Gravity.CENTER,
    id: String = buttonText
): MaterialLoadingButtonModel_ {
    val buttonParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(dpToPx(w), dpToPx(h))
    val layoutParams: FrameLayout.LayoutParams =
        FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    buttonParams.gravity = gravity
    return MaterialLoadingButtonModel_().buttonText(buttonText).listener(click).isButtonEnabled(isEnabled)
        .backgroundColorRes(buttonColorRes)
        .buttonColor(buttonColor)
        .textColor(textColor)
        .textColorRes(textColorRes)
        .frameLayoutLayoutParams(layoutParams).id(id)
}


@EpoxyModelClass(layout = R2.layout.material_loading_button_model)
abstract class MaterialLoadingButtonModel : EpoxyModelWithHolder<MaterialLoadingButtonModel.Holder>() {


    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: View.OnClickListener

    @EpoxyAttribute
    lateinit var buttonText: String

    @JvmField
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var isButtonEnabled: Boolean = true

    @JvmField
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    @ColorRes
    var backgroundColorRes: Int? = null

    @JvmField
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    @ColorRes
    var textColorRes: Int? = null

    @JvmField
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var buttonColor: Int? = null

    @JvmField
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var textColor: Int? = null

    @JvmField
    public var buttonViewHolder: MaterialLoadingButtonModel.Holder? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var frameLayoutLayoutParams: FrameLayout.LayoutParams? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)
        buttonViewHolder = holder
        val context = holder.button.context

        holder.button.setButtonOnClick(listener)
        textColorRes?.let {
            holder.button.setTextColor(ContextCompat.getColor(context, it))
        } ?: run {
            textColor?.let {
                holder.button.setTextColor(it)
            }
        }

        holder.button.setButtonText(buttonText)
        holder.button.setButtonEnabled(isButtonEnabled)

        backgroundColorRes?.let {
            holder.button.setButtonColor(ContextCompat.getColor(context, it))
        } ?: run {
            buttonColor?.let {
                holder.button.setButtonColor(it)
            }
        }

        frameLayoutLayoutParams?.let {
            holder.button.layoutParams = it
        }

    }

    override fun unbind(holder: MaterialLoadingButtonModel.Holder) {
        this.buttonViewHolder = null
        super.unbind(holder)

    }

    class Holder : KotlinEpoxyHolder() {
        val button by bind<LoadingButton>(R.id.button)
        val layout by bind<LoadingButton>(R.id.layout)

    }
}
