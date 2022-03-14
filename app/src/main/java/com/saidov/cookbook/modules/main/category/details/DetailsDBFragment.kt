package com.saidov.cookbook.modules.main.category.details

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.saidov.cookbook.R
import com.saidov.cookbook.core.callback.OnToolBarChangedListener
import com.saidov.cookbook.core.fragment.BaseFragment

/**
 * Created by MUHAMMADJON SAIDOV on 13,March,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
class DetailsDBFragment : BaseFragment(R.layout.fragment_details) {
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

        floatingActionButton.visibility = View.INVISIBLE

        initData()
    }

    private fun initData() {
        val data = this.arguments
        val image = data?.getString("image")
        val strDrink = data?.getString("strDrink")
        val strCategory = data?.getString("strCategory")
        val strGlass = data?.getString("strGlass")
        val strAlcoholic = data?.getString("strAlcoholic")
        val strInstructions = data?.getString("strInstructions")
        val strIngredient1 = data?.getString("strIngredient1")
        val strIngredient2 = data?.getString("strIngredient2")
        val strIngredient3 = data?.getString("strIngredient3")
        val strIngredient4 = data?.getString("strIngredient4")
        val strIngredient5 = data?.getString("strIngredient5")
        val strMeasure1 = data?.getString("strMeasure1")
        val strMeasure2 = data?.getString("strMeasure2")
        val strMeasure3 = data?.getString("strMeasure3")
        val dateModified = data?.getString("dateModified")


        Glide.with(requireActivity())
            .load(image)
            .into(imageDrink)

        titleDrink.text = "Name: $strDrink"
        categoryDrink.text = "Categopy: $strCategory"
        glassDrink.text = "Glass: $strGlass"
        alcoholicDrink.text = "Alcoholic: $strAlcoholic"
        instructionsDrink.text = "Instructions: $strInstructions"
        ingredient1.text =
            "Ingredients: \n $strIngredient1 \n $strIngredient2 \n $strIngredient3 \n $strIngredient4 \n $strIngredient5"
        measure1.text = "Measures: \n $strMeasure1 \n $strMeasure2 \n $strMeasure3"
        publishDrink.text = "Published: $dateModified"


    }


    companion object {
        const val TAG = "MY_LOG"
        fun newInstance(onToolBarChangedListener: OnToolBarChangedListener): DetailsDBFragment {
            val fragment: DetailsDBFragment = DetailsDBFragment()
            fragment.onToolBarChangedListener = onToolBarChangedListener
            return fragment
        }
    }
}