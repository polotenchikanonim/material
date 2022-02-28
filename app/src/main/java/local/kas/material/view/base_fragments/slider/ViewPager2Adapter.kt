package local.kas.material.view.base_fragments.slider

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import local.kas.material.view.base_fragments.earth.EarthFragment
import local.kas.material.view.base_fragments.mars.MarsFragment
import local.kas.material.view.base_fragments.system.SystemFragment


class ViewPager2Adapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int) = fragments[position]


}