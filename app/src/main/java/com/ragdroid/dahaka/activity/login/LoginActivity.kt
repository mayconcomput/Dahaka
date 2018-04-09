package com.ragdroid.dahaka.activity.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.ragdroid.dahaka.R
import com.ragdroid.dahaka.activity.home.HomeActivity
import com.ragdroid.dahaka.app.AppComponent
import com.ragdroid.dahaka.databinding.ActivityLoginBinding
import com.ragdroid.dahaka.mvp.BaseActivity

class LoginActivity : BaseActivity<LoginContract.Presenter, LoginContract.View>(), LoginContract.View {

    override fun getView(): LoginContract.View = this

    private lateinit var loginComponent: LoginComponent
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        super.onCreate(savedInstanceState)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.title_activity_login)
        binding.presenter = presenter
    }

    override fun initDagger(appComponent: AppComponent) {
        loginComponent = appComponent
                .loginBuilder()
                .loginActivity(this)
                .build()
        loginComponent.inject(this)
    }

    override fun showHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun setModel(loginModel: LoginModel) {
        binding.model = loginModel
    }
}