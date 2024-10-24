<?php

namespace App\Http\Controllers;
use App\Models\DataIbuHamil;
use App\Models\DataKehamilan;
use App\Models\DataNifas;
use App\Models\DataKesehatan;
use App\Models\User;

use Illuminate\Http\Request;

class DataKehamilanController extends Controller
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
        $data_kehamilans = DataKehamilan::where('id_ibu', 'like', "%$search%")->paginate($perPage);
        $currentPage = $data_kehamilans->currentPage();
        return view('', compact('data_kehamilans', 'currentPage'));
    }

    public function detail($id, $id_kehamilan)
    {
        // Fetch the record based on the ID from the 'DataIbuHamil' model
        $ibuHamil = DataIbuHamil::find($id);
        

        // Check if the record exists
        if (!$ibuHamil) {
            return redirect()->route('data-ibu-hamil.index')->with('error', 'Data not found.');
        }

        // Fetch health records that match the id_ibu from ibuHamil
        $healthRecords = DataKesehatan::where('id_ibu', $ibuHamil->id)
                                            ->where('id_kehamilan', $id_kehamilan)
                                            ->get();

        // Fetch nifas records that match the id_ibu from ibuHamil
        $nifasRecords = DataNifas::where('id_ibu', $ibuHamil->id)
                                            ->where('id_kehamilan', $id_kehamilan)
                                            ->get();

        // Pass the data to the view
        return view('data-ibu-hamil/detail-page/detail-kehamilan', compact( 'ibuHamil', 'healthRecords', 'nifasRecords'));
    }

    public function create($id)
    {
        $ibuHamil = DataIbuHamil::find($id);  // Fetch the related Ibu Hamil based on the ID
        return view('data-ibu-hamil.detail-page.form.create-data-kehamilan', compact('ibuHamil'));
    }


    public function store(Request $request)
    {
        $request->validate([
            'id_ibu'                => 'required|exists:data_ibu_hamils,id',
            'tanggal_kehamilan'     => 'required',
            'tanggal_hpl'           => 'required',
            'kehamilan_ke'          => 'required|integer',
        ], [
            'tanggal_kehamilan.required'         => 'Tanggal kehamilan wajib diisi.',
            'tanggal_hpl.required'               => 'Tanggal HPL wajib diisi.',
            'kehamilan_ke.required'              => 'Kehamilan ke wajib diisi.',
            'id_ibu.required'                    => 'id ibu ke wajib diisi.',
        ]);

        $data = $request->all();

        DataKehamilan::create($data);
        toast('Data Berhasil Ditambahkan', 'success');
        return redirect()->route('data-ibu-hamil.detail', ['id' => $request->id_ibu]);
    }


    public function edit($id)
    {
        $data_kehamilans = DataKehamilan::find($id);

        return view('', compact('data_kehamilans'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'id_ibu'                => 'required|exists:data_ibu_hamils,id',
            'tanggal_kehamilan'     => 'required',
            'tanggal_hpl'           => 'required',
            'kehamilan_ke'          => 'required|integer',
        ], [
            'tanggal_kehamilan.required'         => 'Tanggal kehamilan wajib diisi.',
            'tanggal_hpl.required'               => 'Tanggal HPL wajib diisi.',
            'kehamilan_ke.required'              => 'Kehamilan ke wajib diisi.',
        ]);
        
        $data_kehamilans                       = DataKehamilan::find($id);
        $data_kehamilans->tanggal_kehamilan    = $request->tanggal;
        $data_kehamilans->tanggal_hpl          = $request->nama_ibu;
        $data_kehamilans->id_ibu               = $request->id_ibu;
        $data_kehamilans->kehamilan_ke         = $request->keluhan;
        $data_kehamilans->save();

        toast('Data Berhasil Diubah','success');
        return redirect()->route('');
    }

    public function delete($id)
    {
        $data_kehamilans = DataKehamilan::find($id);
        $data_kehamilans->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route(''));
    }

}
