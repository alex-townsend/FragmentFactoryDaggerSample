package com.atownsend.fragmentfactorysample

import android.app.Activity
import android.app.Application
import com.atownsend.fragmentfactorysample.di.AppComponent
import com.atownsend.fragmentfactorysample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class App : Application(), HasActivityInjector {

  @Inject
  internal lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    Timber.plant(DebugTree())
    appComponent = DaggerAppComponent
        .builder()
        .application(this)
        .build()

    appComponent.inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

}