package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.KesehatanKehamilanData
import com.proyekakhir.mibu.databinding.FragmentBidanHomeBinding
import com.proyekakhir.mibu.databinding.FragmentLaporanCatatanBinding

class LaporanCatatanFragment : DialogFragment() {
    private var _binding: FragmentLaporanCatatanBinding? = null
    private val binding get() = _binding!!
    private lateinit var pieChart: PieChart
    private var kesehatanCount = 0
    private var nifasCount = 0
    private var anakCount = 0
    private var isAnakLoaded = false
    private var isKesehatanDataLoaded = false
    private var isNifasDataLoaded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaporanCatatanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        pieChart = binding.pieChart

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchKesehatanData()
        fetchNifasData()
        fetchAnakData()
        binding.btnToList.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_listCatatanFragment)
            dialog?.dismiss()
        }
    }

    private fun fetchKesehatanData() {
        val databaseKesehatan = FirebaseDatabase.getInstance().getReference("CatatanKesehatanKehamilan")
        databaseKesehatan.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                kesehatanCount = snapshot.children.sumBy { it.childrenCount.toInt() }
                isKesehatanDataLoaded = true
                updateChartIfDataLoaded()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })
    }

    private fun fetchAnakData() {
        val databaseKesehatan = FirebaseDatabase.getInstance().getReference("CatatanAnak")
        databaseKesehatan.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                anakCount = snapshot.children.sumBy { it.childrenCount.toInt() }
                isAnakLoaded = true
                updateChartIfDataLoaded()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })
    }

    private fun fetchNifasData() {
        val databaseNifas = FirebaseDatabase.getInstance().getReference("CatatanNifas")
        databaseNifas.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nifasCount = snapshot.children.sumBy { it.childrenCount.toInt() }
                isNifasDataLoaded = true
                updateChartIfDataLoaded()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })
    }

    private fun updateChartIfDataLoaded() {
        if (isKesehatanDataLoaded && isNifasDataLoaded && isAnakLoaded) {
            updateChart(kesehatanCount, nifasCount, anakCount)
        }
    }

    private fun updateChart(kesehatanCount: Int, nifasCount: Int, anakCount: Int) {
        val entries = listOf(
            PieEntry(kesehatanCount.toFloat(), "Catatan Kehamilan"),
            PieEntry(nifasCount.toFloat(), "Catatan Nifas"),
            PieEntry(anakCount.toFloat(), "Catatan Anak"),
        )

        val dataSet = PieDataSet(entries, "Data Sizes")
        dataSet.colors = listOf(ColorTemplate.COLORFUL_COLORS[0], ColorTemplate.COLORFUL_COLORS[1], ColorTemplate.COLORFUL_COLORS[2]) // Set different colors for each slice
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 16f

        val pieData = PieData(dataSet)
        pieChart.setEntryLabelColor(Color.WHITE) // Set label text color
        pieChart.setEntryLabelTextSize(8f) // Set label text size to 12dp (smaller)
        pieChart.legend.textSize = 5f
        pieChart.data = pieData
        pieChart.description.isEnabled = false
        pieChart.invalidate() // refresh


        // Set OnChartValueSelectedListener
        pieChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (e is PieEntry) {
                    val label = e.label
                    val value = e.value.toInt().toString()
                    val message = "Category: $label, Value: $value"
                    showToast(message)
                }
            }

            override fun onNothingSelected() {
                // Handle nothing selected if needed
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT, // Width
            WindowManager.LayoutParams.WRAP_CONTENT  // Height
        )
    }
}