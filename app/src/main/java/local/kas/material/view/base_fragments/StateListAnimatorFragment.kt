package local.kas.material.view.base_fragments

import android.os.Bundle
import android.view.View
import local.kas.material.databinding.FragmentStateListAnimatorBinding


class StateListAnimatorFragment :
    BaseFragment<FragmentStateListAnimatorBinding>(FragmentStateListAnimatorBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = StateListAnimatorFragment()
    }
}