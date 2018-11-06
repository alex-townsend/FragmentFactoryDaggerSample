package com.atownsend.fragmentfactorysample.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Extension function for instantiate a Fragment using the [FragmentFactory] attached to a
 * [FragmentManager]
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T : Fragment> FragmentManager.instantiate(args: Bundle? = null): T {
  return fragmentFactory.instantiate(T::class.java.classLoader!!, T::class.java.name, args) as T
}