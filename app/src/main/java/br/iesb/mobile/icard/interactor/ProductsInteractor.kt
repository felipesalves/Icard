package br.iesb.mobile.icard.interactor

import br.iesb.mobile.icard.domain.product.Products
import br.iesb.mobile.icard.repository.ProductsRepository
import br.iesb.mobile.icard.repository.StoreRepository
import javax.inject.Inject

class ProductsInteractor @Inject constructor(
    private val repo: ProductsRepository
){
    suspend fun productsList(id: Int): List<Products>{
        return repo.productsList(id)
    }
}