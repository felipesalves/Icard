package br.iesb.mobile.icard.interactor

import br.iesb.mobile.icard.domain.store.Store
import br.iesb.mobile.icard.repository.StoreRepository
import javax.inject.Inject


class StoreInteractor @Inject constructor(
    private val repo: StoreRepository
) {
    suspend fun storeList(): List<Store> {
        return repo.storeList()
    }

    suspend fun storeDetail(id: Int): Store {
        return repo.storeDetail(id)
    }

}