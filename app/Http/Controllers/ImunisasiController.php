<?php

namespace App\Http\Controllers;

use App\Models\AnakImunisasi;
use App\Models\JenisImunisasi;
use App\Models\DataAnak;
use Illuminate\Http\Request;

class ImunisasiController extends Controller
{
    public function store(Request $request)
    {
        // Validasi input request dari form
        $validated = $request->validate([
            'id_anak' => 'required|exists:data_anaks,id',  // Validasi jika id_anak ada di tabel data_anaks
            'jenis_imunisasi' => 'required|exists:jenis_imunisasis,id',  // Validasi jika jenis imunisasi ada di tabel jenis_imunisasis
            'tanggal_imunisasi' => 'required|date',  // Validasi jika tanggal sesuai format tanggal
            'merek' => 'required|string|max:255',  // Validasi merek imunisasi
            'nama_pemeriksa' => 'required|string|max:255',  // Validasi nama pemeriksa
        ]);

        // Menyimpan data imunisasi ke tabel anak_imunisasis
        $imunisasi = AnakImunisasi::create([
            'id_anak' => $validated['id_anak'],
            'id_jenis' => $validated['jenis_imunisasi'],
            'tanggal' => $validated['tanggal_imunisasi'],
            'merek' => $validated['merek'],
            'nama_pemeriksa' => $validated['nama_pemeriksa'],
        ]);

        // Redirect kembali dengan pesan sukses
        return redirect()->back()->with('success', 'Data imunisasi berhasil disimpan.');
    }

    public function show($id)
    {
        // Mengambil data imunisasi beserta relasi anak dan jenis imunisasi
        $imunisasi = AnakImunisasi::with(['anak', 'jenisImunisasi'])->findOrFail($id);

        // Menampilkan halaman show dengan data imunisasi
        return view('imunisasi.show', compact('imunisasi'));
    }

    public function delete($id)
    {
        // Temukan data berdasarkan ID
        $imunisasi = AnakImunisasi::findOrFail($id);

        // Hapus data
        $imunisasi->delete();

        // Redirect atau kembali dengan pesan sukses
        return redirect()->back()->with('success', 'Data imunisasi berhasil dihapus.');
    }
}
