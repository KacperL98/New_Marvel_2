package com.example.marvelapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.marvelapp.model.Result
import com.example.marvelapp.R
import com.example.marvelapp.databinding.DetailComicsFragmentBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_comics_fragment.*
import timber.log.Timber

@AndroidEntryPoint
class DetailsComicsFragment : Fragment(R.layout.detail_comics_fragment) {

    private var _binding: DetailComicsFragmentBinding? = null
    private val binding get() = _binding
    private var result: Result? = null
    private var creators: String = ""
    private var description: String = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        result = arguments?.getParcelable("person_data") as Result?
        _binding = DetailComicsFragmentBinding.bind(view)

        comicBook.text = result?.title

        val url = "${result?.thumbnail?.path}.${result?.thumbnail?.extension}".replace(
            "http", "https")
        Picasso.get().load(url).into(background_imageView_comic)
        Timber.d("picture ${result?.thumbnail?.path}.${result?.thumbnail?.extension}")

        result?.creators?.items?.forEach {
            creators += "${it.name}\n"
        }
        binding?.comicsBookAuthors?.text = creators

        result?.textObjects?.forEach {
            description += "${it.text}\n"
        }
        binding?.descriptionTxt?.text = description

        binding?.btnLink?.setOnClickListener {
            val website = result?.urls?.firstOrNull()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(website?.url)
            startActivity(intent)
        }
    }

}