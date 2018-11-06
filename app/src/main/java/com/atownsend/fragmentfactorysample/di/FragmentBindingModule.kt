package com.atownsend.fragmentfactorysample.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.atownsend.fragmentfactorysample.di.factory.DaggerFragmentInjectionFactory
import com.atownsend.fragmentfactorysample.ui.MainFragment
import com.atownsend.fragmentfactorysample.ui.SecondFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBindingModule {

  @Binds
  @IntoMap
  @FragmentKey(MainFragment::class)
  abstract fun bindMainFragment(mainFragment: MainFragment): Fragment

  @Binds
  @IntoMap
  @FragmentKey(SecondFragment::class)
  abstract fun bindSecondFragment(secondFragment: SecondFragment): Fragment

  @Binds
  abstract fun bindFragmentFactory(factory: DaggerFragmentInjectionFactory): FragmentFactory

}