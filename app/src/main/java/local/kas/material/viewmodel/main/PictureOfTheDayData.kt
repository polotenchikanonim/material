package local.kas.material.viewmodel.main

import local.kas.material.repository.picture_of_day.PictureOfTheDayDTO

sealed class PictureOfTheDayData {
    data class Success(val pdoServerResponse: PictureOfTheDayDTO) : PictureOfTheDayData()
    data class Error(val pdoServerResponse: PictureOfTheDayDTO) : PictureOfTheDayData()
    data class Loading(val progress: Int) : PictureOfTheDayData()
}
