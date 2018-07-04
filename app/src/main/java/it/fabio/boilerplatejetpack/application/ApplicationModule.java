package it.fabio.boilerplatejetpack.application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import it.fabio.boilerplatejetpack.control.ApiInterceptor;
import it.fabio.boilerplatejetpack.factory.ViewModelFactory;
import it.fabio.boilerplatejetpack.qualifiers.ApplicationContext;
import it.fabio.boilerplatejetpack.repository.local.LocalDataRepository;
import it.fabio.boilerplatejetpack.repository.local.LocalRepository;
import it.fabio.boilerplatejetpack.repository.local.RoomDb;
import it.fabio.boilerplatejetpack.repository.local.RoomDbManager;
import it.fabio.boilerplatejetpack.repository.local.SharedPreferencesManager;
import it.fabio.boilerplatejetpack.repository.remote.ApiService;
import it.fabio.boilerplatejetpack.repository.remote.RemoteDataRepository;
import it.fabio.boilerplatejetpack.repository.remote.RemoteRepository;
import it.fabio.boilerplatejetpack.utils.Constants;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static it.fabio.boilerplatejetpack.utils.Constants.CONFIG.RETROFIT_API_CONNECTION_TIMEOUT_SECONDS;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }


    @Singleton
    @Provides
    Gson providesGson(){
        return new GsonBuilder()
                .create();
    }

    @Singleton
    @Provides
    GsonConverterFactory providesGsonConverterFactory(Gson gson){
        return  GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient(Cache cache, ApiInterceptor apiInterceptor){
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(apiInterceptor)
                .connectTimeout(RETROFIT_API_CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(RETROFIT_API_CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(RETROFIT_API_CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit providesRetrofitIcos(OkHttpClient client, GsonConverterFactory converterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(adapterFactory)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    ApiInterceptor providesApiInterceptor(){
        return new ApiInterceptor();
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(@ApplicationContext Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    ApiService providesApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(@ApplicationContext Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    LocalDataRepository providesLocalDAO(@ApplicationContext Context context, SharedPreferences sharedPreferences){
        return new LocalRepository(new RoomDbManager(RoomDb.getDatabase(context)), new SharedPreferencesManager(sharedPreferences, context));
    }

    @Provides
    @Singleton
    RemoteDataRepository providesRemoteDAO(ApiService apiService, ApiInterceptor apiInterceptor){
        return new RemoteRepository();
    }

}
