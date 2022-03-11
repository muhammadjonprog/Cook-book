package com.saidov.cookbook.modules.main.category.ui.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.core.fragment.BaseFragment
import com.saidov.cookbook.modules.main.category.ui.adapter.ViewPagerAdapter
import com.saidov.cookbook.modules.main.category.ui.model.TabLayoutModel
import com.saidov.cookbook.modules.main.ui.vm.SharedViewModel

class CategoryFragment : BaseFragment(R.layout.fragment_category) {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    var listener: OnToolBarChangedListener? = null
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData(view)
    }

//    override fun onSearch(query: String) {
//        if (isResumed) {
//            val pageActive = pagerAdapter.getFragment(tabLayout.selectedTabPosition) as OnSearchListener
//            pageActive.onSearch(query)
//        }
//    }

    private fun initData(view: View) {
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewpager)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewModel.settingsCategory.value?.let {
            for (i in it) {
                if (i.isChecked) {
                    viewModel.addToHash(i.strCategory)
                    viewPagerAdapter.addFragment(
                        TabLayoutModel(
                            ViewPagerFragment.newInstance(
                                i.strCategory), i.strCategory
                        )
                    )
                } else {
                    viewModel.removeFromHash(i.strCategory)
                }
            }
        }
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewPagerAdapter.getTableLayoutTitle(position)
        }.attach()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener?.setToolbarName(resources.getString(R.string.category))
    }

    companion object {
        fun newInstance(listener: OnToolBarChangedListener?): CategoryFragment {
            val fragment: CategoryFragment = CategoryFragment()
            fragment.listener = listener
            return fragment
        }
    }
}