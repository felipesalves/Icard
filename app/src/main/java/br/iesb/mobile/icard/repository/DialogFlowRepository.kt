package br.iesb.mobile.icard.repository

import br.iesb.mobile.icard.domain.dialogChat.Message
import br.iesb.mobile.icard.repository.network.service.DialogFlowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DialogFlowRepository @Inject constructor(
    private val service: DialogFlowService
){

    suspend fun senMensage(mensage: Message): String{
        return withContext(Dispatchers.IO){
            val responseService = service.sendMessageAsync(mensage)
            responseService[0].queryResult.fulfillmentText
        }
    }

}