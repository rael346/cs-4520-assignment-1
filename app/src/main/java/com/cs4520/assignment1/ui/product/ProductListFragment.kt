package com.cs4520.assignment1.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment1.R
import com.cs4520.assignment1.data.Product
import com.cs4520.assignment1.data.productsDataset

class ProductListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)
        val productAdapter = ProductAdapter()

        if (view is RecyclerView) {
            view.adapter = productAdapter
            val productList = productsDataset.map {
                it -> Product(it[0] as String, it[1] as String, it[2] as String?, it[3] as Int)
            }
            productAdapter.submitList(productList as MutableList<Product>)
        }

        return view
    }
}