package com.saidov.cookbook.modules.main.history

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnSearchListener
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.core.fragment.BaseFragment
import com.saidov.cookbook.modules.main.category.details.DetailsDBFragment
import com.saidov.cookbook.modules.main.ui.adapter.DrinkAdapter
import com.saidov.cookbook.modules.main.ui.model.DrinkModel
import com.saidov.cookbook.modules.main.ui.vm.SharedViewModel


class HistoryFragment : BaseFragment(R.layout.fragment_history), View.OnClickListener,
    View.OnLongClickListener, OnToolBarChangedListener, OnSearchListener {

    private var listener: OnToolBarChangedListener? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var drinkAdapter: DrinkAdapter

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        initObservers()
    }


    private fun init(view: View) {
        recyclerView = view.findViewById(R.id.recyclerHistory)
        val manager: LinearLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = manager
        drinkAdapter = DrinkAdapter(this, this)
        recyclerView.adapter = drinkAdapter

    }

    private fun initObservers() {
        viewModel.all.observe(viewLifecycleOwner, Observer {
            drinkAdapter.differ.submitList(it)
        })
        viewModel.loadHistory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener?.setToolbarName(resources.getString(R.string.favorite))
    }

    companion object {
        const val TAG = "MY_LOG"
        fun newInstance(listener: OnToolBarChangedListener): HistoryFragment {
            val fragment: HistoryFragment = HistoryFragment()
            fragment.listener = listener
            return fragment
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            val drink = it.tag as DrinkModel
            sendData(drink)
        }
    }

    private fun sendData(item: DrinkModel) {
        val bundle = Bundle()
        item.strDrinkThumb?.let { bundle.putString("image", it) }
        item.strDrink?.let { bundle.putString("strDrink", it) }
        item.strCategory?.let { bundle.putString("strCategory", it) }
        item.strGlass?.let { bundle.putString("strGlass", it) }
        item.strAlcoholic?.let { bundle.putString("strAlcoholic", it) }
        item.strInstructions?.let { bundle.putString("strInstructions", it) }
        item.strIngredient1?.let { bundle.putString("strIngredient1", it) }
        item.strIngredient2?.let { bundle.putString("strIngredient2", it) }
        item.strIngredient3?.let { bundle.putString("strIngredient3", it) }
        item.strIngredient4?.let { bundle.putString("strIngredient4", it) }
        item.strIngredient5?.let { bundle.putString("strIngredient5", it) }
        item.strMeasure1?.let { bundle.putString("strMeasure1", it) }
        item.strMeasure2?.let { bundle.putString("strMeasure2", it) }
        item.strMeasure3?.let { bundle.putString("strMeasure3", it) }
        item.dateModified?.let { bundle.putString("dateModified", it) }


        val detailFragment = DetailsDBFragment.newInstance(onToolBarChangedListener = this)
        detailFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, detailFragment)
            .addToBackStack(this::class.java.simpleName).commit()
    }

    private fun popupMenu(v: View, item: DrinkModel) {
        val popupMenus = PopupMenu(v.context, v)
        popupMenus.inflate(R.menu.menu_popup_delete)
        popupMenus.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.deletFavorite -> {
                    viewModel.delete(item)
                    true
                }
                else -> true
            }
        }
        popupMenus.show()
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popupMenus)
        menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(menu, true)
    }

    override fun setToolbarName(title: String) {
        TODO("Not yet implemented")
    }

    override fun setToolBarBackVisibility(status: Boolean) {
        TODO("Not yet implemented")
    }

    override fun clearToolBar() {
        TODO("Not yet implemented")
    }

    override fun onLongClick(v: View?): Boolean {
        v?.let {
            //val drink = it.tag as DrinkModel
            //?popupMenu(v, drink)

        }
        return true
    }

    override fun onSearch(query: String) {
        viewModel.search(query)
    }

}