package local.kas.material.view.base_fragments.animation

import android.os.Bundle
import android.transition.*
import android.transition.TransitionSet.ORDERING_SEQUENTIAL
import android.view.Gravity
import android.view.View
import local.kas.material.databinding.FragmentAnimationBinding

import local.kas.material.view.base_fragments.BaseFragment

class AnimationFragment : BaseFragment<FragmentAnimationBinding>(
    FragmentAnimationBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            button.setOnClickListener {
                animate()
            }
        }
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