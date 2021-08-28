package com.dicoding.foodsmapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.foodsapp.core.R
import com.dicoding.foodsapp.core.databinding.ItemListTourismBinding
import com.dicoding.foodsmapp.core.domain.model.CategoriesFood
import java.util.ArrayList

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.ListViewHolder>() {

    private var listData = ArrayList<CategoriesFood>()
    var onItemClick: ((CategoriesFood) -> Unit)? = null

    fun setData(newListData: List<CategoriesFood>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_tourism, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListTourismBinding.bind(itemView)
        fun bind(data: CategoriesFood) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.strCategoryThumb)
                    .into(ivItemImage)
                tvItemTitle.text = data.strCategory
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}