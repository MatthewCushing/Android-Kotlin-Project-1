package com.udacity.shoestore.ui.shoelist

import android.os.Bundle
import android.view.*
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.WidgetItemBinding
import com.udacity.shoestore.ui.MainActivityViewModel
import kotlinx.android.synthetic.main.widget_item.view.*


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
                val itemWidgetBinding = WidgetItemBinding.inflate(layoutInflater, binding.shoelistContainer, false)
                val itemWidget = itemWidgetBinding.itemviewContainer

                itemWidget.itemwidget_text.text = "${it.name}\n${it.company}\nSize ${it.size}\n${it.description}"
                binding.shoelistContainer.addView(itemWidget)
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