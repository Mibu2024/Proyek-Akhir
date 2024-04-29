package com.proyekakhir.mibu.user.ui.artikel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.databinding.FragmentDetailArtikelBinding
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel

class DetailArtikelFragment : Fragment() {
    private var _binding: FragmentDetailArtikelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailArtikelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as ArtikelModel

        binding.titleArtikel.text = itemData.judul
        binding.isiArtikel.text = itemData.isiArtikel

        if (!itemData.imageUrl.isNullOrEmpty()) {
            Glide.with(this)
                .load(itemData.imageUrl)
                .into(binding.ivPosterArtikel)
        } else {
            binding.cardView3.visibility = View.GONE
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.textView17.setOnClickListener {
            findNavController().popBackStack()
        }

        return root
    }

}