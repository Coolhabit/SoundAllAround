package ru.coolhabit.soundallaround

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.coolhabit.soundallaround.R
import ru.coolhabit.soundallaround.data.entity.ResultAlbums
import ru.coolhabit.soundallaround.databinding.ActivityMainBinding
import ru.coolhabit.soundallaround.view.fragments.DetailsFragment
import ru.coolhabit.soundallaround.view.fragments.HomeFragment

private const val KEY = "album"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .commit()
    }

    fun launchDetailsFragment(resultAlbums: ResultAlbums) {
        val bundle = Bundle()
        bundle.putParcelable(KEY, resultAlbums)
        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }
}