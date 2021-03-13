package br.iesb.mobile.icard.interactor

import android.content.Context
import br.iesb.mobile.icard.domain.LoginLocalData.Person
import br.iesb.mobile.icard.domain.login.LoginData
import br.iesb.mobile.icard.domain.login.LoginResult
import br.iesb.mobile.icard.repository.LoginRepository
import br.iesb.mobile.icard.repository.StoreRepository
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val repo: LoginRepository
){

    suspend fun login(data: LoginData): LoginResult{

        val loginResult = LoginResult()

        if (data.email.isBlank()){
            loginResult.error  = "ERROR_EMAIL_VAZIO"
            return loginResult
        }

        if (data.password.isBlank()){
            loginResult.error  = "ERROR_SENHA_VAZIO"
            return loginResult
        }

        if (data.password.length < 6){
            loginResult.error  = "ERROR_SENHA_MENOR_QUE_6"
            return loginResult
        }

        return repo.login(data)
    }

    suspend fun createCount(data: LoginData): LoginResult{
        val loginResult = LoginResult()

        if (data.email.isBlank()){
            loginResult.error  = "ERROR_EMAIL_VAZIO"
            return loginResult
        }

        if (data.password.isBlank()){
            loginResult.error  = "ERROR_SENHA_VAZIO"
            return loginResult
        }

        if (data.password.length < 6){
            loginResult.error  = "ERROR_SENHA_MENOR_QUE_6"
            return loginResult
        }

        if (data.confirmPassword.isBlank()){
            loginResult.error  = "ERROR_CONFIRMAR_SENHA_VAZIO"
            return loginResult
        }

        if (data.confirmPassword.length < 6){
            loginResult.error  = "ERROR_CONFIRMAR_SENHA_MENOR_QUE_6"
            return loginResult
        }

        if(data.password != data.confirmPassword){
            loginResult.error  = "ERROR_SENHA_NAO_CONFERE"
            return loginResult
        }

        return repo.createCount(data)
    }

    suspend fun forgotPassword(data: LoginData): LoginResult{
        val loginResult = LoginResult()

        if (data.email.isBlank()){
            loginResult.error  = "ERROR_EMAIL_VAZIO"
            return loginResult
        }
        return repo.forgotPassword(data)
    }

    suspend fun insertLoginLocalData(data: Person){
        repo.insertLoginLocalData(data)
    }

    suspend fun searchLoginLocalData(): Person{
       return repo.searchLoginLocalData()
    }

    suspend fun deleteLoginLocalData(person: Person){
        return repo.deleteLoginLocalData(person)
    }

}