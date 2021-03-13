package br.iesb.mobile.icard.view.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.icard.domain.store.Store
import br.iesb.mobile.icard.interactor.StoreInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class StoreListViewModel @Inject constructor(
    val app: Application,
    private val interactor: StoreInteractor
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val storeList = MutableLiveData<List<Store>>()
    val storeDetail = MutableLiveData<Store>()


    fun storeList(){

        launch {
            try {
                val storeListInteractor = interactor.storeList()
                storeList.value = storeListInteractor
            }catch (t: Throwable){
                Log.d("Store", "Error to List Store: ${t.localizedMessage}" )
            }
        }
    }

    fun storeDetail(id: Int){

        launch {
            try {
                val storeDetailinterator = interactor.storeDetail(id)
                storeDetail.value = storeDetailinterator
            }catch (t: Throwable){
                Log.d("Store", "Error to Detail Store: ${t.localizedMessage}" )
            }
        }

    }


}