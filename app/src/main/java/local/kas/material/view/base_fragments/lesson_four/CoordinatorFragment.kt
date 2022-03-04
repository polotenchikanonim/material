package local.kas.material.view.base_fragments.lesson_four

import android.os.Bundle
import android.view.View
import local.kas.material.databinding.FragmentCoordinatorBinding
import local.kas.material.view.base_fragments.BaseFragment


class CoordinatorFragment :
    BaseFragment<FragmentCoordinatorBinding>(FragmentCoordinatorBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }
}