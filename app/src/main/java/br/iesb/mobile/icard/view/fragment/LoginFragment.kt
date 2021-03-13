package br.iesb.mobile.icard.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.databinding.FragmentLoginBinding
import br.iesb.mobile.icard.domain.login.LoginData
import br.iesb.mobile.icard.domain.login.LoginResult
import br.iesb.mobile.icard.view.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
@WithFragmentBindings
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = etEmailLogin
        val password = etPasswordLogin
        val forgotPassword = tvRecoverPassword
        val btLogin = btCreateCount

        email.translationX = 800F
        password.translationX = 800F
        forgotPassword.translationX = 800F
        btLogin.translationX = 800F

        email.setAlpha(0F)
        password.setAlpha(0F)
        forgotPassword.setAlpha(0F)
        btLogin.setAlpha(0F)

        email.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(300).start()
        password.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(400).start()
        forgotPassword.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(500)
            .start()
        btLogin.animate().translationX(0F).alpha(1F).setDuration(800).setStartDelay(600).start()

        tvRecoverPassword.setOnClickListener { screenRecoverPassword() }
        btLogin.setOnClickListener { login() }
    }

    private fun screenRecoverPassword() {
        findNavController().navigate(R.id.action_loginTabsFragment_to_recoverPasswordFragment)
    }

    private fun login() {
        val email = etEmailLogin.text.toString().trim()
        val password = etPasswordLogin.text.toString().trim()
        val data = LoginData(email, password, "")

        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)

            val returnLogin = viewModel.responseLogin.value
            if (returnLogin != null) {
                responseLogin(returnLogin)
            }
        }

        viewModel.login(data)
    }


    fun responseLogin(res: LoginResult) {
        if (res.error != null) {

            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
            builder.setMessage(res.error)
            var alerta = builder.create()
            alerta.show();
            return
        }
        screenMainTabs()
    }

    fun screenMainTabs() {
        findNavController().navigate(R.id.action_loginTabsFragment_to_mainTabsFragment)
    }

}