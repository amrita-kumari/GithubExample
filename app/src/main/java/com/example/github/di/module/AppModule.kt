package com.example.github.di.module

import com.example.github.BuildConfig
import com.example.github.network.RepoApi
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [RepoListViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideRepoApi(retrofit: Retrofit) = retrofit.create(RepoApi::class.java)

    @Provides
    fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides @Named("IO")
    @Singleton
    fun provideIOScheduler(): Scheduler = Schedulers.io()

    @Provides @Named("Main")
    @Singleton
    fun providesMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}