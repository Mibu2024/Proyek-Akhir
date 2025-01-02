<?php

namespace App\Http\Controllers;

use App\Models\JenisImunisasi;
use Illuminate\Http\Request;

class JenisImunisasiController extends Controller
{
    public function index()
    {
        $jenisImunisasi = JenisImunisasi::all();

        return view('jenis_imunisasi.index', compact('jenisImunisasi'));
    }
}
