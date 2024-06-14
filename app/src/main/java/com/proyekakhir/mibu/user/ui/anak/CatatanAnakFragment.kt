package com.proyekakhir.mibu.user.ui.anak

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.network.NetworkConnection
import com.proyekakhir.mibu.databinding.FragmentCatatanAnakBinding
import com.proyekakhir.mibu.databinding.FragmentTabNifasBinding
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.anak.model.AnakModel
import com.proyekakhir.mibu.user.ui.kehamilan.CatatanKehamilanViewModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel
import com.proyekakhir.mibu.user.ui.kehamilan.nifas.ListNifasAdapter

class CatatanAnakFragment : Fragment() {
    private var _binding: FragmentCatatanAnakBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CatatanAnakViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatatanAnakBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(CatatanAnakViewModel::class.java)

        val list = arrayListOf<AnakModel>()
        val adapter = ListAnakAdapter(list)
        val rvAnak = binding.rvDataAnak
        rvAnak.layoutManager = LinearLayoutManager(requireContext())
        rvAnak.setHasFixedSize(true)
        rvAnak.adapter = adapter

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            viewModel.getCatatanAnakList(uid)
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

        adapter.listener = object : ListAnakAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: AnakModel) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_catatan_anak_to_detailAnakUserFragment,
                    bundle
                )
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check connection
        val networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(requireActivity()) {
            if (isAdded) {
                if (it) {
                    binding.ivConnection.visibility = View.GONE
                    binding.tvConnection.visibility = View.GONE
                    binding.rvDataAnak.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Not Connected", Toast.LENGTH_SHORT).show()
                    binding.ivConnection.visibility = View.VISIBLE
                    binding.tvConnection.visibility = View.VISIBLE
                    binding.rvDataAnak.visibility = View.GONE
                    binding.textView5.visibility = View.GONE
                    binding.tvNoDataAnak.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE

                }
            }
        }
    }

}