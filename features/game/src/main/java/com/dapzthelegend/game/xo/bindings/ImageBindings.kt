package com.dapzthelegend.game.xo.bindings

import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.dapzthelegend.game.xo.game.BoxState
import com.dapzthelegend.multiplayer.R

/**
 * View binding for xo piece image based on state.
 *
 * @param state The current state of the image.
 */
@BindingAdapter("image")
fun ImageView.image(state: BoxState) {
    when (state) {
        is BoxState.X ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.x, null))
        is BoxState.O ->
            this.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.o, null))
        is BoxState.Won -> {
            val anim = AnimationUtils.loadAnimation(context, R.anim.blink)
            this.startAnimation(anim)
        }
        is BoxState.Empty -> this.setImageDrawable(null)
    }
}
