package com.uk.androidrecruitmentapp.dagger.module

import com.uk.androidrecruitmentapp.ui.characters.CharactersFragment
import com.uk.androidrecruitmentapp.ui.episodes.EpisodesFragment
import com.uk.androidrecruitmentapp.ui.locations.LocationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeEpisodeFragment(): EpisodesFragment

    @ContributesAndroidInjector
    abstract fun contributeLocationsFragment(): LocationsFragment

    @ContributesAndroidInjector
    abstract fun contributeCharactersFragment(): CharactersFragment
}