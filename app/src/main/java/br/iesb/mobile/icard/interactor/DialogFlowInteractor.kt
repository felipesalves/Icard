package br.iesb.mobile.icard.interactor

import br.iesb.mobile.icard.domain.dialogChat.Message
import br.iesb.mobile.icard.repository.DialogFlowRepository
import br.iesb.mobile.icard.repository.StoreRepository
import br.iesb.mobile.icard.repository.network.service.ProductsService
import javax.inject.Inject

class DialogFlowInteractor @Inject constructor(
    private val repo: DialogFlowRepository
){
    suspend fun sendMensage(mensage: Message): String{
        return repo.senMensage(mensage)
    }
}