package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentStoreListBinding
import br.iesb.mobile.icard.view.adapter.StoreAdapter
import br.iesb.mobile.icard.view.viewModel.StoreListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_store_list.view.*

@AndroidEntryPoint
@WithFragmentBindings
class StoreListFragment : Fragment() {

    private lateinit var binding: FragmentStoreListBinding
    private val viewModel: StoreListViewModel by lazy {
        ViewModelProvider(this).get(StoreListViewModel::class.java)
    }
    lateinit var recicle: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStoreListBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressbar.visibility = View.VISIBLE

        recicle = view.rcStoreList
        recicle.translationY = 300F
        recicle.setAlpha(0F)
        recicle.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()

        recycleViewStore()
    }

    private fun recycleViewStore() {
        val recycleListView = binding.rcStoreList
        recycleListView.layoutManager = LinearLayoutManager(context)

        viewModel.storeList.observe(viewLifecycleOwner) { list ->

            binding.progressbar.visibility = View.GONE

            recycleListView.adapter = StoreAdapter(list) {

                var bundle = bundleOf(
                    "idStore" to it.id,
                    "imageUrl" to it.imageUrl,
                    "longitude" to it.longitude,
                    "latitude" to it.latitude
                )
                findNavController().navigate(R.id.action_mainTabsFragment_to_storeDetailFragment, bundle)
            }
        }

        viewModel.storeList()
    }


}