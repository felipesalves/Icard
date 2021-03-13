package br.iesb.mobile.icard.repository.network.service

import br.iesb.mobile.icard.domain.store.Store
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface StoreService {

    @GET("store")
    @Headers("Content-Type: application/json")
    suspend fun StoreListAsync(): List<Store>


    @GET("store/{id}")
    @Headers("Content-Type: application/json")
    suspend fun StoreDetailAsync(@Path("id") int: Int): Store
}