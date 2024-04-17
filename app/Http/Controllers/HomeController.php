<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\DataIbuHamil;
use RealRashid\SweetAlert\Facades\Alert;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index(Request $request)
    {
        $search          = $request->input('search');
        $perPage         = $request->input('per_page', 5);
        $data_ibu_hamils = DataIbuHamil::where('nama', 'like', "%$search%")->paginate($perPage);
        $currentPage = $data_ibu_hamils->currentPage();
        return view('home', compact('data_ibu_hamils', 'currentPage'));
    }

    public function create()
    {
        return view('createDataIbuHamil');
    }

    public function store(Request $request)
    {
        $request->validate([
            'tanggal'              => 'required',
            'nama'                 => 'required',
            'keluhan'              => 'required',
            'tekanan_darah'        => 'required|integer',
            'berat_badan'          => 'required|integer',
            'umur_kehamilan'       => 'required',
            'tinggi_fundus'        => 'required|integer',
            'letak_janin'          => 'required',
            'denyut_jantung_janin' => 'required|integer',
            'hasil_lab'            => 'required',
            'tindakan'             => 'required',
            'kaki_bengkak'         => 'required',
            'nasihat'              => 'required',
        ], [
            'tanggal.required'              => 'Tanggal wajib diisi.',
            'nama.required'                 => 'Nama wajib diisi.',
            'keluhan.required'              => 'Keluhan wajib diisi.',
            'tekanan_darah.required'        => 'Tekanan darah wajib diisi.',
            'tekanan_darah.integer'         => 'Tekanan darah harus berupa angka.',
            'berat_badan.required'          => 'Berat badan wajib diisi.',
            'berat_badan.integer'           => 'Berat badan harus berupa angka.',
            'umur_kehamilan.required'       => 'Umur kehamilan wajib diisi.',
            'tinggi_fundus.required'        => 'Tinggi fundus wajib diisi.',
            'tinggi_fundus.integer'         => 'Tinggi fundus harus berupa angka.',
            'letak_janin.required'          => 'Letak janin wajib diisi.',
            'denyut_jantung_janin.required' => 'Denyut jantung janin wajib diisi.',
            'denyut_jantung_janin.integer'  => 'Denyut jantung janin harus berupa angka.',
            'hasil_lab.required'            => 'Hasil lab wajib diisi.',
            'tindakan.required'             => 'Tindakan wajib diisi.',
            'kaki_bengkak.required'         => 'Kaki bengkak wajib diisi.',
            'nasihat.required'              => 'Nasihat wajib diisi.',
        ]);
        

        DataIbuHamil::create($request->all());
        toast('Data Berhasil Ditambahkan','success');
        return redirect()->route('home');
    }

    public function edit($id)
    {
        $data_ibu_hamils = DataIbuHamil::find($id);
        return view('editDataIbuHamil', compact('data_ibu_hamils'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'tanggal'              => 'required',
            'nama'                 => 'required',
            'keluhan'              => 'required',
            'tekanan_darah'        => 'required|integer',
            'berat_badan'          => 'required|integer',
            'umur_kehamilan'       => 'required',
            'tinggi_fundus'        => 'required|integer',
            'letak_janin'          => 'required',
            'denyut_jantung_janin' => 'required|integer',
            'hasil_lab'            => 'required',
            'tindakan'             => 'required',
            'kaki_bengkak'         => 'required',
            'nasihat'              => 'required',
        ], [
            'tanggal.required'              => 'Tanggal wajib diisi.',
            'nama.required'                 => 'Nama wajib diisi.',
            'keluhan.required'              => 'Keluhan wajib diisi.',
            'tekanan_darah.required'        => 'Tekanan darah wajib diisi.',
            'tekanan_darah.integer'         => 'Tekanan darah harus berupa angka.',
            'berat_badan.required'          => 'Berat badan wajib diisi.',
            'berat_badan.integer'           => 'Berat badan harus berupa angka.',
            'umur_kehamilan.required'       => 'Umur kehamilan wajib diisi.',
            'tinggi_fundus.required'        => 'Tinggi fundus wajib diisi.',
            'tinggi_fundus.integer'         => 'Tinggi fundus harus berupa angka.',
            'letak_janin.required'          => 'Letak janin wajib diisi.',
            'denyut_jantung_janin.required' => 'Denyut jantung janin wajib diisi.',
            'denyut_jantung_janin.integer'  => 'Denyut jantung janin harus berupa angka.',
            'hasil_lab.required'            => 'Hasil lab wajib diisi.',
            'tindakan.required'             => 'Tindakan wajib diisi.',
            'kaki_bengkak.required'         => 'Kaki bengkak wajib diisi.',
            'nasihat.required'              => 'Nasihat wajib diisi.',
        ]);
        
        $data_ibu_hamils                       = DataIbuHamil::find($id);
        $data_ibu_hamils->tanggal              = $request->tanggal;
        $data_ibu_hamils->nama                 = $request->nama;
        $data_ibu_hamils->keluhan              = $request->keluhan;
        $data_ibu_hamils->tekanan_darah        = $request->tekanan_darah;
        $data_ibu_hamils->berat_badan          = $request->berat_badan;
        $data_ibu_hamils->umur_kehamilan       = $request->umur_kehamilan;
        $data_ibu_hamils->tinggi_fundus        = $request->tinggi_fundus;
        $data_ibu_hamils->letak_janin          = $request->letak_janin;
        $data_ibu_hamils->denyut_jantung_janin = $request->denyut_jantung_janin;
        $data_ibu_hamils->hasil_lab            = $request->hasil_lab;
        $data_ibu_hamils->tindakan             = $request->tindakan;
        $data_ibu_hamils->kaki_bengkak         = $request->kaki_bengkak;
        $data_ibu_hamils->nasihat              = $request->nasihat;
        $data_ibu_hamils->save();

        toast('Data Berhasil Diubah','success');
        return redirect()->route('home');
    }

    public function delete($id)
    {
        $data_ibu_hamils = DataIbuHamil::find($id);
        $data_ibu_hamils->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route('home'));
    }

}
