package local.kas.material.view.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import local.kas.material.R
import local.kas.material.databinding.FragmentSettingsBinding
import local.kas.material.view.MainActivity


class SettingsFragment : Fragment() {

    private val appTheme = "theme"

    private val keyPref = "key"


    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            chipGroup.setOnCheckedChangeListener { _, checkedId ->
                chipGroup.findViewById<Chip>(checkedId)?.let { it ->
                    Toast.makeText(requireContext(), "${it.text} $checkedId", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            chipEntry.setOnCloseIconClickListener {
                Toast.makeText(requireContext(), "chipEntry close", Toast.LENGTH_SHORT).show()
            }
            requireActivity().let { context ->
                tabs.getTabAt(0)?.let {
                    it.text = "Сегодня"
                    it.icon = AppCompatResources.getDrawable(context, R.drawable.ic_search)
                }
                tabs.getTabAt(1)?.let {
                    it.text = "Вчера"
                    it.icon = AppCompatResources.getDrawable(context, R.drawable.ic_favourite_menu)
                }
                tabs.getTabAt(2)?.let {
                    it.text = "Позавчера"
                    it.icon = AppCompatResources.getDrawable(context, R.drawable.ic_plus_fab)
                }
            }
            tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        println(it.position)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
        initButtons()
    }

    private fun initButtons() {
        with(binding) {
            light.setOnClickListener { onClick(R.style.MyThemeGreen) }
            dark.setOnClickListener { onClick(R.style.MyBlackBlue) }
        }
    }

    private fun onClick(style: Int) {
        setAppTheme(style)
        (requireActivity() as MainActivity).recreate()
    }

    private fun setAppTheme(codeStyle: Int) {
        val sharedPref: SharedPreferences =
            requireContext().getSharedPreferences(keyPref, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(appTheme, codeStyle)
        editor.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}