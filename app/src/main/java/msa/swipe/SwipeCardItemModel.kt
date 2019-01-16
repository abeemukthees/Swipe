package msa.swipe

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import msa.swipe.base.BaseEpoxyHolder

/**
 * Created by Abhi Muktheeswarar.
 */

@EpoxyModelClass(layout = R.layout.item_swipe_card)
abstract class SwipeCardItemModel : EpoxyModelWithHolder<SwipeCardItemModel.SwipeCardItemViewHolder>() {

    @EpoxyAttribute
    open var lessonId: Int = -1

    @EpoxyAttribute
    lateinit var title: String

    @DrawableRes
    @EpoxyAttribute
    open var iconResId: Int = -1

    override fun bind(holder: SwipeCardItemViewHolder) {
        super.bind(holder)
        holder.titleTextView.text = title
        holder.bgImageView.setImageResource(iconResId)
    }

    class SwipeCardItemViewHolder : BaseEpoxyHolder() {

        val titleTextView by bind<TextView>(R.id.text_title)
        val bgImageView by bind<ImageView>(R.id.image_bg)
    }
}