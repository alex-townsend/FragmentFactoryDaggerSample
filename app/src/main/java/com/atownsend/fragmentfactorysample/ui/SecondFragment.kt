package com.atownsend.fragmentfactorysample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.atownsend.fragmentfactorysample.R
import com.atownsend.fragmentfactorysample.util.ToolbarProvider
import javax.inject.Inject

@SuppressLint("ValidFragment")
class SecondFragment @Inject constructor(
    private val toolbarProvider: ToolbarProvider
) : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_second, container, false)
  }

  override fun onStart() {
    super.onStart()
    toolbarProvider.getToolbar().title = "Second Fragment"
  }
}