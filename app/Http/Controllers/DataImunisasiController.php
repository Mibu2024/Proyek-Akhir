<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\DataImunisasi;

class DataImunisasiController extends Controller
{
    public function index(Request $request)
    {
        $search      = $request->input('search');
        $perPage     = $request->input('per_page', 5);
        $data_imunisasis  = DataImunisasi::where('nama_anak', 'like', "%$search%")->paginate($perPage);
        $currentPage = $data_imunisasis->currentPage();
        return view('data-imunisasi', compact('data_imunisasis', 'currentPage'));
    }

    public function create()
    {
        return view('create-data-imunisasi');
    }

    public function store(Request $request)
    {
        $request->validate([
        'tanggal'                          => 'required',
        'nama_anak'                        => 'required',
        'imunisasi_dpt_hb_hib_1_polio_2'   => 'required',
        'imunisasi_dpt_hb_hib_2_polio_3'   => 'required',
        'imunisasi_dpt_hb_hib_3_polio_4'   => 'required',
        'imunisasi_campak'                 => 'required',
        'imunisasi_dpt_hb_hib_1_dosis'     => 'required',
        'imunisasi_campak_rubella_1_dosis' => 'required',
        'imunisasi_campak_rubella_dan_dt'  => 'required',
        'imunisasi_tetanus_diphteria_td'   => 'required',
        'nama_pemeriksa'                   => 'required',
        ], [
            'tanggal.required'                          => 'Tanggal periksa wajib diisi.',
            'nama_anak.required'                        => 'Nama anak wajib diisi.',
            'imunisasi_dpt_hb_hib_1_polio_2.required'   => 'Imunisasi DPT HB Hib 1 Polio 2 wajib diisi.',
            'imunisasi_dpt_hb_hib_2_polio_3.required'   => 'Imunisasi DPT HB Hib 2 Polio 3 wajib diisi.',
            'imunisasi_dpt_hb_hib_3_polio_4.required'   => 'Imunisasi DPT HB Hib 3 Polio 4 wajib diisi.',
            'imunisasi_campak.required'                 => 'Imunisasi Campak wajib diisi.',
            'imunisasi_dpt_hb_hib_1_dosis.required'     => 'Imunisasi DPT HB Hib 1 dosis wajib diisi.',
            'imunisasi_campak_rubella_1_dosis.required' => 'Imunisasi Campak Rubella 1 dosis wajib diisi.',
            'imunisasi_campak_rubella_dan_dt.required'  => 'Imunisasi Campak Rubella dan DT wajib diisi.',
            'imunisasi_tetanus_diphteria_td.required'   => 'Imunisasi Tetanus Diphteria TD wajib diisi.',
            'nama_pemeriksa.required'                   => 'Nama pemeriksa wajib diisi.',
        ]);

        DataImunisasi::create($request->all());
        toast('Data Berhasil Ditambahkan','success');
        return redirect()->route('data-imunisasi.index');
    }

    public function edit($id)
    {
        $data_imunisasis = DataImunisasi::find($id);
        return view('edit-data-imunisasi', compact('data_imunisasis'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'tanggal'                          => 'required',
            'nama_anak'                        => 'required',
            'imunisasi_dpt_hb_hib_1_polio_2'   => 'required',
            'imunisasi_dpt_hb_hib_2_polio_3'   => 'required',
            'imunisasi_dpt_hb_hib_3_polio_4'   => 'required',
            'imunisasi_campak'                 => 'required',
            'imunisasi_dpt_hb_hib_1_dosis'     => 'required',
            'imunisasi_campak_rubella_1_dosis' => 'required',
            'imunisasi_campak_rubella_dan_dt'  => 'required',
            'imunisasi_tetanus_diphteria_td'   => 'required',
            'nama_pemeriksa'                   => 'required',
            ], [
                'tanggal.required'                          => 'Tanggal periksa wajib diisi.',
                'nama_anak.required'                        => 'Nama anak wajib diisi.',
                'imunisasi_dpt_hb_hib_1_polio_2.required'   => 'Imunisasi DPT HB Hib 1 Polio 2 wajib diisi.',
                'imunisasi_dpt_hb_hib_2_polio_3.required'   => 'Imunisasi DPT HB Hib 2 Polio 3 wajib diisi.',
                'imunisasi_dpt_hb_hib_3_polio_4.required'   => 'Imunisasi DPT HB Hib 3 Polio 4 wajib diisi.',
                'imunisasi_campak.required'                 => 'Imunisasi Campak wajib diisi.',
                'imunisasi_dpt_hb_hib_1_dosis.required'     => 'Imunisasi DPT HB Hib 1 dosis wajib diisi.',
                'imunisasi_campak_rubella_1_dosis.required' => 'Imunisasi Campak Rubella 1 dosis wajib diisi.',
                'imunisasi_campak_rubella_dan_dt.required'  => 'Imunisasi Campak Rubella dan DT wajib diisi.',
                'imunisasi_tetanus_diphteria_td.required'   => 'Imunisasi Tetanus Diphteria TD wajib diisi.',
                'nama_pemeriksa.required'                   => 'Nama pemeriksa wajib diisi.',
            ]);
        
        $data_imunisasis                                   = DataImunisasi::find($id);
        $data_imunisasis->tanggal                          = $request->tanggal;
        $data_imunisasis->nama_anak                        = $request->nama_anak;
        $data_imunisasis->imunisasi_dpt_hb_hib_1_polio_2   = $request->imunisasi_dpt_hb_hib_1_polio_2;
        $data_imunisasis->imunisasi_dpt_hb_hib_2_polio_3   = $request->imunisasi_dpt_hb_hib_2_polio_3;
        $data_imunisasis->imunisasi_dpt_hb_hib_3_polio_4   = $request->imunisasi_dpt_hb_hib_3_polio_4;
        $data_imunisasis->imunisasi_campak                 = $request->imunisasi_campak;
        $data_imunisasis->imunisasi_dpt_hb_hib_1_dosis     = $request->imunisasi_dpt_hb_hib_1_dosis;
        $data_imunisasis->imunisasi_campak_rubella_1_dosis = $request->imunisasi_campak_rubella_1_dosis;
        $data_imunisasis->imunisasi_campak_rubella_dan_dt  = $request->imunisasi_campak_rubella_dan_dt;
        $data_imunisasis->imunisasi_tetanus_diphteria_td   = $request->imunisasi_tetanus_diphteria_td;
        $data_imunisasis->nama_pemeriksa                   = $request->nama_pemeriksa;
        $data_imunisasis->save();

        toast('Data Berhasil Diubah','success');
        return redirect()->route('data-imunisasi.index');
    }

    public function delete($id)
    {
        $data_imunisasis = DataImunisasi::find($id);
        $data_imunisasis->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route('data-imunisasi.index'));
    }
}
