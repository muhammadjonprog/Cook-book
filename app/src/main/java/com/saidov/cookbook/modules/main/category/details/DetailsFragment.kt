package com.saidov.cookbook.modules.main.category.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.core.fragment.BaseFragment
import com.saidov.cookbook.modules.main.ui.model.DrinkModel
import com.saidov.cookbook.modules.main.ui.vm.SharedViewModel
import com.saidov.cookbook.repository.networkrepository.event.Status

class DetailsFragment : BaseFragment(R.layout.fragment_details), View.OnClickListener {

    private val viewModel: SharedViewModel by activityViewModels()
    private var onToolBarChangedListener: OnToolBarChangedListener? = null
    private lateinit var imageDrink: ImageView
    private lateinit var titleDrink: TextView
    private lateinit var categoryDrink: TextView
    private lateinit var glassDrink: TextView
    private lateinit var instructionsDrink: TextView
    private lateinit var ingredient1: TextView
    private lateinit var measure1: TextView
    private lateinit var publishDrink: TextView
    private lateinit var alcoholicDrink: TextView

    private lateinit var floatingActionButton: FloatingActionButton
    private var idDrink: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        floatingActionButton = view.findViewById(R.id.floatingActionButton)
        imageDrink = view.findViewById(R.id.imageViewDetail)
        titleDrink = view.findViewById(R.id.titleDrink)
        categoryDrink = view.findViewById(R.id.categoryDrink)
        glassDrink = view.findViewById(R.id.glassDrink)
        instructionsDrink = view.findViewById(R.id.instructionsDrink)
        ingredient1 = view.findViewById(R.id.ingredient1)
        measure1 = view.findViewById(R.id.Measure1)
        alcoholicDrink = view.findViewById(R.id.alcoholicDrink)
        publishDrink = view.findViewById(R.id.publishDrink)

        initObservers(view)

        floatingActionButton.setOnClickListener(this)
    }


    private fun initObservers(view: View) {
        val data = this.arguments
        idDrink = data?.getLong("id")!!
        Log.d(TAG, idDrink.toString())
        viewModel.drinkDetails.observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    response.data?.let { drinkResponse ->

                        Glide.with(requireActivity())
                            .load(drinkResponse.list[0].strDrinkThumb)
                            .into(imageDrink)
                        floatingActionButton.tag = drinkResponse.list[0]
                        titleDrink.text = "Name: ${drinkResponse.list.get(0).strDrink}"
                        categoryDrink.text = "Categopy: ${drinkResponse.list.get(0).strCategory}"
                        glassDrink.text = "Glass: ${drinkResponse.list.get(0).strGlass}"
                        instructionsDrink.text =
                            "Instructions: \n ${drinkResponse.list.get(0).strInstructions}"
                        alcoholicDrink.text = "Alcoholic: ${drinkResponse.list.get(0).strAlcoholic}"
                        ingredient1.text =
                            "Ingredients: \n ${drinkResponse.list[0].strIngredient1} " +
                                    "\n" + "${drinkResponse.list[0].strIngredient2}"
                        "\n" + "${drinkResponse.list[0].strIngredient3}"
                        measure1.text = "Measures: \n ${drinkResponse.list[0].strMeasure1} " +
                                "\n" + "${drinkResponse.list[0].strMeasure2}"
                        "\n" + "${drinkResponse.list[0].strMeasure3}"
                        publishDrink.text = "Published: ${drinkResponse.list.get(0).dateModified}"

                    }
                }
                Status.ERROR -> {

                    response.error?.let { message ->
                        Snackbar.make(view, "Ошибка : $message", Snackbar.LENGTH_LONG).show()
                    }
                }
                Status.LOADING -> {

                }
            }
        })

        viewModel.drinkById(id = idDrink)
    }


    override fun onClick(v: View?) {

        if (v?.id == R.id.floatingActionButton) {
            v?.let {
                val drink = it.tag as DrinkModel
                drink.isFavorite = true
                viewModel.updateFav(drink)
            }
        }
        v?.let {
            val drink = it.tag as DrinkModel
            viewModel.add(drink)
        }
    }


    companion object {
        const val TAG = "MY_LOG"
        fun newInstance(onToolBarChangedListener: OnToolBarChangedListener): DetailsFragment {
            val fragment: DetailsFragment = DetailsFragment()
            fragment.onToolBarChangedListener = onToolBarChangedListener
            return fragment
        }
    }
}