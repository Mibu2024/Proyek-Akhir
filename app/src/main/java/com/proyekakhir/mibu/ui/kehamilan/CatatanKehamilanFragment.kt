package com.proyekakhir.mibu.ui.kehamilan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.databinding.FragmentCatatanKehamilanBinding

class CatatanKehamilanFragment : Fragment() {

    private var _binding: FragmentCatatanKehamilanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val catatanKehamilanViewModel =
            ViewModelProvider(this).get(CatatanKehamilanViewModel::class.java)

        _binding = FragmentCatatanKehamilanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        catatanKehamilanViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}