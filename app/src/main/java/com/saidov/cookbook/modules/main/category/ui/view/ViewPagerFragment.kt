package com.saidov.cookbook.modules.main.category.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnSearchListener
import com.saidov.cookbook.core.callback.OnToolBarChangedListener

import com.saidov.cookbook.core.fragment.BaseFragment
import com.saidov.cookbook.modules.main.category.details.DetailsDBFragment.Companion.TAG
import com.saidov.cookbook.modules.main.category.details.DetailsFragment
import com.saidov.cookbook.modules.main.ui.adapter.DrinkAdapter
import com.saidov.cookbook.modules.main.ui.model.DrinkModel

import com.saidov.cookbook.modules.main.ui.vm.SharedViewModel
import com.saidov.cookbook.repository.networkrepository.event.Status

class ViewPagerFragment() : BaseFragment(R.layout.fragment_viewpager),
    View.OnClickListener, View.OnLongClickListener,
    OnToolBarChangedListener, OnSearchListener {
    lateinit var drinkAdapter: DrinkAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    private var category: String = ""
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initData(view)
        initObservers(view)
    }

    private fun initData(view: View) {
        recyclerView = view.findViewById(R.id.recyclerViewDrink)
        progressBar = view.findViewById(R.id.pbDrink)
        val manager: LinearLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = manager
        drinkAdapter = DrinkAdapter(this, this)
        recyclerView.adapter = drinkAdapter
    }

    private fun initObservers(view: View) {
        viewModel.drinkLiveDataMap[category]?.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "Success")
                    hideProgressBar()
                    response.data?.let { drinkResponse ->
                        drinkAdapter.differ.submitList(drinkResponse.list)
                    }
                }
                Status.ERROR -> {
                    Log.d(TAG, "Error")
                    hideProgressBar()
                    response.error?.let { message ->
                        Snackbar.make(view, "Ошибка : $message", Snackbar.LENGTH_LONG).show()
                    }
                }
                Status.LOADING -> {
                    Log.d(TAG, "Loading ")
                    showProgressBar()
                }
            }
        })
        viewModel.drinkByCategory(category)
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }


    private fun sendData(item: DrinkModel) {
        val bundle = Bundle()
        item.idDrink?.let { bundle.putLong("id", it) }

        val detailFragment = DetailsFragment.newInstance(onToolBarChangedListener = this)
        detailFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailFragment)
            .addToBackStack(this::class.java.simpleName).commit()
    }


    override fun onClick(v: View?) {
        v?.let {
            val drink = it.tag as DrinkModel
            sendData(drink)
            viewModel.add(drink)
        }
    }


    override fun setToolbarName(title: String) {
        activity?.actionBar?.title = title
    }

    override fun setToolBarBackVisibility(status: Boolean) {
        TODO("Not yet implemented")
    }

    override fun clearToolBar() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(category: String): ViewPagerFragment {
            val fragment: ViewPagerFragment = ViewPagerFragment()
            fragment.category = category
            return fragment
        }
    }

    override fun onSearch(query: String) {
        if (query.isEmpty()) {
            viewModel.drinkByCategory(category)
        }
        viewModel.searchByDrinkName(query = query, category = category)
    }

    override fun onLongClick(p0: View?): Boolean {
        return true
    }

}
