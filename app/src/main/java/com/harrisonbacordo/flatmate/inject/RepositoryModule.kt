package com.harrisonbacordo.flatmate.inject

import com.harrisonbacordo.flatmate.data.repositories.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepository()
    }
}