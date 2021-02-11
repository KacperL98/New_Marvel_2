package com.example.marvelapp.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.marvelapp.Model.Result
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentListSearchBinding
import com.example.marvelapp.adapter.ListSearchAdapter
import com.example.marvelapp.viewmodel.SearchViewModel
import com.example.marvelapp.viewmodel.SearchViewModel.ViewState.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentListSearchBinding

    private val adapter = ListSearchAdapter(object : ListSearchAdapter.SearchListener {
        override fun onClickSearch(result: Result?) {
            findNavController().navigate(
                R.id.action_nav_search_to_detailsFragment, bundleOf("person_data" to result)
            )
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listOfHeroesRV.adapter = adapter
        viewModel.observeResults.observe(viewLifecycleOwner, Observer {

            when (it) {

                Loading -> binding.progressbar.visibility = View.INVISIBLE
                is Success -> {
                    binding.listOfHeroesRV.visibility = View.VISIBLE

                    binding.notFound.visibility = View.GONE
                    binding.progressbar.visibility = View.GONE
                    adapter.submitList(it.results)
                }
                Error -> {
                    binding.progressbar.visibility = View.GONE
                    binding.notFound.visibility = View.GONE

                    Timber.d("api")
                }
                NotFound -> {
                    binding.listOfHeroesRV.visibility = View.GONE
                    binding.progressbar.visibility = View.GONE
                    binding.notFound.visibility = View.VISIBLE
                    Timber.d("not found")
                }
            }
        })

        binding.etQuery.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.getCharacterByTitle(it.toString())
            }
        }
    }


}


