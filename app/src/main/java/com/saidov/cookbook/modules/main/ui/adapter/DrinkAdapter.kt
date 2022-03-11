package com.saidov.cookbook.modules.main.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saidov.cookbook.R
import com.saidov.cookbook.modules.main.ui.model.DrinkModel

/**
 * Created by MUHAMMADJON SAIDOV on 05,March,2022
 * saidov.developer@gmail.com
 * http://muhammad.com/
 */
class DrinkAdapter(
    val onClickListener: View.OnClickListener) :
    RecyclerView.Adapter<DrinkAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val itemTitle: TextView = itemView.findViewById<TextView>(R.id.itemTitleDrink)

        private val itemImage: ImageView = itemView.findViewById<ImageView>(R.id.imageDrink)

        fun bind() {
            val drink = differ.currentList[adapterPosition]
            itemTitle.text = drink.strDrink

            Glide
                .with(itemView.context)
                .load(drink.strDrinkThumb)
                .into(itemImage)

            itemView.setOnClickListener(onClickListener)

            itemView.tag = drink
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<DrinkModel>() {
        override fun areItemsTheSame(oldItem: DrinkModel, newItem: DrinkModel): Boolean {
            return oldItem.idDrink == newItem.idDrink
        }

        override fun areContentsTheSame(oldItem: DrinkModel, newItem: DrinkModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.drink_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}