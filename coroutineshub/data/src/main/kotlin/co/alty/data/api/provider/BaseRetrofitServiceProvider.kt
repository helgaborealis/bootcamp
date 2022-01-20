package co.alty.data.api.provider

import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class BaseRetrofitServiceProvider {
  private var retrofit: Retrofit? = null
  private var client: OkHttpClient? = null

  abstract fun configureOkHttpClient(): OkHttpClient

  abstract fun configureRetrofit(): Retrofit

  fun <T> getService(clazz: Class<T>): T {
    val retrofit = this.retrofit ?: configureRetrofit().also {
      this.retrofit = it
    }
    return retrofit.create(clazz)
  }

  fun provideOkHttpClient(): OkHttpClient {
    return this.client ?: configureOkHttpClient().also {
      this.client = it
    }
  }
}