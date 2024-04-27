package com.proyekakhir.mibu.user.ui.kehamilan.nifas

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
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.CatatanNifasAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.databinding.FragmentTabNifasBinding
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.kehamilan.CatatanKehamilanViewModel
import com.proyekakhir.mibu.user.ui.kehamilan.kesehatan.ListKesehatanAdapter
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel

class TabNifasFragment : Fragment() {

    private var _binding: FragmentTabNifasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CatatanKehamilanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(CatatanKehamilanViewModel::class.java)

        val list = arrayListOf<NifasModel>()
        val adapter = ListNifasAdapter(list)
        val rvNifas = binding.rvTabNifas
        rvNifas.layoutManager = LinearLayoutManager(requireContext())
        rvNifas.setHasFixedSize(true)
        rvNifas.adapter = adapter

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            viewModel.getCatatanNifasList(uid)
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

        adapter.listener = object : ListNifasAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: NifasModel) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_catatan_kehamilan_to_detailNifasFragment2,
                    bundle
                )
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}