package com.saidov.cookbook.modules.main.history

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.core.fragment.BaseFragment

class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    var listener: OnToolBarChangedListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener?.setToolbarName(resources.getString(R.string.history))
    }

    companion object {
        fun newInstance(listener: OnToolBarChangedListener?): HistoryFragment {
            val fragment: HistoryFragment = HistoryFragment()
            fragment.listener = listener
            return fragment
        }
    }
}