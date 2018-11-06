package com.atownsend.fragmentfactorysample.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.atownsend.fragmentfactorysample.R
import com.atownsend.fragmentfactorysample.R.layout
import com.atownsend.fragmentfactorysample.util.ToolbarProvider
import com.atownsend.fragmentfactorysample.util.instantiate
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

@SuppressLint("ValidFragment")
class MainFragment @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val toolbarProvider: ToolbarProvider
) : Fragment() {

  init {
    Timber.d("Instantiating fragment")
  }

  @SuppressLint("ApplySharedPref")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Timber.d("onCreate: contains state ${savedInstanceState?.getString("test")}")
    if (savedInstanceState == null) {
      sharedPreferences.edit()
          .putLong("last_new_launch", Date().time)
          .commit()
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(layout.fragment_main, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    lastLaunchText.text = DateFormat.getDateTimeInstance()
        .format(Date(sharedPreferences.getLong("last_new_launch", 0L)))

    nextFragmentButton.setOnClickListener {
      requireFragmentManager().transaction {
        replace(R.id.fragment_container,
            // extension function for easier instantiation
            requireFragmentManager().instantiate<SecondFragment>())
        addToBackStack(null)
      }
    }
  }

  override fun onStart() {
    super.onStart()
    toolbarProvider.getToolbar().title = "Main Fragment"
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    Timber.d("onSaveInstanceState: saving state")
    outState.putString("test", "test")
  }
}