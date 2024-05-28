package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.nifas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.LaporanViewModel
import com.proyekakhir.mibu.databinding.FragmentTabListNifasBinding
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository

class TabListNifasFragment : Fragment() {
    private var _binding: FragmentTabListNifasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LaporanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabListNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(LaporanViewModel::class.java)

        val list = arrayListOf<AddNifasData>()
        val adapter = AdapterListNifas(list)
        val rvNifas = binding.rvTabNifas
        rvNifas.layoutManager = LinearLayoutManager(requireContext())
        rvNifas.setHasFixedSize(true)
        rvNifas.adapter = adapter
        binding.btnExport.setOnClickListener {
            ListNifasExporter.createXlsx(list, requireContext())
        }

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            viewModel.allCatatanNifasList()
            viewModel.catatanNifasList.observe(viewLifecycleOwner, Observer { list ->
                adapter.setData(list)
                adapter.notifyDataSetChanged()
                if (list.isEmpty()) {
                    binding.tvNoDataNifas.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataNifas.visibility = View.GONE
                }
            })
        }

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        adapter.listener = object : AdapterListNifas.OnItemClickListenerHome {
            override fun onItemClick(item: AddNifasData) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_listCatatanFragment_to_detailListNifasFragment,
                    bundle
                )
            }
        }

        return root
    }
}