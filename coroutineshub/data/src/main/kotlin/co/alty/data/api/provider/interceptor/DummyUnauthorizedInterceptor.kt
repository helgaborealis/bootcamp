package co.alty.data.api.provider.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class DummyUnauthorizedInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val original = chain.request()
    val response = chain.proceed(original)

    return if (isUnauthorized(response)) {
      // Just replace the code to trigger authenticator
      response.newBuilder().code(401).build()
    } else {
      response
    }
  }

  private fun isUnauthorized(response: Response): Boolean {
    return response.code == 401
  }
}
