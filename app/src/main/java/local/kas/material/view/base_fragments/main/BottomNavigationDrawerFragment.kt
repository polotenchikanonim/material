package local.kas.material.view.base_fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import local.kas.material.R
import local.kas.material.databinding.BottomNavigationLayoutBinding
import local.kas.material.view.base_fragments.animation.AnimationFragment
import local.kas.material.view.base_fragments.animation.explode.ExplodeFragment
import local.kas.material.view.base_fragments.bottom.BottomNavigationFragment
import local.kas.material.view.base_fragments.lesson_four.ConstraintFragment
import local.kas.material.view.base_fragments.lesson_four.CoordinatorFragment
import local.kas.material.view.base_fragments.slider.SliderFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menu ->
            with(requireActivity().supportFragmentManager) {
                when (menu.itemId) {

                    R.id.navigation_one -> {
                        beginTransaction()
                            .replace(R.id.container, SliderFragment.newInstance()).addToBackStack("")
                            .commit()
                    }
                    R.id.navigation_two -> {
                        beginTransaction()
                            .replace(R.id.container, BottomNavigationFragment.newInstance())
                            .addToBackStack("1")
                            .commit()
                    }
                    R.id.navigation_three -> {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ConstraintFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                    R.id.navigation_four -> {
                        beginTransaction()
                            .replace(R.id.container, CoordinatorFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                    R.id.navigation_five -> {
                        beginTransaction().replace(R.id.container, AnimationFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }

                    R.id.navigation_five_explode -> {
                        beginTransaction().replace(R.id.container, ExplodeFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                }
                dismiss()
                true
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}