package com.shellcore.android.firebasechat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shellcore.android.firebasechat.login.LoginPresenter;
import com.shellcore.android.firebasechat.login.ui.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    // Servicios
    private LoginPresenter presenter;

    // Componentes
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_pass)
    TextInputLayout tilPass;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.btn_signin)
    Button btnSignin;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        presenter = new LoginPresenterImpl();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_signin)
    @Override
    public void handleSignIn() {
        presenter.validateLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
    }

    @OnClick(R.id.btn_signup)
    @Override
    public void handleSignUp() {
        presenter.registerNewUser(edtEmail.getText().toString(), edtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        edtPassword.setText("");
        String errorMessage = String.format(getString(R.string.login_error_message_signin), error);
        tilPass.setError(errorMessage);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void newUserError(String error) {
        edtPassword.setText("");
        String errorMessage = String.format(getString(R.string.login_error_message_signup), error);
        tilPass.setError(errorMessage);
    }

    private void setInputs(boolean enabled) {
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
        edtEmail.setEnabled(enabled);
        edtPassword.setEnabled(enabled);
    }
}
