package com.udacity.shoestore.ui.shoelist

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.ui.MainActivityViewModel

class ShoeListFragment : Fragment() {
    lateinit var binding: FragmentShoeListBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        binding.mainActivityViewModel = viewModel
        setHasOptionsMenu(true)

        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            viewModel.shoes.value?.asReversed()?.map {
                val cardView = CardView(requireContext())
                val cardViewParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                cardViewParams.bottomMargin = 32
                cardView.layoutParams = cardViewParams
                cardView.radius = 16f

                val linearLayoutView = LinearLayout(requireContext())
                val linearLayoutViewParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                linearLayoutView.layoutParams = linearLayoutViewParams
                linearLayoutView.setPadding(32, 32, 32, 32)

                val textView = TextView(requireContext())
                val textViewParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                textView.layoutParams = textViewParams
                textView.textSize = 18f
                textView.setLineSpacing(18f, 1.3f)
                textView.setTextColor(Color.BLACK)
                textView.text = "${it.name}\n${it.company}\nSize ${it.size}\n${it.description}"

                linearLayoutView.addView(textView)
                cardView.addView(linearLayoutView)
                binding.shoelistContainer.addView(cardView)
            }
        })

        binding.shoelistFab.setOnClickListener {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}