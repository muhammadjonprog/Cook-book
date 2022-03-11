package com.saidov.cookbook.modules.main.settings.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.core.fragment.BaseFragment
import com.saidov.cookbook.modules.main.settings.adapter.SettingsCategoryAdapter
import com.saidov.cookbook.modules.main.ui.vm.SharedViewModel

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var settingsAdapter: SettingsCategoryAdapter
    var listener: OnToolBarChangedListener? = null

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        listener()
        initObservers()
    }

    private fun init(view: View) {
        recyclerView = view.findViewById(R.id.recyclerSettings)
        settingsAdapter = SettingsCategoryAdapter()
    }

    private fun listener() {
        val manager: LinearLayoutManager = GridLayoutManager(context, 1)
        recyclerView.layoutManager = manager
        recyclerView.adapter = settingsAdapter
    }

    private fun initObservers() {
        viewModel.settingsCategory.observe(viewLifecycleOwner, Observer {
            settingsAdapter.setItems(it)

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener?.setToolbarName(resources.getString(R.string.settings))
    }

    companion object {
        fun newInstance(listener: OnToolBarChangedListener?): SettingsFragment {
            val fragment: SettingsFragment = SettingsFragment()
            fragment.listener = listener
            return fragment
        }
    }
}