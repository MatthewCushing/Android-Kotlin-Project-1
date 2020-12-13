package com.udacity.shoestore.ui.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {
    lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructionsBinding.inflate(layoutInflater, container, false)

        binding.instructionsButton.setOnClickListener {
            val action = InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}