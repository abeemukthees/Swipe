package msa.swipe.utilities

import android.widget.ImageView
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions


/**
 * Created by Abhi Muktheeswarar.
 */


@GlideModule
class SwipeAppGlideModule : AppGlideModule()

fun ImageView.loadUrl(url: String?) {
    GlideApp.with(context)
        .load(url)
        .apply(RequestOptions().transforms(CenterCrop()))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}