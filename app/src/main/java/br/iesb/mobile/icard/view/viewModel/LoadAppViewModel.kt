package br.iesb.mobile.icard.view.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.icard.domain.LoginLocalData.Person
import br.iesb.mobile.icard.interactor.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoadAppViewModel @Inject constructor(
    val app: Application,
    private val interactor: LoginInteractor
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main
    var responsePersonLogin = MutableLiveData<Person>()


    fun searchLoginLocalData() {
        launch {

            try {
                responsePersonLogin.value = interactor.searchLoginLocalData()
            } catch (t: Throwable) {
                responsePersonLogin.value = Person(1, "Error", false)
                Log.d("Person", "Error to Select Local data base: ${t.localizedMessage}")
            }
        }
    }
}