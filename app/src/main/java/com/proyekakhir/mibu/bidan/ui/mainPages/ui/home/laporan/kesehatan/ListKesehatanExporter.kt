package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.kesehatan

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
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

object ListKesehatanExporter {
    fun createXlsx(arsip: ArrayList<AddKesehatanKehamilanData>, context: Context) {
        try {
            val strDate = SimpleDateFormat("dd-MM-yyyy HH-mm-ss", Locale.getDefault()).format(Date())
            val root = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "FileExcel")
            if (!root.exists())
                root.mkdirs()
            val path = File(root, "/Rekap Laporan Pemeriksaan Kesehatan Kehamilan $strDate.xlsx")

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

            val sheet = workbook.createSheet("Data Laporan Pemeriksaan Kesehatan Kehamilan")
            var row = sheet.createRow(0)

            var cell = row.createCell(0)
            cell.setCellValue("Tanggal Periksa")
            cell.cellStyle = headerStyle

            cell = row.createCell(1)
            cell.setCellValue("Nama Pasien")
            cell.cellStyle = headerStyle

            cell = row.createCell(2)
            cell.setCellValue("Tekanan Darah")
            cell.cellStyle = headerStyle

            cell = row.createCell(3)
            cell.setCellValue("Berat Badan")
            cell.cellStyle = headerStyle

            cell = row.createCell(4)
            cell.setCellValue("Umur Kehamilan")
            cell.cellStyle = headerStyle

            cell = row.createCell(5)
            cell.setCellValue("Tinggi Fundus")
            cell.cellStyle = headerStyle

            cell = row.createCell(6)
            cell.setCellValue("Letak Janin")
            cell.cellStyle = headerStyle

            cell = row.createCell(7)
            cell.setCellValue("Denyut Janin")
            cell.cellStyle = headerStyle

            cell = row.createCell(8)
            cell.setCellValue("Hasil lab")
            cell.cellStyle = headerStyle

            cell = row.createCell(9)
            cell.setCellValue("Tindakan")
            cell.cellStyle = headerStyle

            cell = row.createCell(10)
            cell.setCellValue("Kaki Bengkak")
            cell.cellStyle = headerStyle

            cell = row.createCell(11)
            cell.setCellValue("Nasihat")
            cell.cellStyle = headerStyle

            cell = row.createCell(12)
            cell.setCellValue("Keluhan")
            cell.cellStyle = headerStyle

            cell = row.createCell(13)
            cell.setCellValue("Nama Pemeriksa")
            cell.cellStyle = headerStyle

            cell = row.createCell(14)
            cell.setCellValue("Tempat Periksa")
            cell.cellStyle = headerStyle

            for (i in arsip.indices) {
                row = sheet.createRow(i + 1)

                cell = row.createCell(0)
                cell.setCellValue(arsip[i].tanggalPeriksa)

                cell = row.createCell(1)
                cell.setCellValue(arsip[i].nama)

                cell = row.createCell(2)
                cell.setCellValue(arsip[i].tekananDarah)

                cell = row.createCell(3)
                cell.setCellValue(arsip[i].beratBadan)

                cell = row.createCell(4)
                cell.setCellValue(arsip[i].umurKehamilan)

                cell = row.createCell(5)
                cell.setCellValue(arsip[i].tinggiFundus)

                cell = row.createCell(6)
                cell.setCellValue(arsip[i].letakJanin)

                cell = row.createCell(7)
                cell.setCellValue(arsip[i].denyutJanin)

                cell = row.createCell(8)
                cell.setCellValue(arsip[i].hasilLab)

                cell = row.createCell(9)
                cell.setCellValue(arsip[i].tindakan)

                cell = row.createCell(10)
                cell.setCellValue(arsip[i].kakiBengkak)

                cell = row.createCell(11)
                cell.setCellValue(arsip[i].nasihat)

                cell = row.createCell(12)
                cell.setCellValue(arsip[i].keluhan)

                cell = row.createCell(13)
                cell.setCellValue(arsip[i].namaPemeriksa)

                cell = row.createCell(14)
                cell.setCellValue(arsip[i].tempatPeriksa)
            }

            workbook.write(outputStream)
            outputStream.close()
            Toast.makeText(context, "Data berhasil di ekspor!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}