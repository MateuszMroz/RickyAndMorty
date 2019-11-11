package com.uk.androidrecruitmentapp.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.uk.androidrecruitmentapp.R


open class BaseFragment: Fragment() {

    protected fun showError(message: String?, view: View) {
        Snackbar.make(view,
                message ?: getString(R.string.unknown_message), Snackbar.LENGTH_LONG).show()
    }
}