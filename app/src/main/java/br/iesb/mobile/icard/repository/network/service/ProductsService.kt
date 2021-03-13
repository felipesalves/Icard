package br.iesb.mobile.icard.repository.network.service

import br.iesb.mobile.icard.domain.product.Products
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ProductsService {

    @GET("store/{id}/products")
    @Headers("Content-Type: application/json")
    suspend fun ProductsListAsync(@Path("id") int: Int): List<Products>
}