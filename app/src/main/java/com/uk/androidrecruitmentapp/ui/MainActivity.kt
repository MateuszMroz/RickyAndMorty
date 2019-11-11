package com.uk.androidrecruitmentapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import  com.uk.androidrecruitmentapp.R
import com.uk.androidrecruitmentapp.databinding.MainActivityBinding
import com.uk.androidrecruitmentapp.ui.characters.CharactersFragment
import com.uk.androidrecruitmentapp.ui.episodes.EpisodesFragment
import com.uk.androidrecruitmentapp.ui.locations.LocationsFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject



class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatcherAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var binding: MainActivityBinding
    lateinit var navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        navigation = binding.navigation

        if(savedInstanceState == null) {
            loadFragment(EpisodesFragment())
            navigation.selectedItemId = R.id.episodesFragment
        }

        navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.charactersFragment -> {
                    loadFragment(CharactersFragment.newInstance())
                }
                R.id.episodesFragment -> {
                    loadFragment(EpisodesFragment.newInstance())
                }
                R.id.locationsFragment -> {
                    loadFragment(LocationsFragment.newInstance())
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun supportFragmentInjector() = dispatcherAndroidInjector

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }

}