<?php

namespace App\Http\Controllers;

use App\Models\User;
use App\Models\DataAnak;
use App\Models\DataNifas;
use App\Models\DataIbuHamil;
use Illuminate\Http\Request;
use App\Models\DataKehamilan;
use App\Models\DataLayananKb;
use Illuminate\Support\Facades\Response;
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
        $this->middleware(['auth', 'verified']);
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
        $sort = $request->input('sort', 'Paling Baru'); // Default to 'latest' if no sort option is selected

        // Sort based on the selected option
        $query = DataIbuHamil::where('user_id', auth()->id())
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
            default: // 'latest'
                $query->orderBy('created_at', 'desc');
        }

        $data_ibu_hamils = $query->paginate($perPage);
        $currentPage = $data_ibu_hamils->currentPage();
        
        return view('data-ibu-hamil/home', compact('data_ibu_hamils', 'currentPage', 'sort'));
    }


    public function detail($id, Request $request)
    {
        // Fetch the record based on the ID from the 'DataIbuHamil' model
        $ibuHamil = DataIbuHamil::find($id);

        // Check if the record exists
        if (!$ibuHamil) {
            return redirect()->route('data-ibu-hamil.index')->with('error', 'Data not found.');
        }

        // Define month names
        $monthNames = [
            1 => 'Januari', 2 => 'Februari', 3 => 'Maret', 4 => 'April',
            5 => 'Mei', 6 => 'Juni', 7 => 'Juli', 8 => 'Agustus',
            9 => 'September', 10 => 'Oktober', 11 => 'November', 12 => 'Desember'
        ];

        // Fetch and name each month filter separately
        $kehamilanMonth = $request->input('kehamilan_month');
        $nifasMonth = $request->input('nifas_month');
        $anakMonth = $request->input('anak_month');
        $kbMonth = $request->input('kb_month');

        // Get the names for each month filter
        $kehamilanMonthName = $kehamilanMonth ? $monthNames[$kehamilanMonth] : 'Bulan';
        $nifasMonthName = $nifasMonth ? $monthNames[$nifasMonth] : 'Bulan';
        $anakMonthName = $anakMonth ? $monthNames[$anakMonth] : 'Bulan';
        $kbMonthName = $kbMonth ? $monthNames[$kbMonth] : 'Bulan';

        // Fetch kehamilan records with month filtering
        $kehamilanRecords = DataKehamilan::where('id_ibu', $ibuHamil->id)
            ->when($kehamilanMonth, function ($query) use ($kehamilanMonth) {
                $query->whereMonth('tanggal_kehamilan', $kehamilanMonth); // Replace 'tanggal' with the actual date column
            })
            ->get();

        // Fetch nifas records with month filtering
        $nifasRecords = DataNifas::where('id_ibu', $ibuHamil->id)
            ->when($nifasMonth, function ($query) use ($nifasMonth) {
                $query->whereMonth('tanggal', $nifasMonth); // Replace 'tanggal' with the actual date column
            })
            ->get();

        // Fetch anak records with month filtering
        $anakRecords = DataAnak::where('id_ibu', $ibuHamil->id)
            ->when($anakMonth, function ($query) use ($anakMonth) {
                $query->whereMonth('tanggal', $anakMonth); // Replace 'tanggal' with the actual date column
            })
            ->get();

        // Fetch KB records with month filtering
        $kbRecords = DataLayananKb::where('id_ibu', $ibuHamil->id)
            ->when($kbMonth, function ($query) use ($kbMonth) {
                $query->whereMonth('tanggal_praktik', $kbMonth); // Replace 'tanggal' with the actual date column
            })
            ->get();

        // Pass the data to the view
        return view('data-ibu-hamil/detail-page/detail-ibu', compact(
            'ibuHamil', 
            'kehamilanRecords', 'kehamilanMonthName',
            'nifasRecords', 'nifasMonthName',
            'anakRecords', 'anakMonthName',
            'kbRecords', 'kbMonthName'
        ));
    }







    public function create()
    {
        $users = User::all();

        return view('data-ibu-hamil/create-data-ibu-hamil', compact('users'));
    }

    public function store(Request $request)
    {
        // Validasi data yang dikirim dari form
        $request->validate([
            'nama_ibu'           => 'required',
            'umur_ibu'           => 'required|integer',
            'alamat'             => 'required',
            'email'              => 'required|email',
            'nik'                => 'required|numeric',
            'no_telepon'         => 'required|numeric',
            'nama_suami'         => 'required',
            'umur_suami'         => 'required|integer',
            'password'           => 'required|min:8',
            'no_jkn_faskes_tk_1' => 'required',
            'no_jkn_rujukan'     => 'required',
            'gol_darah'          => 'required',
            'pekerjaan'          => 'required',
            'user_id'            => 'required|exists:users,id',
        ], [
            'nama_ibu.required'           => 'Nama Ibu wajib diisi.',
            'umur_ibu.required'           => 'Umur Ibu wajib diisi.',
            'umur_ibu.integer'            => 'Umur Ibu harus berupa angka.',
            'alamat.required'             => 'Alamat wajib diisi.',
            'email.required'              => 'Email wajib diisi.',
            'email.email'                 => 'Alamat email tidak valid, gunakan format email yang benar.',
            'nik.required'                => 'NIK wajib diisi.',
            'nik.numeric'                 => 'NIK harus berupa angka.',
            'no_telepon.required'         => 'Nomor Telepon wajib diisi.',
            'no_telepon.numeric'          => 'Nomor Telepon harus berupa angka.',
            'nama_suami.required'         => 'Nama Suami wajib diisi.',
            'umur_suami.required'         => 'Umur Suami wajib diisi.',
            'umur_suami.integer'          => 'Umur Suami harus berupa angka.',
            'password.required'           => 'Password harus diisi.',
            'password.min'                => 'Password harus terdiri dari minimal 8 karakter.',
            'no_jkn_faskes_tk_1.required' => 'Nomor JKN Faskes TK 1 wajib diisi.',
            'no_jkn_rujukan.required'     => 'Nomor JKN Rujukan wajib diisi.',
            'gol_darah.required'          => 'Golongan darah wajib diisi.',
            'pekerjaan.required'          => 'Pekerjaan wajib diisi.',
            'user_id.required'            => 'User yang dapat mengakses data wajib dipilih.',
            'user_id.exists'              => 'User yang dipilih tidak valid.',
        ]);

        DataIbuHamil::create($request->all());

        toast('Data Berhasil Ditambahkan','success');
        return redirect()->route('home');
    }

    public function edit($id)
    {
        $data_ibu_hamils = DataIbuHamil::findOrFail($id);

        $users = User::all();

        return view('data-ibu-hamil/edit-data-ibu-hamil', compact('data_ibu_hamils', 'users'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'nama_ibu'           => 'required',
            'umur_ibu'           => 'required|integer',
            'alamat'             => 'required',
            'email'              => 'required|email',
            'nik'                => 'required|numeric',
            'no_telepon'         => 'required|numeric',
            'nama_suami'         => 'required',
            'umur_suami'         => 'required|integer',
            'no_jkn_faskes_tk_1' => 'required',
            'no_jkn_rujukan'     => 'required',
            'gol_darah'          => 'required',
            'pekerjaan'          => 'required',
            'user_id'            => 'required|exists:users,id',
        ], [
            'nama_ibu.required'           => 'Nama Ibu wajib diisi.',
            'umur_ibu.required'           => 'Umur Ibu wajib diisi.',
            'umur_ibu.integer'            => 'Umur Ibu harus berupa angka.',
            'alamat.required'             => 'Alamat wajib diisi.',
            'email.required'              => 'Email wajib diisi.',
            'email.email'                 => 'Alamat email tidak valid, gunakan format email yang benar.',
            'nik.required'                => 'NIK wajib diisi.',
            'nik.numeric'                 => 'NIK harus berupa angka.',
            'no_telepon.required'         => 'Nomor Telepon wajib diisi.',
            'no_telepon.numeric'          => 'Nomor Telepon harus berupa angka.',
            'nama_suami.required'         => 'Nama Suami wajib diisi.',
            'umur_suami.required'         => 'Umur Suami wajib diisi.',
            'umur_suami.integer'          => 'Umur Suami harus berupa angka.',
            'no_jkn_faskes_tk_1.required' => 'required',
            'no_jkn_rujukan.required'     => 'required',
            'gol_darah.required'          => 'required',
            'pekerjaan.required'          => 'required',
            'user_id.required'            => 'Puskesmas wajib dipilih.',
            'user_id.exists'              => 'Puskesmas yang dipilih tidak valid.',
        ]);

        $data_ibu_hamils                     = DataIbuHamil::findOrFail($id);
        $data_ibu_hamils->nama_ibu           = $request->nama_ibu;
        $data_ibu_hamils->umur_ibu           = $request->umur_ibu;
        $data_ibu_hamils->alamat             = $request->alamat;
        $data_ibu_hamils->email              = $request->email;
        $data_ibu_hamils->nik                = $request->nik;
        $data_ibu_hamils->no_telepon         = $request->no_telepon;
        $data_ibu_hamils->nama_suami         = $request->nama_suami;
        $data_ibu_hamils->umur_suami         = $request->umur_suami;
        $data_ibu_hamils->no_jkn_faskes_tk_1 = $request->no_jkn_faskes_tk_1;
        $data_ibu_hamils->no_jkn_rujukan     = $request->no_jkn_rujukan;
        $data_ibu_hamils->gol_darah          = $request->gol_darah;
        $data_ibu_hamils->pekerjaan          = $request->pekerjaan;
        $data_ibu_hamils->user_id            = $request->user_id;
        $data_ibu_hamils->save();

        toast('Data Berhasil Diubah','success');
        return redirect()->route('data-ibu-hamil.detail', ['id' => $data_ibu_hamils->id]);
    }

    public function delete($id)
    {
        $data_ibu_hamils = DataIbuHamil::find($id);
        $data_ibu_hamils->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route('home'));
    }

    public function download()
    {
        $data_ibu_hamils = DataIbuHamil::all();

        $csvData = $this->generateCSV($data_ibu_hamils);

        $headers = array(
            'Content-Type' => 'text/csv',
            'Content-Disposition' => 'attachment; filename=data_ibu_hamil_mibu.csv',
        );

        return Response::make($csvData, 200, $headers);
    }

    private function generateCSV($data)
    {
        $csv = '';
    
        // Header
        $csv .= "Data Ibu Hamil - MIBU \n\n";
        $csv .= "No,Nama Ibu,Umur Ibu,Alamat,Email,NIK,Nomor Telepon,Nama Suami,Umur Suami,"
              . "No JKN Faskes TK 1,No JKN Rujukan,Gol Darah,Pekerjaan\n";
    
        $counter = 1;
    
        foreach ($data as $row) {
            // Format fields with units where applicable
            $umur_ibu = $row->umur_ibu . " Tahun";
            $umur_suami = $row->umur_suami . " Tahun";
    
            // Add quotes to each value to handle commas and special characters within fields
            $csv .= "{$counter},\"{$row->nama_ibu}\",\"{$umur_ibu}\",\"{$row->alamat}\",\"{$row->email}\",\"{$row->nik}\",\"{$row->no_telepon}\","
                  . "\"{$row->nama_suami}\",\"{$umur_suami}\","
                  . "\"{$row->no_jkn_faskes_tk_1}\",\"{$row->no_jkn_rujukan}\",\"{$row->gol_darah}\",\"{$row->pekerjaan}\"\n";
    
            $counter++;
        }
    
        return $csv;
    }
    

    
    public function uploadHpl(Request $request)
    {
        $request->validate([
            'id' => 'required|exists:data_ibu_hamils,id',
            'tanggal_hpl' => 'required|date',
        ]);

        $id = $request->input('id');
        $tanggalHpl = $request->input('tanggal_hpl');

        $dataIbuHamil = DataIbuHamil::find($id);
        $dataIbuHamil->tanggal_hpl = $tanggalHpl;
        $dataIbuHamil->save();

        toast('Tanggal HPL Berhasil Diupload', 'success');
        return redirect()->route('home');
    }


}
