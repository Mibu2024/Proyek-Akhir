<?php

namespace App\Http\Controllers;

use App\Models\AnakImunisasi;
use Illuminate\Http\Request;

class ImunisasiController extends Controller
{
    public function store(Request $request)
    {
        $validated = $request->validate([
            'id_anak' => 'required|exists:data_anaks,id',
            'jenis_imunisasi' => 'required|exists:jenis_imunisasis,id',
            'tanggal_imunisasi' => 'required|date',
            'merek' => 'required|string|max:255',
            'nama_pemeriksa' => 'required|string|max:255',
        ]);

        $imunisasi = AnakImunisasi::create([
            'id_anak' => $validated['id_anak'],
            'id_jenis' => $validated['jenis_imunisasi'],
            'tanggal' => $validated['tanggal_imunisasi'],
            'merek' => $validated['merek'],
            'nama_pemeriksa' => $validated['nama_pemeriksa'],
        ]);

        return redirect()->back()->with('success', 'Data imunisasi berhasil disimpan.');
    }

    public function show($id)
    {
        $imunisasi = AnakImunisasi::with(['anak', 'jenisImunisasi'])->findOrFail($id);

        return view('imunisasi.show', compact('imunisasi'));
    }

    public function delete($id)
    {
        $imunisasi = AnakImunisasi::findOrFail($id);

        $imunisasi->delete();

        return redirect()->back()->with('success', 'Data imunisasi berhasil dihapus.');
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate([
            'jenis_imunisasi' => 'required|exists:jenis_imunisasis,id',
            'tanggal_imunisasi' => 'required|date',
            'merek' => 'required|string|max:255',
            'nama_pemeriksa' => 'required|string|max:255',
        ]);

        $imunisasi = AnakImunisasi::findOrFail($id);

        $imunisasi->id_jenis = $validated['jenis_imunisasi'];
        $imunisasi->tanggal = $validated['tanggal_imunisasi'];
        $imunisasi->merek = $validated['merek'];
        $imunisasi->nama_pemeriksa = $validated['nama_pemeriksa'];
        $imunisasi->save();

        return redirect()->back()->with('success', 'Data imunisasi berhasil diperbarui.');
    }
}
