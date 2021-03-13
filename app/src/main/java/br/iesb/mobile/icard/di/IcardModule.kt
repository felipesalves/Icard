package br.iesb.mobile.icard.di

import android.content.Context
import androidx.room.Room
import br.iesb.mobile.icard.repository.local.AppDataBase
import br.iesb.mobile.icard.repository.local.Dao.PersonDao
import br.iesb.mobile.icard.repository.network.service.DialogFlowService
import br.iesb.mobile.icard.repository.network.service.ProductsService
import br.iesb.mobile.icard.repository.network.service.StoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IcardModule {

    @Provides
    fun providerRetrofit(): Retrofit {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://icardapi.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providerDialogFlowService(retrofit: Retrofit): DialogFlowService =
        retrofit.create(DialogFlowService::class.java)

    @Provides
    fun providerProductsService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)

    @Provides
    fun providerStoreService(retrofit: Retrofit): StoreService =
        retrofit.create(StoreService::class.java)

    @Provides
    fun providerPersonDao(appDatabase: AppDataBase): PersonDao =
        appDatabase.getPersonDao()

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "IcardDataBase"
    ).build()

}
