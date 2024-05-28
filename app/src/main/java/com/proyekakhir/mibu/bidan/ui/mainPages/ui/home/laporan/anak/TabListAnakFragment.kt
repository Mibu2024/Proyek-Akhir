package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.anak

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
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.LaporanViewModel
import com.proyekakhir.mibu.databinding.FragmentTabListAnakBinding
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository


class TabListAnakFragment : Fragment() {
    private var _binding: FragmentTabListAnakBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LaporanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabListAnakBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(LaporanViewModel::class.java)

        val list = arrayListOf<AddDataAnak>()
        val adapter = AdapterListAnak(list)
        val rvAnak = binding.rvDataAnak
        rvAnak.layoutManager = LinearLayoutManager(requireContext())
        rvAnak.setHasFixedSize(true)
        rvAnak.adapter = adapter
        binding.btnExport.setOnClickListener {
            ListAnakExporter.createXlsx(list, requireContext())
        }

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            viewModel.allCatatanAnakList()
            viewModel.catatanAnakList.observe(viewLifecycleOwner, Observer { list ->
                adapter.setData(list)
                adapter.notifyDataSetChanged()
                if (list.isEmpty()) {
                    binding.tvNoDataAnak.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataAnak.visibility = View.GONE
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

        adapter.listener = object : AdapterListAnak.OnItemClickListenerHome {
            override fun onItemClick(item: AddDataAnak) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_listCatatanFragment_to_detailListAnakFragment,
                    bundle
                )
            }
        }

        return root
    }
}