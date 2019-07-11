package de.mustafagercek.epoxybase_lib.profile

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

fun Context.settingsItem(
    settingsImageRes: Int,
    settingsText: String,
    actionImageRes: Int? = null,
    backgroundColorRes: Int? = null,
    layoutParams: FrameLayout.LayoutParams? = null,
    listener: View.OnClickListener,
    t: Int = 0,
    b: Int = 0,
    l: Int = 0,
    r: Int = 0
): SettingsItemModel_ {
    val params: FrameLayout.LayoutParams =
        FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    params.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    return SettingsItemModel_().id(settingsText).backgroundColorRes(backgroundColorRes).settingsText(settingsText)
        .settingsImageRes(settingsImageRes)
        .actionImageRes(actionImageRes)
        .layoutParams(params)
        .listener(listener)
}

@EpoxyModelClass(layout = R2.layout.profile_settings_item_model)
abstract class SettingsItemModel : EpoxyModelWithHolder<SettingsItemModel.Holder>() {

    @EpoxyAttribute
    var settingsImageRes: Int = 0

    @EpoxyAttribute
    var actionImageRes: Int? = null

    @EpoxyAttribute
    var backgroundColorRes: Int? = null

    @EpoxyAttribute
    lateinit var settingsText: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: View.OnClickListener

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var layoutParams: FrameLayout.LayoutParams? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)

        holder.settingsImage.setImageResource(settingsImageRes)
        holder.settingsText.text = settingsText
        actionImageRes?.let {
            holder.actionImage.setImageResource(it)
        }
        backgroundColorRes?.let {
            holder.layout.setBackgroundResource(it)
        }
        layoutParams?.let {
            holder.layout.layoutParams = it
            holder.layout.requestLayout()
        }

        holder.layout.setOnClickListener(listener)

    }

    class Holder : KotlinEpoxyHolder() {
        val layout by bind<FrameLayout>(R.id.settings_item_layout)
        val settingsImage by bind<ImageView>(R.id.profile_settings_item_start_image)
        val settingsText by bind<TextView>(R.id.profile_settings_item_text)
        val actionImage by bind<ImageView>(R.id.profile_settings_item_action_image)

    }
}
