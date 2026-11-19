package com.example.myrecipestinorua

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipestinorua.data.remote.Category
import com.example.myrecipestinorua.databinding.CategoryItemBinding

class CategoryAdapter(private val categories: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var onCategoryItemClickedListener: OnCategoryItemClickedListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CategoryViewHolder(
        CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(categories[position])

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.category = category
            binding.categoryItemCl.setOnClickListener {
                onCategoryItemClickedListener?.onCategoryItemClicked(category)
            }
            binding.executePendingBindings()
        }
    }

    fun setCategoryItemClickedListener(listener: OnCategoryItemClickedListener) {
        this.onCategoryItemClickedListener = listener
    }
}
