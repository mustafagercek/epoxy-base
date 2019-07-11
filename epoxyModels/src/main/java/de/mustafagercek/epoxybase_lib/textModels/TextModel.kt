package de.mustafagercek.epoxybase_lib.textModels

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
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import de.mustafagercek.epoxybase_lib.R
import de.mustafagercek.epoxybase_lib.R2
import de.mustafagercek.epoxybase_lib.util.KotlinEpoxyHolder
import de.mustafagercek.epoxybase_lib.util.dpToPx


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
    listener: View.OnClickListener? = null,
    font: Int? = type?.font,
    backgroundColor: Int? = null,
    tc: Int = type?.tc ?: R.color.textDefault
): TextModel_ {
    val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(w, h)
    params.setMargins(dpToPx(l), dpToPx(t), dpToPx(r), dpToPx(b))
    return TextModel_().id(text).backgroundColor(backgroundColor).listener(listener).fontRes(font).textSize(ts)
        .text(text).textColor(tc)
        .textGravity(gravity)
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
    var backgroundColor: Int? = null

    @EpoxyAttribute
    var textGravity: Int? = null

    @EpoxyAttribute
    var fontPath: String? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var listener: View.OnClickListener? = null

    @FontRes
    @EpoxyAttribute
    var fontRes: Int? = null

    @SuppressLint("SetTextI18n")
    override fun bind(holder: Holder) {
        super.bind(holder)
        val context = holder.textView.context

        holder.textView.text = text
        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        holder.textView.setTextColor(ContextCompat.getColor(context, textColor))

        layoutParams?.let {
            holder.textView.layoutParams = it
        }

        backgroundColor?.let {
            holder.layout.setBackgroundColor(it)
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

        listener?.let {
            holder.textView.setOnClickListener(listener)
            holder.textView.isFocusable = true
            holder.textView.isClickable = true
        }

    }

    class Holder : KotlinEpoxyHolder() {
        val textView by bind<TextView>(R.id.text_view)
        val layout by bind<LinearLayout>(R.id.layout)

    }

}
