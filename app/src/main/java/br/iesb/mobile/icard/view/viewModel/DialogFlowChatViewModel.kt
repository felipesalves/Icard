package br.iesb.mobile.icard.view.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.icard.domain.dialogChat.Message
import br.iesb.mobile.icard.domain.dialogChat.MessageChat
import br.iesb.mobile.icard.interactor.DialogFlowInteractor
import br.iesb.mobile.icard.interactor.StoreInteractor
import br.iesb.mobile.icard.view.ReceiveMessageItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class DialogFlowChatViewModel@Inject constructor(
    val app: Application,
    private val interactor: DialogFlowInteractor
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val responseDialog = MutableLiveData<String>()

    fun sendMensage(mensage: String) {
        launch {
            try {
                val message = Message(
                    mensage,
                    "",
                    "123"
                )

                val responseInteractor = interactor.sendMensage(message)
                responseDialog.value = responseInteractor
            } catch (t: Throwable) {
                Log.d("Store", "Error to DialogFlow: ${t.localizedMessage}")
            }
        }
    }
}