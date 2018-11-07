package com.atownsend.fragmentfactorysample

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.atownsend.fragmentfactorysample.ui.MainFragment
import com.atownsend.fragmentfactorysample.util.ToolbarProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector,
    ToolbarProvider {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  @Inject
  lateinit var fragmentFactory: FragmentFactory

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

  override fun getToolbar(): Toolbar = toolbar

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    // must set this BEFORE super.onCreate() so that the custom fragment factory is used in
    // re-creating fragments after rotation or process death
    supportFragmentManager.fragmentFactory = fragmentFactory
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setSupportActionBar(toolbar)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.fragment_container,
              // use the attached fragment factory to instantiate our new fragment
              // see MainFragment for usage of an extension function to accomplish same thing
              supportFragmentManager.fragmentFactory.instantiate(
                  MainFragment::class.java.classLoader!!,
                  MainFragment::class.java.name,
                  null)
          ).commit()
    }

    supportFragmentManager.addOnBackStackChangedListener { handleUpNav() }
    handleUpNav()
  }

  private fun handleUpNav() {
    supportActionBar!!.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) supportFragmentManager.popBackStack()
    return super.onOptionsItemSelected(item)
  }
}
