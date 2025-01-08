<?php

namespace App\Http\Controllers;

use App\Models\DataIbuHamil;
use App\Models\DataLayananKb;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Response;

class DataLayananKbController extends Controller
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
        $search = $request->input('search');
        $perPage = $request->input('per_page', 5);
        $sort = $request->input('sort', 'Paling Baru');

        $userId = auth()->user()->id;
        $ibuHamilIds = DataIbuHamil::where('user_id', $userId)->pluck('id');

        $query = DataLayananKb::where('nama_ibu', 'like', "%$search%");

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

        $data_layanan_kbs = $query->paginate($perPage);
        $currentPage = $data_layanan_kbs->currentPage();
        
        return view('data-layanan-kb/data-layanan-kb', compact('data_layanan_kbs', 'currentPage', 'sort'));
    }


    public function create($id)
    {
        $data_ibu_hamils = DataIbuHamil::find($id);

        return view('data-layanan-kb/create-data-layanan-kb', compact('data_ibu_hamils'));
    }

    public function store(Request $request)
    {
        $request->validate([
            'tanggal_praktik'      => 'required',
            'id_ibu'               => 'required|exists:data_ibu_hamils,id',
            'tekanan_darah'        => 'required|integer',
            'berat_badan'          => 'required|integer',
            'jenis_kb'             => 'required',
            'merk_kb'              => 'required',
            'tanggal_kembali'      => 'required',
            'keluhan'              => 'required'
        ], [
            'tanggal_praktik.required'      => 'Tanggal wajib diisi.',
            'tekanan_darah.required'        => 'Tekanan darah wajib diisi.',
            'tekanan_darah.integer'         => 'Tekanan darah harus berupa angka.',
            'berat_badan.required'          => 'Berat badan wajib diisi.',
            'berat_badan.integer'           => 'Berat badan harus berupa angka.',
            'jenis_kb.required'             => 'Pilih Jenis Kontrasepsi',
            'merk_kb.required'              => 'Isi Merk KB',
            'tanggal_kembali.required'      => 'Pilih tanggal kembali',
            'keluhan.required'              => 'Isi Keluhan Pasien'
        ]);

        $data = $request->all();
        $data['nama_ibu'] = DataIbuHamil::find($request->id_ibu)->nama_ibu;

        DataLayananKb::create($data);
        toast('Data Berhasil Ditambahkan', 'success');
        return redirect()->route('data-ibu-hamil.detail', ['id' => $request->id_ibu]);
    }

    public function edit($id, $id_ibu)
    {
        $data_ibu_hamils = DataIbuHamil::find($id_ibu);
        $data_layanan_kbs = DataLayananKb::find($id);
        return view('data-layanan-kb/edit-data-layanan-kb', compact('data_layanan_kbs', 'data_ibu_hamils'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'tanggal_praktik'      => 'required',
            'id_ibu'               => 'required|exists:data_ibu_hamils,id',
            'tekanan_darah'        => 'required|integer',
            'berat_badan'          => 'required|integer',
            'jenis_kb'             => 'required',
            'merk_kb'              => 'required',
            'tanggal_kembali'      => 'required',
            'keluhan'              => 'required'
        ], [
            'tanggal_praktik.required'      => 'Tanggal Praktik wajib diisi.',
            'tekanan_darah.required'        => 'Tekanan darah wajib diisi.',
            'tekanan_darah.integer'         => 'Tekanan darah harus berupa angka.',
            'berat_badan.required'          => 'Berat badan wajib diisi.',
            'berat_badan.integer'           => 'Berat badan harus berupa angka.',
            'jenis_kb.required'             => 'Pilih Jenis KB',
            'merk_kb.required'              => 'Isi Merk KB',
            'tanggal_kembali'               => 'Pilih Tanggal Kembali',
            'keluhan.required'              => 'Isi Keluhan Pasien'
        ]);
        
        $data_layanan_kbs                       = DataLayananKb::find($id);
        $data_layanan_kbs->tanggal_praktik      = $request->tanggal_praktik;
        $data_layanan_kbs->id_ibu               = $request->id_ibu;
        $data_layanan_kbs->tekanan_darah        = $request->tekanan_darah;
        $data_layanan_kbs->berat_badan          = $request->berat_badan;
        $data_layanan_kbs->jenis_kb             = $request->jenis_kb;
        $data_layanan_kbs->merk_kb              = $request->merk_kb;
        $data_layanan_kbs->tanggal_kembali      = $request->tanggal_kembali;
        $data_layanan_kbs->keluhan              = $request->keluhan;
        $data_layanan_kbs->save();

        
        toast('Data Berhasil Diubah','success');
        return redirect()->route('data-ibu-hamil.detail', ['id' => $request->id_ibu]);
    }

    public function delete($id)
    {
        $data_layanan_kbs = DataLayananKb::find($id);
        $data_layanan_kbs->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route('data-ibu-hamil.detail', ['id' => $data_layanan_kbs->id_ibu]));
    }

    public function download()
    {
        $data_layanan_kbs = DataLayananKb::all();

        $csvData = $this->generateCSV($data_layanan_kbs);

        $headers = array(
            'Content-Type' => 'text/csv',
            'Content-Disposition' => 'attachment; filename=data_layanan_kb_mibu.csv',
        );

        return Response::make($csvData, 200, $headers);
    }

    private function generateCSV($data)
    {
        $csv = '';

        $csv .= "Data Layanan KB - MIBU \n \n";

        $csv .= "No,Tanggal Praktik,Nama Ibu,Tekanan Darah,Berat Badan,Jenis KB, Merk KB, Tanggal Kembali,Keluhan\n";

        $counter = 1;

        foreach ($data as $row) {
            $tekanan_darah        = $row->tekanan_darah . " mmHg";
            $berat_badan          = $row->berat_badan . " Kg";
            $tanggal_praktik = \Carbon\Carbon::parse($row->tanggal_praktik)->format('Y/m/d');
            $tanggal_kembali = \Carbon\Carbon::parse($row->tanggal_kembali)->format('Y/m/d');

            $csv .= "{$counter},{$tanggal_praktik},{$row->nama_ibu},{$tekanan_darah},{$berat_badan},{$row->jenis_kb}, {$row->merk_kb}, {$tanggal_kembali},{$row->keluhan}\n";
            
            $counter++;
        }

        return $csv;
    }
}
