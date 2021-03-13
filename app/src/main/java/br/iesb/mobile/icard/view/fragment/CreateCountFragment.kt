package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentCreateCountBinding
import br.iesb.mobile.icard.view.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_create_count.*
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.icard.domain.login.LoginData
import br.iesb.mobile.icard.domain.login.LoginResult
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class CreateCountFragment : Fragment() {

    private lateinit var binding: FragmentCreateCountBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentCreateCountBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = etEmailCreate
        val password = etPasswordConfimation
        val confirmPassword = etPasswordConfimation
        val createCount = btCreateCount

        email.translationX = 800F
        password.translationX = 800F
        confirmPassword.translationX = 800F
        createCount.translationX = 800F

        email.setAlpha(0F)
        password.setAlpha(0F)
        confirmPassword.setAlpha(0F)
        createCount.setAlpha(0F)


        email.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(300).start()
        password.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(400).start()
        confirmPassword.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(500)
            .start()
        createCount.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(600).start()

        btCreateCount.setOnClickListener { createCout() }

        viewModel.responseLogin.observe(viewLifecycleOwner){ response ->
            showMensagemLogin(response)
            screenMainTabs()
        }
    }

    fun createCout(){
        val email = etEmailCreate.text.toString()
        val password = etPasswordCreate.text.toString()
        val confirmPassword = etPasswordConfimation.text.toString()

        val data = LoginData(email, password, confirmPassword)

        viewModel.createCount(data)
    }

    fun showMensagemLogin(response: LoginResult){
        if(response.error != null) {
            Toast.makeText(context, response.error, Toast.LENGTH_LONG).show()
            return
        } else {
            Toast.makeText(context, response.result, Toast.LENGTH_LONG).show()
            return
        }
    }

    fun screenMainTabs(){
        findNavController().navigate(R.id.action_loginTabsFragment_to_mainTabsFragment)
    }


}