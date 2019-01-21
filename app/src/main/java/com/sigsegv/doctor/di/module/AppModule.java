package com.sigsegv.doctor.di.module;

import android.content.Context;

import com.sigsegv.doctor.rest.RestService;
import com.sigsegv.doctor.util.PrefUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    /**
    * Provide retrofit to view model classes
    */
    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Provide retrofit service to retrofit provider
     */
    @Provides
    @Singleton
    public RestService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RestService.class);
    }

    /**
     * Provide shared preference utils
     */
    @Provides
    @Singleton
    public PrefUtils providePrefUtils(Context context) {
        return new PrefUtils(context);
    }
}