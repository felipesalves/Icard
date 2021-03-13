package br.iesb.mobile.icard.repository

import br.iesb.mobile.icard.domain.store.Store
import br.iesb.mobile.icard.repository.network.service.StoreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val service: StoreService
) {

    suspend fun storeList(): List<Store> {

        return withContext(Dispatchers.IO) {
            val responseService = service.StoreListAsync()

            val responseStore = responseService.map {
                Store(
                    id = it.id,
                    name = it.name,
                    imageUrl = it.imageUrl,
                    loyaltyPoints = it.loyaltyPoints,
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }
            responseStore
        }
    }

    suspend fun storeDetail(id: Int): Store {

        return withContext(Dispatchers.IO) {
            val responseService = service.StoreDetailAsync(id)

            val responseStore =
                Store(
                    id = responseService.id,
                    name = responseService.name,
                    imageUrl = responseService.imageUrl,
                    loyaltyPoints = responseService.loyaltyPoints,
                    latitude = responseService.latitude,
                    longitude = responseService.longitude,
                    primaryColor = responseService.primaryColor,
                    secondColor = responseService.secondColor
                )
            responseStore
        }
    }

}