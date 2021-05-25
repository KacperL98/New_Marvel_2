package com.example.marvelapp.fragments

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.marvelapp.R
import com.example.marvelapp.adapter.ListComicsAdapter
import com.example.marvelapp.basic.Const
import com.example.marvelapp.databinding.FragmentListComicsBinding
import com.example.marvelapp.viewmodel.ComicsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : Fragment() {
    private val viewModel: ComicsViewModel by viewModels()
    private var _binding: FragmentListComicsBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        ListComicsAdapter {
            findNavController().navigate(
                R.id.action_nav_comics_to_detailsFragment, bundleOf(
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
        _binding = FragmentListComicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listOfHeroesRV.adapter = adapter
        setObservers()
    }
    private fun setObservers () {
        viewModel.observeResults.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
        })
    }
}







