package com.payback.data.di

import com.payback.data.usecase.RepositoryUsecase
import com.payback.domain.model.Hits
import com.payback.domain.repositories.RemoteRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: RepositoryUsecase): RemoteRepo


}