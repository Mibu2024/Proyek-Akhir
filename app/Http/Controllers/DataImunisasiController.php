<?php

namespace App\Http\Controllers;

use App\Models\DataAnak;
use Illuminate\Http\Request;
use App\Models\DataImunisasi;
use Illuminate\Support\Facades\Response;

class DataImunisasiController extends Controller
{
    public function index(Request $request)
    {
        $search = $request->input('search');
        $perPage = $request->input('per_page', 5);
        $data_imunisasis = DataImunisasi::where('nama_anak', 'like', "%$search%")->paginate($perPage);
        $currentPage = $data_imunisasis->currentPage();
        return view('data-imunisasi', compact('data_imunisasis', 'currentPage'));
    }

    public function create()
    {
        $data_anaks = DataAnak::all();

        return view('create-data-imunisasi', compact('data_anaks'));
    }

    public function store(Request $request)
    {
        $request->validate(
            [
                'tanggal' => 'required',
                'nama_anak' => 'required',
                'id_anak'    => 'required|exists:data_anaks,id',
                'nama_pemeriksa' => 'required',
                'hepatitis_b' => 'required',
                'bcg' => 'required',
                'polio_tetes_1' => 'required',
                'dpt_hb_hib_1' => 'required',
                'polio_tetes_2' => 'required',
                'rota_virus_1' => 'required',
                'pcv_1' => 'required',
                'dpt_hb_hib_2' => 'required',
                'polio_tetes_3' => 'required',
                'rota_virus_2' => 'required',
                'pcv_2' => 'required',
                'dpt_hb_hib_3' => 'required',
                'polio_tetes_4' => 'required',
                'polio_suntik_1' => 'required',
                'rota_virus_3' => 'required',
                'campak_rubella' => 'required',
                'polio_suntik_2' => 'required',
                'japanese_encephalitis' => 'required',
                'pcv_3' => 'required',
                'dpt_hb_hib_lanjutan' => 'required',
                'campak_rubella_lanjutan' => 'required',
            ],
            [
                'tanggal.required' => 'Tanggal periksa wajib diisi.',
                'nama_anak.required' => 'Nama anak wajib diisi.',
                'nama_pemeriksa.required' => 'Nama pemeriksa wajib diisi.',
                'hepatitis_b.required' => 'Imunisasi hepatitis B wajib Diisi',
                'bcg.required' => 'Imunisasi BCG wajib Diisi',
                'polio_tetes_1.required' => 'Imunisasi polio tetes 1 wajib Diisi',
                'dpt_hb_hib_1.required' => 'Imunisasi dpt-hb-hib 1 wajib Diisi',
                'polio_tetes_2.required' => 'Imunisasi polio tetes 2 wajib Diisi',
                'rota_virus_1.required' => 'Imunisasi rotavirus 1 wajib Diisi',
                'pcv_1.required' => 'Imunisasi pcv 1 wajib Diisi',
                'dpt_hb_hib_2.required' => 'Imunisasi dpt-hb-hib 2 wajib Diisi',
                'polio_tetes_3.required' => 'Imunisasi polio tetes 3 wajib Diisi',
                'rota_virus_2.required' => 'Imunisasi rotavirus 2 wajib Diisi',
                'pcv_2.required' => 'Imunisasi pcv 2 wajib Diisi',
                'dpt_hb_hib_3.required' => 'Imunisasi dpt-hb-hib 3 wajib Diisi',
                'polio_tetes_4.required' => 'Imunisasi polio tetes 4 wajib Diisi',
                'polio_suntik_1.required' => 'Imunisasi polio suntik 1 wajib Diisi',
                'rota_virus_3.required' => 'Imunisasi rotavirus 3 wajib Diisi',
                'campak_rubella.required' => 'Imunisasi campak rubella wajib Diisi',
                'polio_suntik_2.required' => 'Imunisasi polio suntik 2 wajib Diisi',
                'japanese_encephalitis.required' => 'Imunisasi japanese encephalitis wajib Diisi',
                'pcv_3.required' => 'Imunisasi pcv 3 wajib Diisi',
                'dpt_hb_hib_lanjutan.required' => 'Imunisasi dpt-hb-hib lanjutan wajib Diisi',
                'campak_rubella_lanjutan.required' => 'Imunisasi campak rubella lanjutan wajib Diisi',
            ],
        );

        $data = $request->all();
        $data['nama_anak'] = DataAnak::find($request->id_anak)->nama_anak;

        DataImunisasi::create($data);
        toast('Data Berhasil Ditambahkan', 'success');
        return redirect()->route('data-imunisasi.index');
    }

