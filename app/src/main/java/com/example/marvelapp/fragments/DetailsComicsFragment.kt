package com.example.marvelapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapp.model.Result
import com.example.marvelapp.R
import com.example.marvelapp.databinding.DetailComicsFragmentBinding
import com.example.marvelapp.databinding.FragmentListComicsBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_comics_fragment.*
import timber.log.Timber

@AndroidEntryPoint
class DetailsComicsFragment : Fragment() {

    private lateinit var binding: DetailComicsFragmentBinding
    private var result: Result? = null
    private var creators: String = ""
    private var description: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailComicsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        result = arguments?.getParcelable("person_data") as Result?
        binding = DetailComicsFragmentBinding.bind(view)

        comicBook.text = result?.title

        val url = "${result?.thumbnail?.path}.${result?.thumbnail?.extension}".replace(
            "http", "https"
        )
        Picasso.get().load(url).into(background_imageView_comic)
        Timber.d("picture ${result?.thumbnail?.path}.${result?.thumbnail?.extension}")

        result?.creators?.items?.forEach {
            creators += "${it.name}\n"
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

}