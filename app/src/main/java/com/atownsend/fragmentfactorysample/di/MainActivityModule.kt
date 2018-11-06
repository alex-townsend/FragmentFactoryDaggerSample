package com.atownsend.fragmentfactorysample.di

import com.atownsend.fragmentfactorysample.MainActivity
import com.atownsend.fragmentfactorysample.util.ToolbarProvider
import dagger.Binds
import dagger.Module

@Module(includes = [FragmentBindingModule::class])
abstract class MainActivityModule {

  @Binds
  @PerActivity
  abstract fun bindsToolbarProvider(mainActivity: MainActivity): ToolbarProvider
}
