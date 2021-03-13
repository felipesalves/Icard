package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentRecoverPasswordBinding
import br.iesb.mobile.icard.domain.login.LoginData
import br.iesb.mobile.icard.domain.login.LoginResult
import br.iesb.mobile.icard.view.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_create_count.*
import kotlinx.android.synthetic.main.fragment_create_count.btCreateCount
import kotlinx.android.synthetic.main.fragment_recover_password.*
import retrofit2.Response.error

@AndroidEntryPoint
@WithFragmentBindings
class RecoverPasswordFragment : Fragment() {

    private lateinit var binding: FragmentRecoverPasswordBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecoverPasswordBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btCreateCount.setOnClickListener { forgotPassword() }

        viewModel.responseLogin.observe(viewLifecycleOwner) { response ->
            showMensagemLogin(response)
        }

    }

    fun forgotPassword(){
        val email = etEmailForgot.text.toString()
        val data = LoginData(email, "", "")

        viewModel.forgotPassword(data)
    }

    fun showMensagemLogin(response: LoginResult){
        if(response.error != null) {
            Toast.makeText(context, response.error, Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(context, response.result, Toast.LENGTH_LONG).show()
        return
    }

}