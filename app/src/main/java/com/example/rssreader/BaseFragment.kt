package com.example.rssreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {
    protected abstract val layout: Int

    protected var toolbar: Toolbar? = null

    protected val navController: NavController
        get() = findNavController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(layout, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        toolbar?.apply {
            // this line must be before setSupportActionBar()
            title = ""

            val activity = requireActivity() as AppCompatActivity
            activity.setSupportActionBar(toolbar)

            // this line must be after setSupportActionBar()
            setNavigationOnClickListener { navController.navigateUp() }
        }

        return view
    }

    fun setTitle(@StringRes resId: Int) {
        toolbar?.setTitle(resId)
    }

    fun setTitle(title: String) {
        toolbar?.title = title
    }

    fun setNavigationIcon(@DrawableRes resId: Int) {
        toolbar?.setNavigationIcon(resId)
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val v = view?.rootView ?: return
        requireActivity().hideKeyboard(v)
    }
}