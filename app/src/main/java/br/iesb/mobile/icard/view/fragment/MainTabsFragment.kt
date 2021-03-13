package br.iesb.mobile.icard.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentLoginTabsBinding
import br.iesb.mobile.icard.databinding.FragmentMainTabsBinding
import br.iesb.mobile.icard.view.adapter.LoginAdapter
import br.iesb.mobile.icard.view.adapter.MainTabsAdapter
import br.iesb.mobile.icard.view.viewModel.LoginViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_login_tabs.view.*
import kotlinx.android.synthetic.main.fragment_store_list.view.*

@AndroidEntryPoint
@WithFragmentBindings
class MainTabsFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private lateinit var binding: FragmentMainTabsBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.app)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)
        binding = FragmentMainTabsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.layout.menu_action_bar, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.tab_layout
        viewPager = view.view_pager

        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)

        viewPager.adapter = childFragmentManager?.let { MainTabsAdapter(it, tabLayout.tabCount) }

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.translationY = 300F

        tabLayout.setAlpha(0F)


        tabLayout.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(100).start()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                viewModel.deleteLoginLocalData()
                screenLoginTabs()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun screenLoginTabs() {
        findNavController().navigate(R.id.action_mainTabsFragment_to_loginTabsFragment)
    }


}