package de.mustafagercek.epoxybase_lib.loading

import android.content.Context
import com.airbnb.epoxy.*
import de.mustafagercek.epoxybase_lib.R2
import de.mustafagercek.epoxybase_lib.util.KotlinEpoxyHolder

fun Context.loadingModel(): RowLoadingModel_ {
    return RowLoadingModel_().id("loading")
}

@EpoxyModelClass(layout = R2.layout.loading_model)
abstract class RowLoadingModel : EpoxyModelWithHolder<RowLoadingModel.Holder>() {

    class Holder : KotlinEpoxyHolder()
}

