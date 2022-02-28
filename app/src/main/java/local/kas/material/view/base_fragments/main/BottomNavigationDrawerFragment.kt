package local.kas.material.view.base_fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import local.kas.material.R
import local.kas.material.databinding.BottomNavigationLayoutBinding
import local.kas.material.view.base_fragments.bottom.BottomNavigationFragment
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
            when (menu.itemId) {
                R.id.navigation_one -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SliderFragment.newInstance()).addToBackStack("")
                        .commit()
                }
                R.id.navigation_two -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, BottomNavigationFragment.newInstance())
                        .addToBackStack("1")
                        .commit()
                }
            }
            dismiss()
            true
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}