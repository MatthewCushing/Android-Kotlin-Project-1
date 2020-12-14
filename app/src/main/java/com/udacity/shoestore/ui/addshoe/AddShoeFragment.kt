package com.udacity.shoestore.ui.addshoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.data.models.Shoe
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.ui.MainActivityViewModel

class AddShoeFragment : Fragment() {
    lateinit var binding: FragmentAddShoeBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddShoeBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.isShoeAdded.observe(viewLifecycleOwner, Observer {
            if (it) {
                viewModel.shoeAddedComplete()
                findNavController().popBackStack()
            }
        })

        binding.addshoeCancelButton.setOnClickListener { findNavController().popBackStack() }

        return binding.root
    }
}