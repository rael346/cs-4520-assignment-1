package com.cs4520.assignment1.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cs4520.assignment1.R
import com.cs4520.assignment1.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java];

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login

        loginButton.isEnabled = true

        loginViewModel.loginResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loginResult.error?.let {
                    showLoginFailed(it)
                }

                if (loginResult.success) {
                    updateUiWithUser()
                    loginViewModel.reset()
                }
            })

        loginButton.setOnClickListener {
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString(),
            )
        }
    }

    private fun updateUiWithUser() {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, R.string.login_success, Toast.LENGTH_LONG).show()
    }
    private fun showLoginFailed(@StringRes errorString: Int) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loginViewModel.reset()
        _binding = null
    }
}