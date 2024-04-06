package com.proyekakhir.mibu.ui.kehamilan.nifas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.databinding.FragmentTabNifasBinding

class TabNifasFragment : Fragment() {

    private var _binding: FragmentTabNifasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val catatanNifasViewModel =
            ViewModelProvider(this).get(CatatanNifasViewModel::class.java)

        _binding = FragmentTabNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}