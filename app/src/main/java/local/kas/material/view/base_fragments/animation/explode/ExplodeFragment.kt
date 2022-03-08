package local.kas.material.view.base_fragments.animation.explode

import android.graphics.Rect
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import local.kas.material.databinding.FragmentExplodeBinding
import local.kas.material.view.base_fragments.BaseFragment

class ExplodeFragment : BaseFragment<FragmentExplodeBinding>(FragmentExplodeBinding::inflate),
    OnMyClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val explodeAdapter = ExplodeAdapter(this)
        with(binding) {
            with(recyclerView) {
                adapter = explodeAdapter
            }
        }
    }


    override fun onClick(view: View, position: Int) {
        with(binding) {
            val rect = Rect()
            view.let {
                val explode = Explode().apply {
                    duration = 500
                    epicenterCallback = object : Transition.EpicenterCallback() {
                        override fun onGetEpicenter(transition: Transition) = rect
                    }
                    excludeTarget(it, true)
                }
                it.getGlobalVisibleRect(rect)
                TransitionManager.beginDelayedTransition(
                    transitionContainer, explode
                )
            }

            with(recyclerView) {
                adapter = null
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ExplodeFragment()
    }
}