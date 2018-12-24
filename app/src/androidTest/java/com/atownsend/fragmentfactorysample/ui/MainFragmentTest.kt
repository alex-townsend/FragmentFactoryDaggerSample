package com.atownsend.fragmentfactorysample.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.atownsend.fragmentfactorysample.R.id
import com.atownsend.fragmentfactorysample.util.ToolbarProvider
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

  lateinit var toolbarProvider: ToolbarProvider
  lateinit var sharedPreferences: SharedPreferences

  @Before
  fun setup() {
    val context = InstrumentationRegistry.getInstrumentation().context
    val toolbar = Toolbar(context)
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    toolbarProvider = object : ToolbarProvider {
      override fun getToolbar(): Toolbar = toolbar
    }
  }

  @Test
  fun testMainFragmentLoad() {
    val factory = object : FragmentFactory() {
      override fun instantiate(classLoader: ClassLoader, className: String,
          args: Bundle?): Fragment {
        return MainFragment(sharedPreferences, toolbarProvider)
      }
    }
    val scenario = launchFragmentInContainer<MainFragment>(null, factory)

    scenario.moveToState(Lifecycle.State.RESUMED)
    Assert.assertEquals("Main Fragment", toolbarProvider.getToolbar().title)
    onView(withId(id.lastLaunchText)).check(matches(not(withText(""))))
  }

}