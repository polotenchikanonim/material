package local.kas.material.view.base_fragments.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import local.kas.material.databinding.FragmentObjectAnimateBinding
import local.kas.material.view.base_fragments.BaseFragment


class ObjectAnimateFragment :
    BaseFragment<FragmentObjectAnimateBinding>(FragmentObjectAnimateBinding::inflate) {

    private var flag = true
    private val animDuration = 1000L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            fab.setOnClickListener {
                showAnim()
                flag = !flag
            }
            transparentBackground.alpha = 0f
            optionTwoContainer.apply {
                alpha = 0f
                isClickable = false
            }
            optionOneContainer.apply {
                alpha = 0f
                isClickable = false
            }
        }

    }

    private fun showAnim() {
        with(binding) {
            if (flag) {
                ObjectAnimator.ofFloat(plusImageview, View.ROTATION, 405f, 0f)
                    .apply { duration = animDuration }.start()
                ObjectAnimator.ofFloat(optionTwoContainer, View.TRANSLATION_Y, 0f, -130F)
                    .apply { duration }.start()
                ObjectAnimator.ofFloat(optionOneContainer, View.TRANSLATION_Y, 0f, -260F)
                    .apply { duration }.start()
                anim(optionTwoContainer, 1f)
                anim(optionOneContainer, 1f)
                animContainer(transparentBackground,0.8f)
            } else {
                ObjectAnimator.ofFloat(plusImageview, View.ROTATION, 0f, 405f)
                    .apply { duration = animDuration }.start()
                ObjectAnimator.ofFloat(optionTwoContainer, View.TRANSLATION_Y, -130F, 0f)
                    .apply { duration }.start()
                ObjectAnimator.ofFloat(optionOneContainer, View.TRANSLATION_Y, -260F, 0f)
                    .apply { duration }.start()
                anim(optionTwoContainer, 0f)
                anim(optionOneContainer, 0f)
                animContainer(transparentBackground, 0f)
            }
        }

    }

    private fun animContainer(transparentBackground: FrameLayout, alpha: Float) {
        transparentBackground.animate().alpha(alpha).setDuration(animDuration).setListener(
            object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                }
            }
        )
    }

    private fun anim(optionOneContainer: LinearLayout, alpha: Float) {
        optionOneContainer.animate().alpha(alpha).setDuration(animDuration).setListener(
            object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    optionOneContainer.isClickable = !flag
                    super.onAnimationEnd(animation)
                }
            }
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = ObjectAnimateFragment()
    }
}