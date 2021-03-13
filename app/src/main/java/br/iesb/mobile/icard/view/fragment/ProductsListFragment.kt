package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentProductsBinding
import br.iesb.mobile.icard.databinding.FragmentStoreListBinding
import br.iesb.mobile.icard.view.adapter.ProductsAdapter
import br.iesb.mobile.icard.view.adapter.StoreAdapter
import br.iesb.mobile.icard.view.viewModel.ProductsListViewModel
import br.iesb.mobile.icard.view.viewModel.StoreListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlin.properties.Delegates

@AndroidEntryPoint
@WithFragmentBindings
class ProductsListFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductsListViewModel by lazy {
        ViewModelProvider(this).get(ProductsListViewModel::class.java)
    }

    lateinit var imageUrl: String
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()
    var idStore by Delegates.notNull<Int>()
    lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idStore = requireArguments().getInt("idStore")
        imageUrl = requireArguments().getString("imageUrl").toString()
        latitude = requireArguments().getDouble("latitude")
        longitude = requireArguments().getDouble("longitude")

        bundle = bundleOf(
            "idStore" to idStore,
            "imageUrl" to imageUrl,
            "latitude" to latitude,
            "longitude" to longitude
        )

        binding.progressbar.visibility = View.VISIBLE
        recycleViewProducts()
    }

    private fun recycleViewProducts() {
        val recycleListView = binding.rcProductsList
        recycleListView.layoutManager = LinearLayoutManager(context)

        var id = requireArguments().getInt("idStore")

        viewModel.productsList.observe(viewLifecycleOwner) { list ->

            binding.progressbar.visibility = View.GONE

            recycleListView.adapter = ProductsAdapter(list) {

            }
        }

        viewModel.productsList(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home ->{
                screenDetailStore()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun screenDetailStore() {
        findNavController().navigate(R.id.action_productsListFragment_to_storeDetailFragment, bundle)
    }

}