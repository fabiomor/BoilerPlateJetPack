package it.fabio.boilerplatejetpack.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.IOException;

import javax.security.auth.login.LoginException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import it.fabio.boilerplatejetpack.R;
import it.fabio.boilerplatejetpack.application.App;
import it.fabio.boilerplatejetpack.application.ApplicationComponent;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;
    protected ProgressDialog mProgressDialog;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        unbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        registerListeners();
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        hideDialog();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterListeners();
    }

    protected void showDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void registerListeners(){ }

    @CallSuper
    protected void unregisterListeners(){
        if(unbinder != null) unbinder.unbind();
    }

    protected void showErrorMessage(Throwable error, String TAG){
        if (error instanceof HttpException) {
            HttpException httpException = (HttpException) error;
            Response response = httpException.response();
            switch (response.code()) {
                case 400:
                    //Snackbar.make(getView(), getString(R.string.error_400), Snackbar.LENGTH_LONG).show();
                    FancyToast.makeText(getApplicationContext(), getString(R.string.error_400), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    Log.e(TAG, getString(R.string.error_400));
                    break;
                case 401:
                    //Snackbar.make(getView(), getString(R.string.error_401), Snackbar.LENGTH_LONG).show();
                    FancyToast.makeText(getApplicationContext(), getString(R.string.error_401), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    Log.e(TAG, getString(R.string.error_401));
                    break;
                case 404:
                    //Snackbar.make(getView(), getString(R.string.error_404), Snackbar.LENGTH_LONG).show();
                    FancyToast.makeText(getApplicationContext(), getString(R.string.error_404), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    Log.e(TAG, getString(R.string.error_404));
                    break;
                default:
                    //Snackbar.make(getView(), getString(R.string.error_generic), Snackbar.LENGTH_LONG).show();
                    FancyToast.makeText(getApplicationContext(), getString(R.string.error_generic), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                    Log.e(TAG, getString(R.string.error_generic));
            }
        }
        else if (error instanceof IOException) {
            //Snackbar.make(getView(), getString(R.string.connection_error), Snackbar.LENGTH_LONG).show();
            FancyToast.makeText(getApplicationContext(), getString(R.string.connection_error), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
        }
        else if(error instanceof LoginException){
            //Snackbar.make(getView(), getString(R.string.first_access_uncompleted), Snackbar.LENGTH_LONG).show();
            FancyToast.makeText(getApplicationContext(), getString(R.string.login_error), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
        }
        else {
            //Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
            FancyToast.makeText(getApplicationContext(), error.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
        }
        Log.e(TAG, error.getMessage());
    }

    protected abstract int getContentView();

    protected abstract View getView();
}
