package local.kas.material.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import local.kas.material.R
import local.kas.material.databinding.ActivityMainBinding
import local.kas.material.view.main.MainFragment

//Создайте первый экран приложения с фотографией дня. done
//Добавьте описание (приходит с сервера) под фотографией в виде текстовой подписи или в виде BottomSheet. done
//Добавьте текстовое поле для поиска неизвестных слов в «Википедии». done
//Добавьте адаптивную иконку для вашего приложения. done

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance()).commit()
        }
    }
}