package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.anak

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.LaporanViewModel
import com.proyekakhir.mibu.databinding.FragmentTabListAnakBinding
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import org.apache.commons.math3.analysis.function.Add
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class TabListAnakFragment : Fragment() {
    private var _binding: FragmentTabListAnakBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LaporanViewModel
    private lateinit var barChart: BarChart
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

        barChart = binding.barChart

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
                    updateBarChart(list)
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

    private fun updateBarChart(list: List<AddDataAnak>) {
        val entries = mutableListOf<BarEntry>()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val monthlyData = IntArray(12) { 0 }  // Array to hold counts for each month

        // Group data by month
        for (data in list) {
            val date = dateFormat.parse(data.tanggalPeriksa)
            val calendar = Calendar.getInstance()
            date?.let {
                calendar.time = it
                val month = calendar.get(Calendar.MONTH)
                monthlyData[month]++
            }
        }

        // Create entries for the bar chart
        for (i in 0..11) {
            entries.add(BarEntry(i.toFloat(), monthlyData[i].toFloat()))
        }

        // Create dataset and configure chart
        val dataSet = BarDataSet(entries, "Monthly Data")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 16f
        dataSet.setDrawValues(true)

        // Set value formatter to display data size inside each bar
        dataSet.valueFormatter = object : ValueFormatter() {
            override fun getBarLabel(entry: BarEntry?): String {
                return entry?.y?.toInt().toString()
            }
        }

        val barData = BarData(dataSet)
        barData.setValueTextSize(12f)
        barData.setValueTextColor(Color.BLACK)
        barChart.data = barData
        barChart.description.isEnabled = false
        barChart.setFitBars(true)

        // Set X-axis labels
        val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(months)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = 12

        // Configure Y-axis to show values inside bars
        val leftAxis = barChart.axisLeft
        leftAxis.setDrawGridLines(true)
        leftAxis.axisMinimum = 0f
        leftAxis.granularity = 1f

        val rightAxis = barChart.axisRight
        rightAxis.setDrawGridLines(true)
        rightAxis.axisMinimum = 0f
        rightAxis.granularity = 1f

        barChart.legend.isEnabled = false

        barChart.invalidate()
    }
}