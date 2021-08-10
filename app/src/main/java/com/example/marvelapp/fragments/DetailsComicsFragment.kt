package com.example.marvelapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapp.basic.Const
import com.example.marvelapp.databinding.DetailComicsFragmentBinding
import com.example.marvelapp.model.Result
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsComicsFragment : Fragment() {

    private var _binding: DetailComicsFragmentBinding? = null
    private val binding get() = _binding!!

    private var result: Result? = null
    private var creators: String = ""
    private var description: String = ""

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
    }
}