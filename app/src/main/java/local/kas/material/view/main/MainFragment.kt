package local.kas.material.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import local.kas.material.databinding.MainFragmentBinding

import local.kas.material.viewmodel.main.PictureOfTheDayData
import local.kas.material.viewmodel.main.PictureOfTheDayViewModel

class MainFragment : Fragment() {


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    //    private lateinit var viewModel: PictureOfTheDayViewModel
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            textInputLayout.setEndIconOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://en.wikipedia.org/wiki/${inputEditText.text.toString()}")
                })
            }
        }

        viewModel.let { vm ->
            vm.getData().observe(viewLifecycleOwner) { renderData(it) }
            vm.sendRequest()
        }
    }

    private fun renderData(pictureOfTheDayData: PictureOfTheDayData) {
        when (pictureOfTheDayData) {
            is PictureOfTheDayData.Error -> {
            }
            is PictureOfTheDayData.Loading -> {
            }
            is PictureOfTheDayData.Success -> {
                with(binding) {
                    pictureOfTheDayData.pdoServerResponse.let {
                        explanation.text = it.explanation
                        imageView.load(it.url)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }


}