    public function edit($id)
    {
        // Query untuk mendapatkan data anak-anak dari tabel `data_anaks`
        $data_anaks = DataAnak::all();

        $data_imunisasis = DataImunisasi::find($id);
        return view('edit-data-imunisasi', compact('data_imunisasis', 'data_anaks'));
    }

    public function update(Request $request, $id)
    {
        $request->validate(
            [
                'tanggal' => 'required',
                'nama_anak' => 'required',
                'id_anak'    => 'required|exists:data_anaks,id',
                'nama_pemeriksa' => 'required',
                'hepatitis_b' => 'required',
                'bcg' => 'required',
                'polio_tetes_1' => 'required',
                'dpt_hb_hib_1' => 'required',
                'polio_tetes_2' => 'required',
                'rota_virus_1' => 'required',
                'pcv_1' => 'required',
                'dpt_hb_hib_2' => 'required',
                'polio_tetes_3' => 'required',
                'rota_virus_2' => 'required',
                'pcv_2' => 'required',
                'dpt_hb_hib_3' => 'required',
                'polio_tetes_4' => 'required',
                'polio_suntik_1' => 'required',
                'rota_virus_3' => 'required',
                'campak_rubella' => 'required',
                'polio_suntik_2' => 'required',
                'japanese_encephalitis' => 'required',
                'pcv_3' => 'required',
                'dpt_hb_hib_lanjutan' => 'required',
                'campak_rubella_lanjutan' => 'required',
            ],
            [
                'tanggal.required' => 'Tanggal periksa wajib diisi.',
                'nama_anak.required' => 'Nama anak wajib diisi.',
                'nama_pemeriksa.required' => 'Nama pemeriksa wajib diisi.',
                'hepatitis_b.required' => 'Imunisasi hepatitis B wajib Diisi',
                'bcg.required' => 'Imunisasi BCG wajib Diisi',
                'polio_tetes_1.required' => 'Imunisasi polio tetes 1 wajib Diisi',
                'dpt_hb_hib_1.required' => 'Imunisasi dpt-hb-hib 1 wajib Diisi',
                'polio_tetes_2.required' => 'Imunisasi polio tetes 2 wajib Diisi',
                'rota_virus_1.required' => 'Imunisasi rotavirus 1 wajib Diisi',
                'pcv_1.required' => 'Imunisasi pcv 1 wajib Diisi',
                'dpt_hb_hib_2.required' => 'Imunisasi dpt-hb-hib 2 wajib Diisi',
                'polio_tetes_3.required' => 'Imunisasi polio tetes 3 wajib Diisi',
                'rota_virus_2.required' => 'Imunisasi rotavirus 2 wajib Diisi',
                'pcv_2.required' => 'Imunisasi pcv 2 wajib Diisi',
                'dpt_hb_hib_3.required' => 'Imunisasi dpt-hb-hib 3 wajib Diisi',
                'polio_tetes_4.required' => 'Imunisasi polio tetes 4 wajib Diisi',
                'polio_suntik_1.required' => 'Imunisasi polio suntik 1 wajib Diisi',
                'rota_virus_3.required' => 'Imunisasi rotavirus 3 wajib Diisi',
                'campak_rubella.required' => 'Imunisasi campak rubella wajib Diisi',
                'polio_suntik_2.required' => 'Imunisasi polio suntik 2 wajib Diisi',
                'japanese_encephalitis.required' => 'Imunisasi japanese encephalitis wajib Diisi',
                'pcv_3.required' => 'Imunisasi pcv 3 wajib Diisi',
                'dpt_hb_hib_lanjutan.required' => 'Imunisasi dpt-hb-hib lanjutan wajib Diisi',
                'campak_rubella_lanjutan.required' => 'Imunisasi campak rubella lanjutan wajib Diisi',
            ],
        );

        $data_imunisasis = DataImunisasi::find($id);
        $data_imunisasis->tanggal = $request->tanggal;
        $data_imunisasis->nama_anak = $request->nama_anak;
        $data_imunisasis->id_anak = $request->id_anak;
        $data_imunisasis->hepatitis_b = $request->hepatitis_b;
        $data_imunisasis->bcg = $request->bcg;
        $data_imunisasis->polio_tetes_1 = $request->polio_tetes_1;
        $data_imunisasis->dpt_hb_hib_1 = $request->dpt_hb_hib_1;
        $data_imunisasis->polio_tetes_2 = $request->polio_tetes_2;
        $data_imunisasis->rota_virus_1 = $request->rota_virus_1;
        $data_imunisasis->pcv_1 = $request->pcv_1;
        $data_imunisasis->dpt_hb_hib_2 = $request->dpt_hb_hib_2;
        $data_imunisasis->polio_tetes_3 = $request->polio_tetes_3;
        $data_imunisasis->rota_virus_2 = $request->rota_virus_2;
        $data_imunisasis->pcv_2 = $request->pcv_2;
        $data_imunisasis->dpt_hb_hib_3 = $request->dpt_hb_hib_3;
        $data_imunisasis->polio_tetes_4 = $request->polio_tetes_4;
        $data_imunisasis->polio_suntik_1 = $request->polio_suntik_1;
        $data_imunisasis->rota_virus_3 = $request->rota_virus_3;
        $data_imunisasis->campak_rubella = $request->campak_rubella;
        $data_imunisasis->polio_suntik_2 = $request->polio_suntik_2;
        $data_imunisasis->japanese_encephalitis = $request->japanese_encephalitis;
        $data_imunisasis->pcv_3 = $request->pcv_3;
        $data_imunisasis->dpt_hb_hib_lanjutan = $request->dpt_hb_hib_lanjutan;
        $data_imunisasis->campak_rubella_lanjutan = $request->campak_rubella_lanjutan;
        $data_imunisasis->nama_pemeriksa = $request->nama_pemeriksa;
        $data_imunisasis->save();

        toast('Data Berhasil Diubah', 'success');
        return redirect()->route('data-imunisasi.index');
    }

