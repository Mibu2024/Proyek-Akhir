package com.proyekakhir.mibu.ui.anak

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proyekakhir.mibu.R

class CatatanAnakFragment : Fragment() {

    companion object {
        fun newInstance() = CatatanAnakFragment()
    }

    private lateinit var viewModel: CatatanAnakViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catatan_anak, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CatatanAnakViewModel::class.java)
        // TODO: Use the ViewModel
    }

}