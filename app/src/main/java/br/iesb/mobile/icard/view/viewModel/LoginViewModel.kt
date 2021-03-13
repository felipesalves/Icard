package br.iesb.mobile.icard.view.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.icard.R
import br.iesb.mobile.icard.domain.LoginLocalData.Person
import br.iesb.mobile.icard.domain.login.LoginData
import br.iesb.mobile.icard.domain.login.LoginResult
import br.iesb.mobile.icard.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    val app: Application,
    private val interactor: LoginInteractor
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val responseLogin = MutableLiveData<LoginResult>()

    var responsePersonLogin = MutableLiveData<Person>()

    fun login(data: LoginData) {
        launch {
            val responseInteractor = interactor.login(data)
            if (responseInteractor.error != null) {
                when (responseInteractor.error) {
                    "ERROR_EMAIL_VAZIO" -> {
                        responseInteractor.error = app.getString(R.string.mandatory_Email)
                    }
                    "ERROR_SENHA_VAZIO" -> {
                        responseInteractor.error = app.getString(R.string.mandatory_Password)
                    }
                    "ERROR_SENHA_MENOR_QUE_6" -> {
                        responseInteractor.error = app.getString(R.string.shorter_Password)
                    }
                    "LOGIN_FIREBASE_ERROR" -> {
                        responseInteractor.error = app.getString(R.string.login_error)
                    }
                }
            } else {
                insertLoginLocalData(data)
                responseInteractor.error = null
            }

            responseLogin.value = responseInteractor

        }
    }

    fun createCount(data: LoginData) {

        launch {
            val responseInteractor = interactor.createCount(data)
            if (responseInteractor.error != null) {
                when (responseInteractor.error) {
                    "ERROR_EMAIL_VAZIO" -> {
                        responseInteractor.error = app.getString(R.string.mandatory_Email)
                    }
                    "ERROR_SENHA_VAZIO" -> {
                        responseInteractor.error = app.getString(R.string.mandatory_Password)
                    }
                    "ERROR_SENHA_MENOR_QUE_6" -> {
                        responseInteractor.error = app.getString(R.string.shorter_Password)
                    }
                    "LOGIN_FIREBASE_ERROR" -> {
                        responseInteractor.error = app.getString(R.string.login_error)
                    }
                    "ERROR_SENHA_NAO_CONFERE" -> {
                        responseInteractor.error = app.getString(R.string.password_Does_not_match)
                    }
                    "ERROR_CONFIRMAR_SENHA_VAZIO" -> {
                        responseInteractor.error =
                            app.getString(R.string.confirm_mandatory_Password)
                    }
                    "ERROR_CONFIRMAR_SENHA_MENOR_QUE_6" -> {
                        responseInteractor.error = app.getString(R.string.password_Does_not_match)
                    }
                }
            } else {
                responseInteractor.error = null
                responseInteractor.result = app.getString(R.string.create_count_success)
            }
            responseLogin.value = responseInteractor
        }
    }

    fun forgotPassword(data: LoginData) {
        launch {
            val responseInteractor = interactor.forgotPassword(data)
            if (responseInteractor.error != null) {
                when (responseInteractor.error) {
                    "ERROR_EMAIL_VAZIO" -> {
                        responseInteractor.error = app.getString(R.string.mandatory_Email)
                    }
                    "EMAIL_NAO_CONFERE" -> {
                        responseInteractor.error = app.getString(R.string.email_Does_not_match)
                    }
                }
            } else {
                responseInteractor.error = null
            }
            responseLogin.postValue(responseInteractor)
        }
    }

    fun insertLoginLocalData(data: LoginData) {
        launch {
            val loginLocalData = Person(0, data.email, true)

            interactor.insertLoginLocalData(data = loginLocalData)
        }
    }

    fun deleteLoginLocalData() {
        launch {
            responsePersonLogin.value = interactor.searchLoginLocalData()
            val person = Person(responsePersonLogin.value!!.id,
                responsePersonLogin.value!!.email,
                responsePersonLogin.value!!.isLogin)

            interactor.deleteLoginLocalData(person)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }


}