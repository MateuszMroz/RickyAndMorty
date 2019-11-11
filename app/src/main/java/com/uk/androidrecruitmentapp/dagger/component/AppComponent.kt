package com.uk.androidrecruitmentapp.dagger.component

import android.app.Application
import com.uk.androidrecruitmentapp.dagger.module.MainActivityModule
import com.uk.androidrecruitmentapp.dagger.module.NetworkModule
import com.uk.androidrecruitmentapp.dagger.viewModel.ViewModelModule
import com.uk.androidrecruitmentapp.ARApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    MainActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(arApplication: ARApplication)
}