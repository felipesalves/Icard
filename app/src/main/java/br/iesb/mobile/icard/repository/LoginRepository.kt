package br.iesb.mobile.icard.repository

import br.iesb.mobile.icard.domain.LoginLocalData.Person
import br.iesb.mobile.icard.domain.login.LoginData
import br.iesb.mobile.icard.domain.login.LoginResult
import br.iesb.mobile.icard.repository.local.Dao.PersonDao
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository @Inject constructor(
    private val dao: PersonDao
){

    suspend fun login(data: LoginData): LoginResult = suspendCoroutine { response ->

        val auth = FirebaseAuth.getInstance()
        val operation = auth.signInWithEmailAndPassword(data.email, data.password)

        operation.addOnCompleteListener{ responseLogin ->
            val loginResult = LoginResult()
            if(responseLogin.isSuccessful){
                loginResult.result = "LOGIN_FIREBASE_SUCESSO"
            } else {
                loginResult.error = "LOGIN_FIREBASE_ERROR"
            }
            response.resume(loginResult)
        }
    }

    suspend fun createCount(data: LoginData): LoginResult = suspendCoroutine { response ->
        val auth = FirebaseAuth.getInstance()
        val operation = auth.createUserWithEmailAndPassword(data.email, data.password)

        operation.addOnCompleteListener{ responseCreateCount ->
            val loginResult = LoginResult()
            if(responseCreateCount.isSuccessful){
                loginResult.result = "LOGIN_CONTA_FIREBASE_SUCESSO"
            } else {
                loginResult.error = "LOGIN_FIREBASE_ERROR"
            }
            response.resume(loginResult)
        }
    }

    suspend fun forgotPassword(data: LoginData):  LoginResult = suspendCoroutine { response ->
        val auth = FirebaseAuth.getInstance()
        val operation = auth.sendPasswordResetEmail(data.email)

        operation.addOnCompleteListener{ responseForgotPassword ->
            val loginResult = LoginResult()
            if(responseForgotPassword.isSuccessful){
                loginResult.result = "LOGIN_CONTA_FIREBASE_SUCESSO"
            } else {
                loginResult.error = "EMAIL_NAO_CONFERE"
            }
            response.resume(loginResult)
        }
    }

    suspend fun insertLoginLocalData(data: Person){

        GlobalScope.launch {
            dao.insertPerson(data)
        }
    }

    suspend fun searchLoginLocalData():Person {

        return withContext(Dispatchers.IO){
            val responseService = dao.getPerson()

            val responseStore =
                Person(
                    id = responseService.id,
                    email = responseService.email,
                    isLogin = responseService.isLogin
                )
            responseStore
        }
    }

    suspend fun deleteLoginLocalData(person: Person) {
        dao.deletePerson(person)
    }


}