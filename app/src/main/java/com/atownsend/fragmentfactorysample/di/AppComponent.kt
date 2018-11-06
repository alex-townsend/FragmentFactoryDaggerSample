package com.atownsend.fragmentfactorysample.di

import com.atownsend.fragmentfactorysample.App
import com.atownsend.fragmentfactorysample.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

  fun inject(app: App)

  @Component.Builder
  interface Builder {
    fun build(): AppComponent

    @BindsInstance
    fun application(application: App): Builder
  }

}