package com.example.gamecompose.di

import android.content.Context
import com.example.gamecompose.application.GameApplication
import com.example.gamecompose.data.remote.api.ApiBuilder
import com.example.gamecompose.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Singleton
   @Provides
   fun provideGameApp(@ApplicationContext app: Context): GameApplication {
      return app as GameApplication
   }

   @Singleton
   @Provides
   fun provideApi(apiBuilder: ApiBuilder): ApiService {
      return apiBuilder.builder(ApiService::class.java)
   }
}