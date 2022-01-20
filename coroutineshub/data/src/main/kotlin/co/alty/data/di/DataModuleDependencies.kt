package co.alty.data.di

import co.alty.data.repos.auth.AuthRemoteRepo
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DataModuleDependencies {

    fun getAuthRepo(): AuthRemoteRepo

}
