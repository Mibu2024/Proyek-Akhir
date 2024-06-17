package com.proyekakhir.mibu.user.ui.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.proyekakhir.mibu.databinding.FragmentDetailArtikelBinding
import com.proyekakhir.mibu.user.api.response.DataArtikelItem

class DetailArtikelFragment : Fragment() {
    private var _binding: FragmentDetailArtikelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailArtikelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as DataArtikelItem

        binding.titleArtikel.text = itemData.judul
        binding.isiArtikel.text = itemData.isi

        if (!itemData.foto.isNullOrEmpty()) {
            Glide.with(this)
                .load(itemData.foto)
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