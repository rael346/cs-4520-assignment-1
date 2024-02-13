package com.cs4520.assignment1.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment1.R
import com.cs4520.assignment1.data.Product

class ProductAdapter : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback) {
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productNameTextView: TextView = itemView.findViewById(R.id.productName)
        private val productImageView: ImageView = itemView.findViewById(R.id.productImage)
        private val productDateTextView: TextView = itemView.findViewById(R.id.productDate)
        private val productPriceTextView: TextView = itemView.findViewById(R.id.productPrice)

        private var currentProduct: Product? = null

        fun bind(product: Product) {
            currentProduct = product
            productNameTextView.text = product.name
            productDateTextView.text = product.date
            productPriceTextView.text = product.price.toString()
            if (product.productType == "Equipment") {
                productImageView.setImageResource(R.drawable.equipment)
                itemView.setBackgroundResource(R.color.light_red)
            }

            if (product.productType == "Food") {
                productImageView.setImageResource(R.drawable.food)
                itemView.setBackgroundResource(R.color.light_yellow)
            }
        }
    }

    /* Creates and inflates view and return Product View Holder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_product_item, parent, false)
        return ProductViewHolder(view)
    }

    /* Gets current product and uses it to bind view. */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }
}

object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.name == newItem.name
    }
}