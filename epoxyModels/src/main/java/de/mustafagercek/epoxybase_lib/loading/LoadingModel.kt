package de.mustafagercek.epoxybase_lib.loading

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.*
import de.mustafagercek.epoxybase_lib.R2

fun Context.loadingModel(): LoadingViewModel_ {
    return LoadingViewModel_().id("loading")
}

@ModelView(defaultLayout = R2.layout.loading_model)
class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

}
