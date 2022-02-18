package local.kas.material.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import local.kas.material.R
import local.kas.material.view.main.MainFragment

//Создайте первый экран приложения с фотографией дня.
//Добавьте описание (приходит с сервера) под фотографией в виде текстовой подписи или в виде BottomSheet.
//Добавьте текстовое поле для поиска неизвестных слов в «Википедии».
//Добавьте адаптивную иконку для вашего приложения.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainFragment.newInstance()
    }
}