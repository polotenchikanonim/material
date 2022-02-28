package local.kas.material.view.base_fragments.bottom

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import local.kas.material.R
import local.kas.material.databinding.FragmentBotomNavigationBinding
import local.kas.material.view.base_fragments.BaseFragment
import local.kas.material.view.base_fragments.earth.EarthFragment
import local.kas.material.view.base_fragments.mars.MarsFragment
import local.kas.material.view.base_fragments.system.SystemFragment


class BottomNavigationFragment :
    BaseFragment<FragmentBotomNavigationBinding>(FragmentBotomNavigationBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            bottomNavigationView.let {
                setListeners(it)
                it.selectedItemId = R.id.bottom_view_system
                it.getOrCreateBadge(R.id.bottom_view_mars).apply {
                    number = 10000
                    maxCharacterCount = 4
                    badgeGravity = BadgeDrawable.TOP_START
                }
            }
        }
    }

    private fun setListeners(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    navigateTo(EarthFragment())
                    true
                }
                R.id.bottom_view_mars -> {
                    navigateTo(MarsFragment())
                    true
                }
                R.id.bottom_view_system -> {
                    navigateTo(SystemFragment())
                    true
                }
                else -> true
            }
        }
    }


    private fun navigateTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(binding.container2.id, fragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = BottomNavigationFragment()
    }

}