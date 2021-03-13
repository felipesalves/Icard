package br.iesb.mobile.icard.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.ItemProductsBinding
import br.iesb.mobile.icard.domain.product.Products
import com.squareup.picasso.Picasso

class ProductsAdapter (
    private val productsList: List<Products>,
    private val productsItemClick: ((Products) -> Unit)
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.products = productsList[position]
        binding?.executePendingBindings()

        if (binding != null) {
            Picasso.get().load(binding?.products?.imageUrl).into(binding.imgProducts)
        }
    }


    override fun getItemCount(): Int = productsList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemProductsBinding? = ItemProductsBinding.bind(view)

        init {
            view.setOnClickListener{
                productsItemClick.invoke(productsList[adapterPosition])
            }
        }
    }

}