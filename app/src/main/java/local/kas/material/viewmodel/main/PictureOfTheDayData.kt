package local.kas.material.viewmodel.main

import local.kas.material.repository.PDOServerResponse

sealed class PictureOfTheDayData {
    data class Success(val pdoServerResponse: PDOServerResponse):PictureOfTheDayData()
    data class Error(val pdoServerResponse: PDOServerResponse):PictureOfTheDayData()
    data class Loading(val progress: Int) : PictureOfTheDayData()
}
