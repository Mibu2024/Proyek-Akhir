package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.nifas

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.HorizontalAlignment
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ListNifasExporter {
    fun createXlsx(arsip: ArrayList<AddNifasData>, context: Context) {
        try {
            val strDate = SimpleDateFormat("dd-MM-yyyy HH-mm-ss", Locale.getDefault()).format(Date())
            val root = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "FileExcel")
            if (!root.exists())
                root.mkdirs()
            val path = File(root, "/Rekap Laporan Pemeriksaan Nifas $strDate.xlsx")

            val workbook = XSSFWorkbook()
            val outputStream = FileOutputStream(path)

            val headerStyle = workbook.createCellStyle()
            headerStyle.alignment = HorizontalAlignment.CENTER
            headerStyle.fillForegroundColor = IndexedColors.BLUE_GREY.index
            headerStyle.fillPattern = FillPatternType.SOLID_FOREGROUND
            headerStyle.setBorderTop(BorderStyle.MEDIUM)
            headerStyle.setBorderBottom(BorderStyle.MEDIUM)
            headerStyle.setBorderRight(BorderStyle.MEDIUM)
            headerStyle.setBorderLeft(BorderStyle.MEDIUM)

            val font = workbook.createFont()
            font.fontHeightInPoints = 12.toShort()
            font.setColor(IndexedColors.WHITE.index)
            headerStyle.setFont(font)

            val sheet = workbook.createSheet("Data Laporan Pemeriksaan Nifas")
            var row = sheet.createRow(0)

            var cell = row.createCell(0)
            cell.setCellValue("Tanggal Periksa")
            cell.cellStyle = headerStyle

            cell = row.createCell(1)
            cell.setCellValue("Kunjungan Ke")
            cell.cellStyle = headerStyle

            cell = row.createCell(2)
            cell.setCellValue("Periksa Asi")
            cell.cellStyle = headerStyle

            cell = row.createCell(3)
            cell.setCellValue("Periksa Pendarahan")
            cell.cellStyle = headerStyle

            cell = row.createCell(4)
            cell.setCellValue("Periksa Jalan Lahir")
            cell.cellStyle = headerStyle

            cell = row.createCell(5)
            cell.setCellValue("Vitamin A")
            cell.cellStyle = headerStyle

            cell = row.createCell(6)
            cell.setCellValue("Masalah")
            cell.cellStyle = headerStyle

            cell = row.createCell(7)
            cell.setCellValue("Tindakan")
            cell.cellStyle = headerStyle

            cell = row.createCell(8)
            cell.setCellValue("Nama")
            cell.cellStyle = headerStyle

            for (i in arsip.indices) {
                row = sheet.createRow(i + 1)

                cell = row.createCell(0)
                cell.setCellValue(arsip[i].tanggalPeriksa)

                cell = row.createCell(1)
                cell.setCellValue(arsip[i].kunjunganKe)

                cell = row.createCell(2)
                cell.setCellValue(arsip[i].periksaAsi)

                cell = row.createCell(3)
                cell.setCellValue(arsip[i].periksaPendarahan)

                cell = row.createCell(4)
                cell.setCellValue(arsip[i].periksaJalanLahir)

                cell = row.createCell(5)
                cell.setCellValue(arsip[i].vitaminA)

                cell = row.createCell(6)
                cell.setCellValue(arsip[i].masalah)

                cell = row.createCell(7)
                cell.setCellValue(arsip[i].tindakan)

                cell = row.createCell(8)
                cell.setCellValue(arsip[i].nama)
            }

            workbook.write(outputStream)
            outputStream.close()
            Toast.makeText(context, "Data berhasil di ekspor!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}