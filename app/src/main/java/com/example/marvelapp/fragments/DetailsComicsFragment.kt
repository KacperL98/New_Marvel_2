package com.example.marvelapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.R
import com.example.marvelapp.adapter.VerticalComicsModel
import com.example.marvelapp.basic.Const
import com.example.marvelapp.basic.Const.Companion.RESULT_COMIC
import com.example.marvelapp.databinding.DetailComicsFragmentBinding
import com.example.marvelapp.model.Result
import com.example.marvelapp.viewmodel.CharactersViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsComicsFragment : Fragment() {

    private var _binding: DetailComicsFragmentBinding? = null
    private val binding get() = _binding!!
    private val mAdapter = VerticalComicsModel()
    private lateinit var mViewModel: CharactersViewModel
    private val quizId: Int by lazy { arguments?.getLong(RESULT_COMIC) as Int }

    private var result: Result? = null
    private var creators: String = ""
    private var description: String = ""

    private var mId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailComicsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        extendableView()
        observe()
        mViewModel.getCharacterComics(quizId)
        binding.recyclerComic.adapter = mAdapter


    }


    private fun initView() {
        result = arguments?.getParcelable(Const.RESULT_COMIC) as Result?

        binding.comicBook.text = result?.title

        val url = "${result?.thumbnail?.path}.${result?.thumbnail?.extension}".replace(
            "http", "https"
        )
        Picasso.get().load(url).into(binding.backgroundImageViewComic)

        result?.creators?.items?.forEach {
            creators += "${it.name}, "
        }
        binding.comicsBookAuthors.text = creators

        result?.textObjects?.forEach {
            description += "${it.text}\n"
        }
        binding.descriptionTxt.text = description

        binding.btnLink.setOnClickListener {
            val website = result?.urls?.firstOrNull()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(website?.url)
            startActivity(intent)
        }
    }

    private fun extendableView() {
        BottomSheetBehavior.from(binding.sheetShape).apply {
            peekHeight = 600
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }    private fun observe() {
        mViewModel.observeCharacterComicsResult.observe(viewLifecycleOwner, { it->
                mAdapter.submitList(it)
        })
    }
}