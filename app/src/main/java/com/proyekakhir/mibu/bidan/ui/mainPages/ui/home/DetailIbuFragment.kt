package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.CatatanAnakAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.CatatanKesehatanAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.CatatanNifasAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.AddCatatanViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.databinding.FragmentDetailIbuBinding

class DetailIbuFragment : Fragment() {
    private var _binding: FragmentDetailIbuBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvKesehatan: RecyclerView
    private lateinit var rvNifas: RecyclerView
    private lateinit var rvAnak: RecyclerView
    private lateinit var nifasList: ArrayList<AddNifasData>
    private lateinit var anakList: ArrayList<AddDataAnak>
    private lateinit var adapterNifas: CatatanNifasAdapter
    private lateinit var adapterAnak: CatatanAnakAdapter
    private lateinit var kesehatanList: ArrayList<AddKesehatanKehamilanData>
    private lateinit var adapterKesehatan: CatatanKesehatanAdapter
    private lateinit var viewModel: AddCatatanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailIbuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(AddCatatanViewModel::class.java)

        val itemData = arguments?.getSerializable("itemData") as? IbuHamilData

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvNamaIbu.text = ": ${itemData?.fullname}"
        binding.tvUmurIbu.text = ": ${itemData?.umur}"
        binding.tvNoTelponIbu.text = ": ${itemData?.noTelepon}"
        binding.tvNikIbu.text = ": ${itemData?.nik}"
        binding.tvNamaSuami.text = ": ${itemData?.namaSuami}"
        binding.tvAlamat.text = ": ${itemData?.alamat}"

        binding.btnAddCatatanKesehatan.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("itemData", itemData)
            findNavController().navigate(
                R.id.action_detailIbuFragment_to_addCatatanKesehatanFragment,
                bundle
            )
        }

        binding.btnAddCatatanNifas.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("itemData", itemData)
            findNavController().navigate(
                R.id.action_detailIbuFragment_to_addCatatanNifasFragment,
                bundle
            )
        }

        binding.btnAddAnak.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("itemData", itemData)
            findNavController().navigate(
                R.id.action_detailIbuFragment_to_addDataAnakFragment,
                bundle
            )
        }

        kesehatanList = arrayListOf<AddKesehatanKehamilanData>()
        adapterKesehatan = CatatanKesehatanAdapter(kesehatanList)
        rvKesehatan = binding.rvHistoryCatatanKesehatan
        rvKesehatan.layoutManager = LinearLayoutManager(requireContext())
        rvKesehatan.setHasFixedSize(true)
        rvKesehatan.adapter = adapterKesehatan

        nifasList = arrayListOf<AddNifasData>()
        adapterNifas = CatatanNifasAdapter(nifasList)
        rvNifas = binding.rvHistoryCatatanNifas
        rvNifas.layoutManager = LinearLayoutManager(requireContext())
        rvNifas.setHasFixedSize(true)
        rvNifas.adapter = adapterNifas

        anakList = arrayListOf<AddDataAnak>()
        adapterAnak = CatatanAnakAdapter(anakList)
        rvAnak = binding.rvCatatanAnak
        rvAnak.layoutManager = LinearLayoutManager(requireContext())
        rvAnak.setHasFixedSize(true)
        rvAnak.adapter = adapterAnak

        val uid = itemData?.uid
        if (uid != null) {
            viewModel.getCatatanKesehatanList(uid)
            viewModel.catatanKesehatanList.observe(viewLifecycleOwner, Observer { list ->
                adapterKesehatan.setData(list)
                adapterKesehatan.notifyDataSetChanged()
                if (list.isEmpty()) {
                    binding.tvNoDataKesehatan.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataKesehatan.visibility = View.GONE
                }
            })

            viewModel.getCatatanNifasList(uid)
            viewModel.catatanNifasList.observe(viewLifecycleOwner, Observer { list ->
                adapterNifas.setData(list)
                adapterNifas.notifyDataSetChanged()
                if (list.isEmpty()) {
                    binding.tvNoDataNifas.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataNifas.visibility = View.GONE
                }
            })

            viewModel.getCatatanAnakList(uid)
            viewModel.catatanAnakList.observe(viewLifecycleOwner, Observer { list ->
                adapterAnak.setData(list)
                adapterAnak.notifyDataSetChanged()
                if (list.isEmpty()) {
                    binding.tvNoDataAnak.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataAnak.visibility = View.GONE
                }
            })
        }

        val pbKesehatan = binding.pbHistoryKesehatan
        val pbNifas = binding.pbHistoryNifas
        val pbAnak = binding.pbDataAnak
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading) {
                pbKesehatan.visibility = View.VISIBLE
                pbNifas.visibility = View.VISIBLE
                pbAnak.visibility = View.VISIBLE
            } else {
                pbKesehatan.visibility = View.GONE
                pbNifas.visibility = View.GONE
                pbAnak.visibility = View.GONE
            }
        })

        adapterKesehatan.listener = object : CatatanKesehatanAdapter.OnItemClickListener {
            override fun onItemClick(item: AddKesehatanKehamilanData) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_detailIbuFragment_to_detailKesehatanFragment2,
                    bundle
                )
            }
        }

        adapterNifas.listener = object : CatatanNifasAdapter.OnItemClickListener {
            override fun onItemClick(item: AddNifasData) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_detailIbuFragment_to_detailNifasFragment,
                    bundle
                )
            }
        }

        adapterAnak.listener = object : CatatanAnakAdapter.OnItemClickListener {
            override fun onItemClick(item: AddDataAnak) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_detailIbuFragment_to_detailAnakFragment,
                    bundle
                )
            }
        }


        return root
    }
}