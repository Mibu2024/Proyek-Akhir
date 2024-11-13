<?php

namespace App\Http\Controllers;

use App\Models\DataNifas;
use App\Models\DataIbuHamil;
use App\Models\DataKehamilan;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Response;

class DataNifasController extends Controller
{
    public function index(Request $request)
    {
        $search = $request->input('search');
        $perPage = $request->input('per_page', 5);
        $sort = $request->input('sort', 'Paling Baru');

        $userId = auth()->user()->id;
        $ibuHamilIds = DataIbuHamil::where('user_id', $userId)->pluck('id');

        $query = DataNifas::whereIn('id_ibu', $ibuHamilIds)
            ->where('nama_ibu', 'like', "%$search%");

        switch ($sort) {
            case 'oldest':
                $query->orderBy('created_at', 'asc');
                break;
            case 'a-z':
                $query->orderBy('nama_ibu', 'asc');
                break;
            case 'z-a':
                $query->orderBy('nama_ibu', 'desc');
                break;
            default:
                $query->orderBy('created_at', 'desc');
        }

        $data_nifas = $query->paginate($perPage);
        $currentPage = $data_nifas->currentPage();
        
        return view('data-catatan-nifas/data-nifas', compact('data_nifas', 'currentPage', 'sort'));
    }


    public function create($id, $id_kehamilan)
    {
        $data_ibu_hamils = DataIbuHamil::find($id);
        $kehamilan = DataKehamilan::findOrFail($id_kehamilan);
        return view('data-catatan-nifas/create-data-nifas', compact('kehamilan', 'data_ibu_hamils'));
    }

    public function store(Request $request)
    {
        $request->validate([
            'tanggal'                   => 'required',
            'id_ibu'                    => 'required|exists:data_ibu_hamils,id',
            'kunjungan_nifas'           => 'required',
            'hasil_periksa_payudara'    => 'required',
            'hasil_periksa_pendarahan'  => 'required',
            'hasil_periksa_jalan_lahir' => 'required',
            'vitamin_a'                 => 'required',
            'masalah'                   => 'required',
            'tindakan'                  => 'required',
        ], [
            'tanggal.required'                   => 'Tanggal wajib diisi.',
            'kunjungan_nifas.required'           => 'Kunjungan Nifas wajib diisi.',
            'hasil_periksa_payudara.required'    => 'Hasil Periksa Payudara wajib diisi.',
            'hasil_periksa_pendarahan.required'  => 'Hasil Periksa Pendarahan wajib diisi.',
            'hasil_periksa_jalan_lahir.required' => 'Hasil Periksa Jalan Lahir wajib diisi.',
            'vitamin_a.required'                 => 'Vitamin A wajib diisi.',
            'masalah.required'                   => 'Masalah wajib diisi.',
            'tindakan.required'                  => 'Tindakan wajib diisi.',
        ]);

        $data = $request->all();
        $data['nama_ibu'] = DataIbuHamil::find($request->id_ibu)->nama_ibu;
        

        DataNifas::create($data);
        toast('Data Berhasil Ditambahkan','success');
        return redirect()->route('data-kehamilan.detail', ['id' => $request->id_ibu, 'id_kehamilan' => $request->id_kehamilan]);
    }

    public function edit($id, $id_ibu)
    {
        $data_ibu_hamils = DataIbuHamil::find($id_ibu);
        $data_nifas = DataNifas::find($id);
        return view('data-catatan-nifas/edit-data-nifas', compact('data_nifas', 'data_ibu_hamils'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'tanggal'                   => 'required',
            'id_ibu'                    => 'required|exists:data_ibu_hamils,id',
            'kunjungan_nifas'           => 'required',
            'hasil_periksa_payudara'    => 'required',
            'hasil_periksa_pendarahan'  => 'required',
            'hasil_periksa_jalan_lahir' => 'required',
            'vitamin_a'                 => 'required',
            'masalah'                   => 'required',
            'tindakan'                  => 'required',
        ], [
            'tanggal.required'                   => 'Tanggal wajib diisi.',
            'kunjungan_nifas.required'           => 'Kunjungan Nifas wajib diisi.',
            'hasil_periksa_payudara.required'    => 'Hasil Periksa Payudara wajib diisi.',
            'hasil_periksa_pendarahan.required'  => 'Hasil Periksa Pendarahan wajib diisi.',
            'hasil_periksa_jalan_lahir.required' => 'Hasil Periksa Jalan Lahir wajib diisi.',
            'vitamin_a.required'                 => 'Vitamin A wajib diisi.',
            'masalah.required'                   => 'Masalah wajib diisi.',
            'tindakan.required'                  => 'Tindakan wajib diisi.',
        ]);
        
        $data_nifas                            = DataNifas::find($id);
        $data_nifas->tanggal                   = $request->tanggal;
        $data_nifas->id_ibu                    = $request->id_ibu;
        $data_nifas->kunjungan_nifas           = $request->kunjungan_nifas;
        $data_nifas->hasil_periksa_payudara    = $request->hasil_periksa_payudara;
        $data_nifas->hasil_periksa_pendarahan  = $request->hasil_periksa_pendarahan;
        $data_nifas->hasil_periksa_jalan_lahir = $request->hasil_periksa_jalan_lahir;
        $data_nifas->vitamin_a                 = $request->vitamin_a;
        $data_nifas->masalah                   = $request->masalah;
        $data_nifas->tindakan                  = $request->tindakan;
        $data_nifas->save();

        toast('Data Berhasil Diubah','success');
        return redirect()->route('data-kehamilan.detail', ['id' => $request->id_ibu, 'id_kehamilan' => $data_nifas->id_kehamilan]);
    }

    public function delete($id)
    {
        $data_nifas = DataNifas::find($id);
        $data_nifas->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route('data-kehamilan.detail', ['id' => $data_nifas->id_ibu, 'id_kehamilan' => $data_nifas->id_kehamilan]));
    }

    public function download()
    {
        $data_nifas = DataNifas::all();

        $csvData = $this->generateCSV($data_nifas);

        $headers = array(
            'Content-Type' => 'text/csv',
            'Content-Disposition' => 'attachment; filename=data_nifas_mibu.csv',
        );

        return Response::make($csvData, 200, $headers);
    }

    private function generateCSV($data)
    {
        $csv = '';

        $csv .= "Data Nifas - MIBU \n \n";

        $csv .= "No,Tanggal Periksa,Nama Ibu,Kunjungan Nifas,Hasil Periksa Payudara,Hasil Periksa Pendarahan,Hasil Periksa Jalan Lahir,Vitamin A,Masalah,Tindakan\n";

        $counter = 1;

        foreach ($data as $row) {
            $csv .= "{$counter},{$row->tanggal},{$row->nama_ibu},{$row->kunjungan_nifas},{$row->hasil_periksa_payudara},{$row->hasil_periksa_pendarahan},{$row->hasil_periksa_jalan_lahir},{$row->vitamin_a},{$row->masalah},{$row->tindakan}\n";
            
            $counter++;
        }

        return $csv;
    }
}
