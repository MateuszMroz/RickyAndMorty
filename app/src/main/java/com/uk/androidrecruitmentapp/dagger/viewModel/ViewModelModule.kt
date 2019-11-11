package com.uk.androidrecruitmentapp.dagger.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uk.androidrecruitmentapp.ui.characters.CharactersViewModel
import com.uk.androidrecruitmentapp.ui.episodes.EpisodesViewModel
import com.uk.androidrecruitmentapp.ui.locations.LocationViewModel
import com.uk.androidrecruitmentapp.view_model_factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    abstract fun bindEpisodeViewModel(viewModel: EpisodesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindLocationViewModel(viewModel: LocationViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}