package com.saidov.cookbook.modules.main.favorites

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.core.fragment.BaseFragment


class FavoritesFragment : BaseFragment(R.layout.fragment_favorite) {
    var listener: OnToolBarChangedListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener?.setToolbarName(resources.getString(R.string.favorite))
    }

    companion object {
        fun newInstance(listener: OnToolBarChangedListener?): FavoritesFragment {
            val fragment: FavoritesFragment = FavoritesFragment()
            fragment.listener = listener
            return fragment
        }
    }
}