package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentLoadAppBinding
import br.iesb.mobile.icard.databinding.FragmentLoginBinding
import br.iesb.mobile.icard.view.viewModel.LoadAppViewModel
import br.iesb.mobile.icard.view.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_load_app.*

@AndroidEntryPoint
@WithFragmentBindings
class LoadAppFragment : Fragment() {


    private lateinit var binding: FragmentLoadAppBinding
    private val viewModel: LoadAppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.hide()

        binding = FragmentLoadAppBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgLogo.translationY = 800F
        tvNameLogo.translationY = 800F

        imgLogo.setAlpha(0F)
        tvNameLogo.setAlpha(0F)

        imgLogo.animate().translationY(0F).alpha(1F).setDuration(800).setStartDelay(40).start()
        tvNameLogo.animate().translationY(0F).alpha(1F).setDuration(800).setStartDelay(50).start()

        searchLoginLocalData()
    }

    private fun searchLoginLocalData() {
        viewModel.responsePersonLogin.observe(viewLifecycleOwner){
            if (it.isLogin){
                screenMainTabs()
            } else {
                screenLogin()
            }
        }
        viewModel.searchLoginLocalData()
    }

    fun screenMainTabs() {
        findNavController().navigate(R.id.action_loadAppFragment_to_mainTabsFragment)
    }

    fun screenLogin() {
        findNavController().navigate(R.id.action_loadAppFragment_to_loginTabsFragment)
    }

}