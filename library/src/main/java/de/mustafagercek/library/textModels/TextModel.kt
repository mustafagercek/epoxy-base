package de.mustafagercek.library.textModels

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
import android.view.ViewGroup
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import de.mustafagercek.library.R
import de.mustafagercek.library.R2
import de.mustafagercek.library.util.KotlinEpoxyHolder
import de.mustafagercek.library.util.dpToPx


fun Context.textModel(
    text: String,
    type: Type? = null,
    t: Int = 0,
    b: Int = 0,
    l: Int = 0,
    r: Int = 0,
    h: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    w: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    gravity: Int = Gravity.START,
    ts: Float = type?.ts ?: 14f,
    font: Int? = type?.font,
    tc: Int = type?.tc ?: R.color.textDefault
): TextModel_ {
    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(w, h)
    params.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    return TextModel_().id(text).fontRes(font).textSize(ts).text(text).textColor(tc).textGravity(gravity)
        .layoutParams(params)
}

class Type(val ts: Float, val tc: Int, val font: Int? = null) {
    companion object {
        val TITLE: Type = Type(18f, R.color.textDark, null)
        val REGULAR: Type = Type(14f, R.color.textDefault, null)
    }
}


@EpoxyModelClass(layout = R2.layout.text_row_model)
abstract class TextModel : EpoxyModelWithHolder<TextModel.Holder>() {

    @EpoxyAttribute
    lateinit var text: String
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var layoutParams: LinearLayout.LayoutParams? = null
    @EpoxyAttribute
    var textSize: Float = 20f
    @EpoxyAttribute
    var textColor: Int = 0

    @EpoxyAttribute
    var textGravity: Int? = null

    @EpoxyAttribute
    var fontPath: String? = null

    @FontRes
    @EpoxyAttribute
    var fontRes: Int? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)
        val context = holder.textView.context

        holder.textView.text = text
        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        holder.textView.setTextColor(textColor)

        layoutParams?.let {
            holder.textView.layoutParams = it
        }

        textGravity?.let {
            holder.textView.gravity = it
        }

        fontPath?.let {
            val tf = Typeface.createFromAsset(context.assets, fontPath)
            holder.textView.typeface = tf
        }

        fontRes?.let {
            val typeFace = ResourcesCompat.getFont(context, it)
            holder.textView.typeface = typeFace
        }

    }

    class Holder : KotlinEpoxyHolder() {
        val textView by bind<TextView>(R.id.text_view)
    }

}
