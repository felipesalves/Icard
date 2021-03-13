package br.iesb.mobile.icard.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.ItemStoreBinding
import br.iesb.mobile.icard.domain.store.Store
import com.squareup.picasso.Picasso

class StoreAdapter (
    private val storeList: List<Store>,
    private val storeItemClick: ((Store) -> Unit)
) : RecyclerView.Adapter<StoreAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_store, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.store = storeList[position]
        binding?.executePendingBindings()

        if (binding != null) {
            Picasso.get().load(binding?.store?.imageUrl).into(binding.imgStore)
        }
    }


    override fun getItemCount(): Int = storeList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemStoreBinding? = ItemStoreBinding.bind(view)

        init {
            view.setOnClickListener{
                storeItemClick.invoke(storeList[adapterPosition])
            }
        }
    }


}