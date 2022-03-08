package local.kas.material.view.base_fragments.animation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import local.kas.material.databinding.FragmentObjectAnimateBinding
import local.kas.material.view.base_fragments.BaseFragment


class ObjectAnimateFragment :
    BaseFragment<FragmentObjectAnimateBinding>(FragmentObjectAnimateBinding::inflate) {

    var flag = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        flag = !flag
        binding.fab.setOnClickListener {
            if (flag) {
                ObjectAnimator.ofFloat(binding.plusImageview, View.ROTATION, 0f, 405f)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ObjectAnimateFragment()
    }
}