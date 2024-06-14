package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.anak

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
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

object ListAnakExporter {
    fun createXlsx(arsip: ArrayList<AddDataAnak>, context: Context) {
        try {
            val strDate = SimpleDateFormat("dd-MM-yyyy HH-mm-ss", Locale.getDefault()).format(Date())
            val root = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "FileExcel")
            if (!root.exists())
                root.mkdirs()
            val path = File(root, "/Rekap Laporan Pemeriksaan Anak $strDate.xlsx")

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

            val sheet = workbook.createSheet("Data Laporan Pemeriksaan Anak")
            var row = sheet.createRow(0)

            var cell = row.createCell(0)
            cell.setCellValue("Nama Anak")
            cell.cellStyle = headerStyle

            cell = row.createCell(1)
            cell.setCellValue("Tanggal Lahir")
            cell.cellStyle = headerStyle

            cell = row.createCell(2)
            cell.setCellValue("Umur")
            cell.cellStyle = headerStyle

            cell = row.createCell(3)
            cell.setCellValue("Berat Badan")
            cell.cellStyle = headerStyle

            cell = row.createCell(4)
            cell.setCellValue("dptHb1Polio2")
            cell.cellStyle = headerStyle

            cell = row.createCell(5)
            cell.setCellValue("dptHb2Polio3")
            cell.cellStyle = headerStyle

            cell = row.createCell(6)
            cell.setCellValue("dptHb3Polio4")
            cell.cellStyle = headerStyle

            cell = row.createCell(7)
            cell.setCellValue("Campak")
            cell.cellStyle = headerStyle

            cell = row.createCell(8)
            cell.setCellValue("dptHb1Dosis")
            cell.cellStyle = headerStyle

            cell = row.createCell(9)
            cell.setCellValue("campakRubella1Dosis")
            cell.cellStyle = headerStyle

            cell = row.createCell(10)
            cell.setCellValue("campakRubellaDt")
            cell.cellStyle = headerStyle

            cell = row.createCell(11)
            cell.setCellValue("tetanus")
            cell.cellStyle = headerStyle

            cell = row.createCell(12)
            cell.setCellValue("Nama Pemeriksa")
            cell.cellStyle = headerStyle

            cell = row.createCell(13)
            cell.setCellValue("Periksa Selanjutnya")
            cell.cellStyle = headerStyle

            cell = row.createCell(14)
            cell.setCellValue("Nama Ibu")
            cell.cellStyle = headerStyle

            for (i in arsip.indices) {
                row = sheet.createRow(i + 1)

                cell = row.createCell(0)
                cell.setCellValue(arsip[i].namaAnak)

                cell = row.createCell(1)
                cell.setCellValue(arsip[i].tanggalLahir)

                cell = row.createCell(2)
                cell.setCellValue(arsip[i].umur)

                cell = row.createCell(3)
                cell.setCellValue(arsip[i].beratBadan)

                cell = row.createCell(4)
                cell.setCellValue(arsip[i].dptHb1Polio2)

                cell = row.createCell(5)
                cell.setCellValue(arsip[i].dptHb2Polio3)

                cell = row.createCell(6)
                cell.setCellValue(arsip[i].dptHb3Polio4)

                cell = row.createCell(7)
                cell.setCellValue(arsip[i].campak)

                cell = row.createCell(8)
                cell.setCellValue(arsip[i].dptHb1Dosis)

                cell = row.createCell(9)
                cell.setCellValue(arsip[i].campakRubella1Dosis)

                cell = row.createCell(10)
                cell.setCellValue(arsip[i].campakRubellaDt)

                cell = row.createCell(11)
                cell.setCellValue(arsip[i].tetanus)

                cell = row.createCell(12)
                cell.setCellValue(arsip[i].namaPemeriksa)

                cell = row.createCell(13)
                cell.setCellValue(arsip[i].periksaSelanjutnya)

                cell = row.createCell(14)
                cell.setCellValue(arsip[i].namaIbu)
            }

            workbook.write(outputStream)
            outputStream.close()
            Toast.makeText(context, "Data berhasil di ekspor!", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}