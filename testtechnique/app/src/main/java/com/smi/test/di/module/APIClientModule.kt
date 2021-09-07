package com.smi.test.di.module


import dagger.Module

@Module(includes = [NetworkModule::class, ParsingModule::class])
class APIClientModule {
/*
    @Provides
    @ApplicationScope
    fun apiClient(retrofit: Retrofit): APIClient {
        return retrofit.create(APIClient::class.java)
    }


    @Provides
    @ApplicationScope
    fun retrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }*/
}
