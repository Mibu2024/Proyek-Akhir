<?php

namespace App\Http\Controllers;

use App\Models\JenisImunisasi;
use Illuminate\Http\Request;

class JenisImunisasiController extends Controller
{
    public function index()
    {
        // Ambil semua jenis imunisasi
        $jenisImunisasi = JenisImunisasi::all();

        // Kembalikan view dengan data yang diperlukan
        return view('data-ibu-hamil.detail-page.components.status-imunisasi-section', compact('jenisImunisasi'));
    }
}
