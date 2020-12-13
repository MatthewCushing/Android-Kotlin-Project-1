package com.udacity.shoestore.ui.addshoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        binding.addshoeSaveButton.setOnClickListener { _: View ->
            val name = when(binding.addshoeInputName.text.toString() == "") {
                true -> "N/A"
                false -> binding.addshoeInputName.text.toString()
            }

            val company = when(binding.addshoeInputCompany.text.toString() == "") {
                true -> "N/A"
                false -> binding.addshoeInputCompany.text.toString()
            }

            val size = when(binding.addshoeInputSize.text.toString() == "") {
                true -> 0.0
                false -> binding.addshoeInputSize.text.toString().toDouble()
            }

            val description = when(binding.addshoeInputDescription.text.toString() == "") {
                true -> "N/A"
                false -> binding.addshoeInputDescription.text.toString()
            }

            val newShoe = Shoe(name, size, company, description)

            viewModel.addShoe(newShoe)

            findNavController().popBackStack()
        }

        binding.addshoeCancelButton.setOnClickListener { findNavController().popBackStack() }

        return binding.root
    }
}