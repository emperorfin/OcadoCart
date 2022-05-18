package emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.service

import emperorfin.android.ocadocart.BuildConfig
import emperorfin.android.ocadocart.data.datasources.remote.frameworks.retrofit.webservices.ocado.endpoints.products.ResponseWrapper
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Tuesday 17th May, 2022.
 */


interface OcadoService {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BuildConfig.OCADO_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

        val INSTANCE: OcadoService by lazy { retrofit.create(OcadoService::class.java) }
    }

    @GET("products")
    suspend fun getProductsOverviews(): Response<ResponseWrapper>

}