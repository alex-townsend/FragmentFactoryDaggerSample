package com.atownsend.fragmentfactorysample.di.factory

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.atownsend.fragmentfactorysample.di.PerActivity
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

/**
 * [FragmentFactory] class that takes a map of [Fragment] classes and related
 * Dagger [Provider] instances to create new [Fragment] instances using dependency injection
 */
@PerActivity
class DaggerFragmentInjectionFactory @Inject constructor(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

  init {
    Timber.d("Creating injection factory")
  }

  override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
    val fragmentClass = loadFragmentClass(classLoader, className)
    val creator = creators[fragmentClass]
        ?: throw IllegalArgumentException("Unknown fragment class $fragmentClass")

    try {
      val fragment = creator.get()
      fragment.arguments = args
      return fragment
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}