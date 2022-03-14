package local.kas.material.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import local.kas.material.BuildConfig
import local.kas.material.repository.picture_of_day.PictureOfTheDayDTO
import local.kas.material.repository.picture_of_day.PictureOfTheDayRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayData> = MutableLiveData(),
    private val pictureOfTheDayRetrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {

    fun getData(): LiveData<PictureOfTheDayData> {
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(PictureOfTheDayData.Loading(0))
        pictureOfTheDayRetrofitImpl.getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<PictureOfTheDayDTO> {
                    override fun onResponse(
                        call: Call<PictureOfTheDayDTO>,
                        response: Response<PictureOfTheDayDTO>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                liveData.postValue(PictureOfTheDayData.Success(it))
                            }
                        } else {
                            println(response.code())
                        }
                    }

                    override fun onFailure(call: Call<PictureOfTheDayDTO>, t: Throwable) {
                        println()
                    }

                }
            )
    }
}