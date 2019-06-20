package de.mustafagercek.library.profile

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.github.ivbaranov.mli.MaterialLetterIcon
import de.mustafagercek.library.R
import de.mustafagercek.library.R2
import de.mustafagercek.library.util.KotlinEpoxyHolder

fun Context.profileHeader(
    letterText: String,
    text: String,
    descriptionText: String? = null,
    letterColorRes: Int? = null,
    textColorRes: Int? = null,
    iconBackgroundColorRes: Int? = null,
    headerBackgroundColorRes: Int? = null,
    backgroundImageRes: Int? = null
): ProfileHeaderModel_ {
    return ProfileHeaderModel_().id("profile_header").letterText(letterText).text(text).description(descriptionText)
        .backgroundImagePath(backgroundImageRes)
        .layoutBackgroundColor(
            if (headerBackgroundColorRes != null) ContextCompat.getColor(
                this,
                headerBackgroundColorRes
            ) else null
        )
        .iconBackgroundColor(
            if (iconBackgroundColorRes != null) ContextCompat.getColor(
                this,
                iconBackgroundColorRes
            ) else null
        )
        .letterColor(
            if (letterColorRes != null) ContextCompat.getColor(
                this,
                letterColorRes
            ) else null
        ).textColor(
            if (textColorRes != null) ContextCompat.getColor(
                this,
                textColorRes
            ) else null
        )
}

@EpoxyModelClass(layout = R2.layout.profile_header_model)
abstract class ProfileHeaderModel : EpoxyModelWithHolder<ProfileHeaderModel.Holder>() {

    @EpoxyAttribute
    lateinit var letterText: String

    @EpoxyAttribute
    lateinit var text: String

    @EpoxyAttribute
    var description: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var letterColor: Int? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var textColor: Int? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var backgroundImagePath: Int? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var iconBackgroundColor: Int? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var layoutBackgroundColor: Int? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)
        val c = holder.layout.context

        holder.icon.letter = letterText
        holder.text.text = text
        description?.let {
            holder.description.text = description
        } ?: run {
            holder.description.visibility = View.GONE
        }
        textColor?.let {
            holder.text.setTextColor(it)
            holder.description.setTextColor(it)
        }
        letterColor?.let {
            holder.icon.letterColor = it
        }

        iconBackgroundColor?.let {
            holder.icon.shapeColor=it
        }

        backgroundImagePath?.let {
            holder.backgroundImage.setImageResource(it)
        }

        layoutBackgroundColor?.let {
            holder.layout.setBackgroundColor(it)
        }

    }

    class Holder : KotlinEpoxyHolder() {
        val layout by bind<FrameLayout>(R.id.profile_header_model_layout)
        val icon by bind<MaterialLetterIcon>(R.id.profile_header_model_icon)
        val text by bind<TextView>(R.id.profile_header_model_name)
        val description by bind<TextView>(R.id.profile_header_model_description)
        val backgroundImage by bind<ImageView>(R.id.profile_header_model_background_image)
    }

}
