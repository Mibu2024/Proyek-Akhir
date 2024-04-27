package com.proyekakhir.mibu.user.ui.kehamilan.kesehatan

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
import com.proyekakhir.mibu.databinding.FragmentTabKesehatanBinding
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.kehamilan.CatatanKehamilanViewModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel
import com.proyekakhir.mibu.user.ui.kehamilan.nifas.ListNifasAdapter

class TabKesehatanFragment : Fragment() {
    private var _binding: FragmentTabKesehatanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CatatanKehamilanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabKesehatanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(CatatanKehamilanViewModel::class.java)

        val list = arrayListOf<KesehatanModel>()
        val adapter = ListKesehatanAdapter(list)
        val rvKesehatan = binding.rvTabKesehatan
        rvKesehatan.layoutManager = LinearLayoutManager(requireContext())
        rvKesehatan.setHasFixedSize(true)
        rvKesehatan.adapter = adapter

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            viewModel.getCatatanKesehatanList(uid)
            viewModel.catatanKesehatanList.observe(viewLifecycleOwner, Observer { list ->
                adapter.setData(list)
                adapter.notifyDataSetChanged()
                if (list.isEmpty()) {
                    binding.tvNoDataKesehatan.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataKesehatan.visibility = View.GONE
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

        adapter.listener = object : ListKesehatanAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: KesehatanModel) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_catatan_kehamilan_to_detailKesehatanFragment,
                    bundle
                )
            }
        }

        return root
    }

}