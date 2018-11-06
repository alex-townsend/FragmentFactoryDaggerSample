package com.atownsend.fragmentfactorysample

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.atownsend.fragmentfactorysample.di.MainActivityModule
import com.atownsend.fragmentfactorysample.di.PerActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
abstract class AppModule {

  @PerActivity
  @ContributesAndroidInjector(modules = [MainActivityModule::class])
  internal abstract fun mainActivityInjector(): MainActivity

  @Module
  companion object {
    @Provides
    @JvmStatic
    fun provideSharedPreferences(
        app: App): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
  }


}