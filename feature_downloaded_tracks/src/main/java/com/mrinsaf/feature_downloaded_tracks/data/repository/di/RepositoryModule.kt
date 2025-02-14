package com.mrinsaf.feature_downloaded_tracks.data.repository.di

import com.mrinsaf.feature_downloaded_tracks.data.repository.DeezerLocalRepository
import com.mrinsaf.feature_downloaded_tracks.data.repository.DeezerLocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDeezerLocalRepository(
        deezerLocalRepositoryImpl: DeezerLocalRepositoryImpl
    ): DeezerLocalRepository
}