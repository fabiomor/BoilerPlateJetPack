package it.fabio.boilerplatejetpack.application;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import it.fabio.boilerplatejetpack.control.ApiInterceptor;
import it.fabio.boilerplatejetpack.factory.ViewModelFactory;
import it.fabio.boilerplatejetpack.modules.main.MainActivity;
import it.fabio.boilerplatejetpack.repository.local.LocalDataRepository;
import it.fabio.boilerplatejetpack.repository.remote.ApiService;
import it.fabio.boilerplatejetpack.repository.remote.RemoteDataRepository;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    ApiService exposeApiServices();
    ApiInterceptor exposeApiInterceptor();
    Context exposeContext();
    LocalDataRepository exposeLocalDAO();
    RemoteDataRepository exposeRemoteDAO();
    ViewModelFactory exposeViewModelFactory();
}
