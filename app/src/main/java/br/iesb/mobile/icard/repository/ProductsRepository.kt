package br.iesb.mobile.icard.repository

import br.iesb.mobile.icard.domain.product.Products
import br.iesb.mobile.icard.repository.network.service.ProductsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val service: ProductsService
){

    suspend fun productsList(id: Int): List<Products>{

        return withContext(Dispatchers.IO){
            val responseService = service.ProductsListAsync(id)

            val responseStore = responseService.map {
                Products(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    price = it.price
                )
            }
            responseStore
        }
    }
}