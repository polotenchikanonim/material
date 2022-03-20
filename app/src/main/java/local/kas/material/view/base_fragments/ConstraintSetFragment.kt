package local.kas.material.view.base_fragments

import local.kas.material.databinding.FragmentConstraintSetBinding


class ConstraintSetFragment :
    BaseFragment<FragmentConstraintSetBinding>(FragmentConstraintSetBinding::inflate) {


    companion object {

        @JvmStatic
        fun newInstance() = ConstraintSetFragment()
    }
}