package com.saidov.cookbook.modules.main.settings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.saidov.cookbook.R
import com.saidov.cookbook.modules.main.settings.model.SettingsCategoryModel
import com.saidov.cookbook.modules.main.ui.model.CategoryModelList
import com.saidov.cookbook.repository.networkrepository.event.Resource


/**
 * Created by MUHAMMADJON SAIDOV on 07,февраль,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */

class SettingsCategoryAdapter :
    RecyclerView.Adapter<SettingsCategoryAdapter.MyViewHolder>() {

    private var items: ArrayList<SettingsCategoryModel> = ArrayList()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var checkBox: MaterialCheckBox = itemView.findViewById(R.id.checkbox)

        fun bind(categoryModel: SettingsCategoryModel) {
            checkBox.isChecked = categoryModel.isChecked
            checkBox.text = categoryModel.strCategory
            checkBox.setOnCheckedChangeListener{button,isCheched->
                categoryModel.isChecked = isCheched
            }
        }


    }

    fun setItems(arrayList: ArrayList<SettingsCategoryModel>) {
        items = arrayList
        notifyItemInserted(itemCount)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.checkbox_item, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind((items[position]))

    }

    override fun getItemCount(): Int {
        return items.size
    }
}