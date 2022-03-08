package local.kas.material.view.base_fragments.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import local.kas.material.R
import local.kas.material.databinding.MainFragmentBinding
import local.kas.material.view.MainActivity
import local.kas.material.view.base_fragments.BaseFragment
import local.kas.material.view.base_fragments.settings.SettingsFragment
import local.kas.material.viewmodel.main.PictureOfTheDayData
import local.kas.material.viewmodel.main.PictureOfTheDayViewModel


class MainFragment : BaseFragment<MainFragmentBinding>(MainFragmentBinding::inflate) {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var isMain = true


    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            getData().observe(viewLifecycleOwner) {
                renderData(it)
            }
            sendRequest()
        }
        setListeners()
//        initBottomSheet()  // doesn't work :(  fixme IMPORTANT
//        initMyBottomSheet()  // doesn't correct work :( fixme IMPORTANT

        setHasOptionsMenu(true)
        with(binding) {
            (requireActivity() as MainActivity).setSupportActionBar(bottomAppBar)
            with(imageView) {
                setOnClickListener {
                    scaleType = if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                        ImageView.ScaleType.CENTER_INSIDE
                    } else {
                        ImageView.ScaleType.CENTER_CROP
                    }
                }
            }

        }
    }


    private fun setListeners() {
        with(binding) {
            textInputLayout.setEndIconOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(
                        "https://en.wikipedia.org/wiki/${inputEditText.text.toString()}"
                    )
                })
            }
            fab.setOnClickListener {
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
                isMain = !isMain
            }
        }


    }

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
                        with(imageView) {
//                            load(it.url)
                            load("https://raw.githubusercontent.com/mentatusn/material_1728_3/master/app/src/main/res/drawable/earth.png")
                        }
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
                    .replace(R.id.container, SettingsFragment.newInstance()).addToBackStack(null)
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

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

//    override fun onResume() {
//        super.onResume()
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.container, ExplodeFragment.newInstance())
//            .addToBackStack(null)
//            .commit()
//    }


}