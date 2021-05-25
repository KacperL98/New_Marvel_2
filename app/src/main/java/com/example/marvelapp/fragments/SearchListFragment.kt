package com.example.marvelapp.fragments

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
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.adapter.ListComicsAdapter
import com.example.marvelapp.basic.Const
import com.example.marvelapp.databinding.FragmentListSearchBinding
import com.example.marvelapp.extension.gone
import com.example.marvelapp.extension.hideKeyboard
import com.example.marvelapp.extension.show
import com.example.marvelapp.viewmodel.SearchViewModel
import com.example.marvelapp.viewmodel.SearchViewModel.ViewState.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchListFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentListSearchBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        ListComicsAdapter {
            findNavController().navigate(
                R.id.action_nav_search_to_detailsFragment, bundleOf(
                    Const.RESULT_COMIC to it
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        closeKeyboardAfterScroll()

        binding.listOfHeroesRV.adapter = adapter
        viewModel.observeResults.observe(viewLifecycleOwner, Observer {

            when (it) {
                Loading -> binding.progressBar.show()
                is Success -> {
                    binding.listOfHeroesRV.show()
                    binding.notFound.gone()
                    binding.progressBar.gone()
                    adapter.submitList(it.results)
                }
                Error -> {
                    binding.progressBar.gone()
                    binding.notFound.gone()
                    Timber.d("api")
                }
                NotFound -> {
                    binding.listOfHeroesRV.gone()
                    binding.progressBar.gone()
                    binding.notFound.show()
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

    private fun closeKeyboardAfterScroll() {
        binding.listOfHeroesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 20) {
                    hideKeyboard()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}
