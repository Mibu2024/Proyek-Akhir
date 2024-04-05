package com.proyekakhir.mibu.ui.nifas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.databinding.FragmentCatatanNifasBinding

class CatatanNifasFragment : Fragment() {

    private var _binding: FragmentCatatanNifasBinding? = null

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

        _binding = FragmentCatatanNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        catatanNifasViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}