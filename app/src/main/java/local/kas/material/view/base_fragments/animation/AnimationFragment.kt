package local.kas.material.view.base_fragments.animation

import android.os.Bundle
import android.transition.*
import android.transition.TransitionSet.ORDERING_SEQUENTIAL
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import local.kas.material.databinding.FragmentAnimationBinding

import local.kas.material.view.base_fragments.BaseFragment

class AnimationFragment : BaseFragment<FragmentAnimationBinding>(
    FragmentAnimationBinding::inflate
) {

    private var flag = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            button.setOnClickListener {
                animate()
            }
            appCompatButton.setOnClickListener {
                animate3(it)
            }
            button2.setOnClickListener {
                animate4()
            }
        }
    }

    private fun animate3(it: View) {
        TransitionManager.beginDelayedTransition(
            binding.root,
            ChangeBounds().apply {
                pathMotion = ArcMotion()
                duration = 2000
            })
        val params = it.layoutParams as FrameLayout.LayoutParams
        params.gravity = if (flag) {
            Gravity.TOP or Gravity.START
        } else {
            Gravity.BOTTOM or Gravity.END
        }
        flag = !flag
        it.layoutParams = params
        animate4()
    }

    private fun animate() {
        with(binding) {
            val transitionSet = TransitionSet()
            transitionSet.apply {
                addTransition(Slide(Gravity.END).apply { duration = 2000 })
                addTransition(ChangeBounds().apply { duration = 2000 })
                ordering = ORDERING_SEQUENTIAL
            }
            TransitionManager.beginDelayedTransition(transitionContainer, transitionSet)
            with(text) {
                visibility =
                    if (visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
    }

    private fun animate4() {
        val strings: MutableList<String> = ArrayList()
        repeat(5) {
            strings.add("item $it")
        }
        strings.shuffle()

        with(binding.linerLayout) {
            TransitionManager.beginDelayedTransition(this, ChangeBounds())
            removeAllViews()
            strings.forEach {
                addView(TextView(this.context).apply {
                    text = it
                    textSize = 20f
                    transitionName = it
                    gravity = Gravity.CENTER_HORIZONTAL
                })
            }
        }

    }


    private fun animate2() {
        with(binding) {

            val transitionSet = TransitionSet()
            transitionSet.apply {
                addTransition(Fade().apply { duration = 2000 })
                addTransition(ChangeBounds().apply { duration = 2000 })
                ordering = ORDERING_SEQUENTIAL
            }
            TransitionManager.beginDelayedTransition(transitionContainer, transitionSet)
            with(text) {
                visibility =
                    if (visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
    }

    private fun animate1() {
        with(binding) {
            TransitionManager.beginDelayedTransition(transitionContainer, AutoTransition().apply {
                duration = 1000
            })
            with(text) {
                visibility =
                    if (visibility == View.GONE) View.VISIBLE else View.GONE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AnimationFragment()
    }

}