package co.alty.data.di

import android.content.Context
import com.google.gson.Gson
import co.alty.data.api.AuthApi
import co.alty.data.api.provider.RetrofitGsonServiceProvider
import co.alty.data.repos.auth.AuthRemoteRepo
import co.alty.data.repos.auth.AuthRemoteRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

  @Provides
  @Singleton
  internal fun providesOkHttpCache(@ApplicationContext context: Context): Cache {
    val size: Long = 15 * 1024 * 1024 // 15 MB
    return Cache(context.cacheDir, size)
  }

  @Provides
  @Singleton
  internal fun provideDefaultMoshi(): Gson {
    return Gson()
  }

  @Provides
  @Singleton
  internal fun provideAuthApi(provider: RetrofitGsonServiceProvider): AuthApi {
    return provider.getService(AuthApi::class.java)
  }

  @Provides
  @Singleton
  fun provideAuthRepo(authApi: AuthApi): AuthRemoteRepo {
    return AuthRemoteRepoImpl(authApi)
  }
}