package de.mustafagercek.library.button

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
import de.mustafagercek.library.R
import de.mustafagercek.library.R2
import de.mustafagercek.library.imageModels.ImageRowModel_
import de.mustafagercek.library.util.KotlinEpoxyHolder
import de.mustafagercek.library.util.dpToPx
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
    buttonBackgroundColor: Int? = null,
    gravity: Int = Gravity.CENTER,
    id: String = buttonText
): MaterialLoadingButtonModel_ {
    val buttonParams: FrameLayout.LayoutParams = FrameLayout.LayoutParams(dpToPx(w), dpToPx(h))
    val layoutParams: FrameLayout.LayoutParams =
        FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    layoutParams.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    buttonParams.gravity = gravity
    return MaterialLoadingButtonModel_().buttonText(buttonText).listener(click).isButtonEnabled(isEnabled).backgroundColor(buttonBackgroundColor)
        .frameLayoutLayoutParams(layoutParams).id(id)
}


@EpoxyModelClass(layout = R2.layout.material_loading_button_model)
abstract class MaterialLoadingButtonModel : EpoxyModelWithHolder<MaterialLoadingButtonModel.Holder>() {


    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: View.OnClickListener

    @EpoxyAttribute
    lateinit var buttonText: String

    @JvmField
    @EpoxyAttribute
    var isButtonEnabled: Boolean = true

    @JvmField
    @EpoxyAttribute
    @ColorRes
    var backgroundColor: Int? = null

    @JvmField
    @EpoxyAttribute
    @ColorRes
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
        textColor?.let {
            holder.button.setTextColor(ContextCompat.getColor(context, it))
        }

        holder.button.setButtonText(buttonText)
        holder.button.setButtonEnabled(isButtonEnabled)

        backgroundColor?.let {
            holder.button.setButtonColor(ContextCompat.getColor(context, it))
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
