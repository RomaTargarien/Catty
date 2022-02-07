package com.example.catty.di

import android.content.Context
import com.example.catty.prefs.GameRepository
import com.example.catty.prefs.GameRepositoryImpl
import com.example.catty.ui.util.food.FoodFactory
import com.example.catty.ui.util.food.FoodFactoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainGameModul {

    @Provides
    @Singleton
    fun provideFoodFactory() = FoodFactoryImpl() as FoodFactory

    @Provides
    @Singleton
    fun provideGameRepositore(@ApplicationContext context: Context) =
        GameRepositoryImpl(context) as GameRepository

}