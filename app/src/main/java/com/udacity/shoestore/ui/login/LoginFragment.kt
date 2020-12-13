package com.udacity.shoestore.ui.login

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.loginTitle.setTextColor(Color.WHITE)
        }

        binding.loginRegisterButton.setOnClickListener { navigateToWelcome()}
        binding.loginLoginButton.setOnClickListener { navigateToWelcome()}

        return binding.root
    }

    private fun navigateToWelcome() {
        val action = LoginFragmentDirections.actionLoginToWelcomeFragment()
        this.findNavController().navigate(action)
    }
}