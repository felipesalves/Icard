package br.iesb.mobile.icard.repository.network.service

import br.iesb.mobile.icard.domain.dialogChat.Message
import br.iesb.mobile.icard.domain.dialogChat.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DialogFlowService {
    @POST("message/text/send")
    @Headers("Content-Type: application/json")
    suspend fun sendMessageAsync(@Body message: Message): Array<Response>
}