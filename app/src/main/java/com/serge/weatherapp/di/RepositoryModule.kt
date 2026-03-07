package com.serge.weatherapp.di

import com.serge.weatherapp.repo.WeatherRepo
import com.serge.weatherapp.repo.WeatherRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module     // provider of dependencies
@InstallIn(SingletonComponent::class)   // lives for whole app
abstract class RepositoryModule {

    @Binds  // which instance to use for an interface
    @Singleton  // makes sure that there's only one instance throughout the app
    abstract fun bindWeatherRepository(
        weatherRepositoryImplementation: WeatherRepositoryImplementation
    ): WeatherRepo
}