package it.fabio.boilerplatejetpack.modules.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import it.fabio.boilerplatejetpack.R;
import it.fabio.boilerplatejetpack.base.BaseFragment;
import it.fabio.boilerplatejetpack.factory.ViewModelFactory;
import it.fabio.boilerplatejetpack.repository.data.User;
import timber.log.Timber;

public class MainFragment extends BaseFragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private Observer<List<User>> userObserver;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void resolveDaggerDependencies() {
        DaggerMainFragmentComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.main_fragment;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        userObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                for(User user : users){
                    Timber.i(user.getLastName());
                }
            }
        };

        mViewModel.getUsers().observe(this, userObserver);
    }

}