    public function delete($id)
    {
        $data_imunisasis = DataImunisasi::find($id);
        $data_imunisasis->delete();
        toast('Data Berhasil Dihapus', 'success');
        return redirect(route('data-imunisasi.index'));
    }

    public function download()
    {
        $data_imunisasis = DataImunisasi::all();

        $csvData = $this->generateCSV($data_imunisasis);

        $headers = [
            'Content-Type' => 'text/csv',
            'Content-Disposition' => 'attachment; filename=data_imunisasi_mibu.csv',
        ];

        return Response::make($csvData, 200, $headers);
    }

    private function generateCSV($data)
    {
        $csv = '';

        $csv .= "Data Imunisasi - MIBU \n \n";

        $csv .= "No,
        Tanggal Periksa,
        Nama Anak,
        Imunisasi Hepatitis B,
        Imunisasi BCG,
        Imunisasi Polio Tetes 1,
        Imunisasi DPT-HB-Hib 1,
        Imunisasi Polio Tetes 2,
        Imunisasi Rota virus 1,
        Imunisasi PCV 1,
        Imunisasi DPT-HB-Hib 2,
        Imunisasi Polio Tetes 3,
        Imunisasi Rotavirus 2,
        Imunisasi PCV 2,
        Imunisasi DPT-HB-Hib 3,
        Imunisasi Polio Tetes 4,
        Imunisasi Polio Suntik 1,
        Imunisasi Rotavirus 3,
        Imunsasi Campak Rubella,
        Imunisasi Polio Suntik 2,
        Imunisasi Japanese Encephalitis,
        Imunisasi PCV 3,
        Imunisasi DPT-HB-Hib Lanjutan,
        Imunisasi Campak Rubella Lanjutan,
        Nama Pemeriksa\n";

        $counter = 1;

        foreach ($data as $row) {
            $csv .= "{$counter},
            {$row->tanggal},
            {$row->nama_anak},
            {$row->hepatitis_b},
            {$row->bcg},
            {$row->polio_tetes_1},
            {$row->dpt_hb_hib_1},
            {$row->polio_tetes_2},
            {$row->rota_virus_1},
            {$row->pcv_1},
            {$row->dpt_hb_hib_2},
            {$row->polio_tetes_3},
            {$row->rota_virus_2},
            {$row->pcv_2},
            {$row->dpt_hb_hib_3},
            {$row->polio_tetes_4},
            {$row->polio_suntik_1},
            {$row->rota_virus_3},
            {$row->campak_rubella},
            {$row->polio_suntik_2},
            {$row->japanese_encephalitis},
            {$row->pcv_3},
            {$row->dpt_hb_hib_lanjutan},
            {$row->campak_rubella_lanjutan},
            {$row->nama_pemeriksa}\n";

            $counter++;
        }

        return $csv;
    }
}
