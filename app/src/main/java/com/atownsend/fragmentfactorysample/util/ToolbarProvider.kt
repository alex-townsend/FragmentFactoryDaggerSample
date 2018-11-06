package com.atownsend.fragmentfactorysample.util

import androidx.appcompat.widget.Toolbar

/**
 * Interface for providing a reference to a Toolbar
 */
interface ToolbarProvider {
  fun getToolbar(): Toolbar
}