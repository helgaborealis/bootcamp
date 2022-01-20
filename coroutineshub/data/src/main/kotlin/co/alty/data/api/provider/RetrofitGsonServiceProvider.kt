package co.alty.data.api.provider

import co.alty.data.BuildConfig
import com.google.gson.Gson
import co.alty.data.api.provider.interceptor.DummyUnauthorizedInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitGsonServiceProvider @Inject constructor(
  private val gson: Gson,
  private val cache: Cache
) : BaseRetrofitServiceProvider() {

  override fun configureOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder().apply {
      if (BuildConfig.DEBUG) {
        addInterceptor(HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
        })
      }
      connectTimeout(90L, TimeUnit.SECONDS)
      callTimeout(90L, TimeUnit.SECONDS)
      readTimeout(90L, TimeUnit.SECONDS)
      writeTimeout(90L, TimeUnit.SECONDS)
      addNetworkInterceptor(DummyUnauthorizedInterceptor())
//      addInterceptor(AccessTokenInterceptor(authLocalRepository))
//      authenticator(
//        AccessTokenAuthenticator(
//          authLocalRepository,
//          authApiProvider
//        )
//      )
      //todo
    }

    builder.cache(cache)
    return builder.build()
  }

  override fun configureRetrofit(): Retrofit {
    return Retrofit.Builder()
      .client(provideOkHttpClient())
      .addConverterFactory(ScalarsConverterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
//      .baseUrl(BuildConfig.BASE_URL) //todo
      .build()
  }

}
