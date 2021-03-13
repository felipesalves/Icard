package br.iesb.mobile.icard.view.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.icard.domain.product.Products
import br.iesb.mobile.icard.interactor.ProductsInteractor
import br.iesb.mobile.icard.interactor.StoreInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    val app: Application,
    private val interactor: ProductsInteractor
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    val productsList = MutableLiveData<List<Products>>()

    fun productsList(id: Int){

        launch {
            try {
                val productsListInteractor = interactor.productsList(id)
                productsList.value = productsListInteractor
            }catch (t: Throwable){
                Log.d("Store", "Error to List Store: ${t.localizedMessage}" )
            }
        }
    }

}