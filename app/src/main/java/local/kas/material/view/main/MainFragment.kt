package local.kas.material.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import local.kas.material.R
import local.kas.material.databinding.MainFragmentBinding

import local.kas.material.view.MainActivity
import local.kas.material.view.settings.SettingsFragment
import local.kas.material.viewmodel.main.PictureOfTheDayData
import local.kas.material.viewmodel.main.PictureOfTheDayViewModel


class MainFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendRequest()

        binding.textInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }


//        initBottomSheet()  // doesn't work :(  fixme IMPORTANT
//        initMyBottomSheet()  // doesn't correct work :( fixme IMPORTANT
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.supportActionBar == null)  {
            (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        }
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            with(binding) {
                if (isMain) {
                    with(bottomAppBar) {
                        navigationIcon = null
                        fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                        replaceMenu(R.menu.menu_bottom_bar_other_screen)
                    }
                    fab.setImageResource(R.drawable.ic_back_fab)
                } else {
                    with(bottomAppBar) {
                        navigationIcon = ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_hamburger_menu_bottom_bar
                        )
                        fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                        replaceMenu(R.menu.menu_bottom_bar)
                    }
                    fab.setImageResource(R.drawable.ic_plus_fab)
                }
            }
            isMain = !isMain
        }
    }

    private var isMain = true

    private fun renderData(pictureOfTheDayData: PictureOfTheDayData) {
        when (pictureOfTheDayData) {
            is PictureOfTheDayData.Error -> {

            }
            is PictureOfTheDayData.Loading -> {

            }
            is PictureOfTheDayData.Success -> {
                //pictureOfTheDayData.serverResponse.title
                //pictureOfTheDayData.serverResponse.explanation
                with(binding) {
                    pictureOfTheDayData.pdoServerResponse.let {
                        imageView.load(it.url)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> {
                Toast.makeText(requireContext(), "app_bar_fav", Toast.LENGTH_SHORT).show()
            }
            R.id.app_bar_settings -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance()).addToBackStack("")
                    .commit()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager, "ff")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private fun initBottomSheet() {
//        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Log.d("myLogs", "BottomSheetBehavior.STATE_DRAGGING")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("myLogs", "BottomSheetBehavior.STATE_COLLAPSED")
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("myLogs", "BottomSheetBehavior.STATE_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        Log.d("myLogs", "BottomSheetBehavior.STATE_HALF_EXPANDED")
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        Log.d("myLogs", "BottomSheetBehavior.STATE_HIDDEN")
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        Log.d("myLogs", "BottomSheetBehavior.STATE_SETTLING")
                    }

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("myLogs", "slideOffset $slideOffset")
            }

        })
    }

    private fun initMyBottomSheet() {
        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.SheetDialog)
        val view = layoutInflater.inflate(R.layout.bottom_sheet, binding.bottom, false)
        binding.bottom
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}