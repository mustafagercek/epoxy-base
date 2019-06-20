package de.mustafagercek.library.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import de.mustafagercek.library.R
import de.mustafagercek.library.R2
import de.mustafagercek.library.util.KotlinEpoxyHolder

fun Context.settingsItem(
    settingsImageRes: Int,
    settingsText: String,
    actionImageRes: Int? = null,
    backgroundColorRes: Int? = null,
    listener: View.OnClickListener
): SettingsItemModel_ {
    return SettingsItemModel_().id(settingsText).backgroundColorRes(backgroundColorRes).settingsText(settingsText)
        .settingsImageRes(settingsImageRes)
        .actionImageRes(actionImageRes)
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

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)

        holder.settingsImage.setImageResource(settingsImageRes)
        holder.settingsText.text = settingsText
        actionImageRes?.let {
            holder.actionImage.setImageResource(it)
        }
        backgroundColorRes?.let {
            holder.layout.setBackgroundColor(it)
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
