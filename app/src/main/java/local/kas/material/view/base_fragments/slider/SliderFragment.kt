package local.kas.material.view.base_fragments.slider

import android.os.Bundle
import android.view.View
import local.kas.material.databinding.FragmentSliderBinding
import local.kas.material.view.base_fragments.BaseFragment


class SliderFragment : BaseFragment<FragmentSliderBinding>(FragmentSliderBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.adapter = ViewPager2Adapter(requireActivity())
    }

    companion object {
        @JvmStatic
        fun newInstance() = SliderFragment()
    }
}