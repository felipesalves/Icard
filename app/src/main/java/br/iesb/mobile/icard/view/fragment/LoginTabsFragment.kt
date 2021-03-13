@file:Suppress("DEPRECATION")

package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentLoginTabsBinding
import br.iesb.mobile.icard.domain.login.LoginResult
import br.iesb.mobile.icard.view.adapter.LoginAdapter
import br.iesb.mobile.icard.view.viewModel.LoginViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_login_tabs.view.*

@AndroidEntryPoint
@WithFragmentBindings
class LoginTabsFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private lateinit var binding: FragmentLoginTabsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding = FragmentLoginTabsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayout = view.tab_layout
        viewPager = view.view_pager
        val copyRight = view.tvCopyRight

        tabLayout.addTab(tabLayout.newTab())
        tabLayout.addTab(tabLayout.newTab())
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)

        viewPager.adapter = childFragmentManager?.let { LoginAdapter(it, tabLayout.tabCount) }

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        copyRight.translationY = 300F
        tabLayout.translationY = 300F

        copyRight.setAlpha(0F)
        tabLayout.setAlpha(0F)

        copyRight.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()
        tabLayout.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(100).start()

        return
    }


}