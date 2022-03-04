package local.kas.material.view.base_fragments.lesson_four

import android.os.Bundle
import android.view.View
import local.kas.material.databinding.FragmentConstraintBinding
import local.kas.material.view.base_fragments.BaseFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ConstraintFragment :
    BaseFragment<FragmentConstraintBinding>(FragmentConstraintBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            button3.setOnClickListener {
                if (group1.visibility == View.VISIBLE) {
                    group1.visibility = View.GONE
                } else {
                    group1.visibility = View.VISIBLE
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = ConstraintFragment()
    }
}