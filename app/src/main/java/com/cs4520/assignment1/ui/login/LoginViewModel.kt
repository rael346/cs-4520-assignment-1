package com.cs4520.assignment1.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assignment1.R

class LoginViewModel : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        if (username == "admin" && password == "admin") {
            _loginResult.value = LoginResult(success = true)
        } else {
            _loginResult.value = LoginResult(R.string.login_failed)
        }
    }

    fun reset() {
        _loginResult.value = LoginResult()
    }
}