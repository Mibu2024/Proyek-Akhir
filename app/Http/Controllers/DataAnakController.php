<?php

namespace App\Http\Controllers;

use App\Models\DataAnak;
use App\Models\DataIbuHamil;
use App\Models\DataImunisasi;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Response;

class DataAnakController extends Controller
{
    public function index(Request $request)
    {
        $search = $request->input('search');
        $perPage = $request->input('per_page', 5);
        $sort = $request->input('sort', 'Paling Baru');

        $userId = auth()->user()->id;
        $ibuHamilIds = DataIbuHamil::where('user_id', $userId)->pluck('id');

        $query = DataAnak::whereIn('id_ibu', $ibuHamilIds)
            ->where(function ($query) use ($search) {
                $query->where('nama_ibu', 'like', "%$search%")
                    ->orWhere('nama_anak', 'like', "%$search%");
            });

        switch ($sort) {
            case 'oldest':
                $query->orderBy('created_at', 'asc');
                break;
            case 'a-z':
                $query->orderBy('nama_anak', 'asc');
                break;
            case 'z-a':
                $query->orderBy('nama_anak', 'desc');
                break;
            default:
                $query->orderBy('created_at', 'desc');
        }

        $data_anaks = $query->paginate($perPage);
        $currentPage = $data_anaks->currentPage();
        
        return view('data-catatan-anak/data-anak', compact('data_anaks', 'currentPage', 'sort'));
    }


    public function detail($id)
    {
        // Fetch the specific 'DataAnak' record based on the ID
        $anakRecords = DataAnak::find($id);

        // Check if the record exists
        if (!$anakRecords) {
            return redirect()->route('data-ibu-hamil.index')->with('error', 'Data Anak not found.');
        }

        // Fetch the associated Ibu Hamil record using 'id_ibu'
        $ibuHamil = DataIbuHamil::find($anakRecords->id_ibu);

        $imunisasiRecords = DataImunisasi::find($anakRecords->id_anak);

        // Pass the data to the view
        return view('data-ibu-hamil/detail-page/detail-anak', compact('anakRecords', 'ibuHamil', 'imunisasiRecords'));
    }


    public function create($id)
    {
        $data_ibu_hamils = DataIbuHamil::find($id);
        return view('data-catatan-anak/create-data-anak', compact('data_ibu_hamils'));
    }

    public function store(Request $request)
    {
        $request->validate([
            'tanggal'           => 'required',
            'id_ibu'            => 'required|exists:data_ibu_hamils,id',
            'nama_anak'         => 'required',
            'tanggal_lahir'     => 'required',
            'umur'              => 'required',
            'berat_badan'       => 'required|integer',
            'tinggi_badan'      => 'required',
            'lingkar_kepala'    => 'required',
        ], [
            'tanggal.required'          => 'Tanggal Periksa wajib diisi.',
            'nama_anak.required'        => 'Nama Anak wajib diisi.',
            'tanggal_lahir.required'    => 'Tanggal Lahir wajib diisi.',
            'umur.required'             => 'Umur wajib diisi.',
            'berat_badan.required'      => 'Berat badan wajib diisi.',
            'berat_badan.integer'       => 'Berat badan harus berupa angka.',
            'tinggi_badan.required'     => 'Tinggi badan wajib diisi',
            'lingkar_kepala.required'   => 'Lingkar kepala wajib diisi',
        ]);

        $data = $request->all();
        $data['nama_ibu'] = DataIbuHamil::find($request->id_ibu)->nama_ibu;
        

        DataAnak::create($data);
        toast('Data Berhasil Ditambahkan','success');
        return redirect()->route('data-ibu-hamil.detail', ['id' => $request->id_ibu]);
    }

    public function edit($id, $id_ibu)
    {
        $data_ibu_hamils = DataIbuHamil::find($id_ibu);
        $data_anaks = DataAnak::find($id);
        return view('data-catatan-anak/edit-data-anak', compact('data_anaks', 'data_ibu_hamils'));
    }

    public function updateImunisasi(Request $request, $id)
    {
        // Validate incoming request data
        $request->validate([
            'imunisasi_column' => 'required|string', // Expecting a single column name
            'tanggal_imunisasi' => 'required|date', // Expecting a single date
        ], [
            'imunisasi_column.required' => 'Imunisasi column is required.',
            'tanggal_imunisasi.required' => 'Tanggal field is required.',
        ]);

        // Fetch the DataAnak record
        $dataAnak = DataAnak::find($id);

        // Check if the record exists
        if (!$dataAnak) {
            return redirect()->back()->with('error', 'Data Anak not found.');
        }

        // Get the immunization column name from the request
        $imunisasiColumn = $request->imunisasi_column;

        // Check if the field exists in the fillable array
        if (in_array($imunisasiColumn, $dataAnak->getFillable())) {
            // Update the imunisasi name to "Sudah"
            $dataAnak->{$imunisasiColumn} = "Sudah";

            // Construct the date field name
            $tanggalField = 'tanggal_imunisasi_' . $imunisasiColumn;

            // Update the corresponding date
            $dataAnak->{$tanggalField} = $request->tanggal_imunisasi;

            // Save the updated record
            $dataAnak->save();

            toast('Imunisasi data successfully updated', 'success');
        } else {
            return redirect()->back()->with('error', 'Invalid immunization name.');
        }

        return redirect()->route('data-anak.detail', ['id' => $dataAnak->id]);
    }

    


    public function update(Request $request, $id)
    {
        $request->validate([
            'tanggal'        => 'required',
            'id_ibu'         => 'required|exists:data_ibu_hamils,id',
            'nama_anak'      => 'required',
            'tanggal_lahir'  => 'required',
            'umur'           => 'required',
            'berat_badan'    => 'required|integer',
            'tinggi_badan'   => 'required',
            'lingkar_kepala' => 'required',
        ], [
            'tanggal.required'        => 'Tanggal Periksa wajib diisi.',
            'nama_anak.required'      => 'Nama Anak wajib diisi.',
            'tanggal_lahir.required'  => 'Tanggal Lahir wajib diisi.',
            'umur.required'           => 'Umur wajib diisi.',
            'berat_badan.required'    => 'Berat badan wajib diisi.',
            'berat_badan.integer'     => 'Berat badan harus berupa angka.',
            'tinggi_badan.required'   => 'Tinggi badan wajib diisi',
            'lingkar_kepala.required' => 'Lingkar kepala wajib diisi',
        ]);
        
        $data_anaks                 = DataAnak::find($id);
        $data_anaks->tanggal        = $request->tanggal;
        $data_anaks->id_ibu         = $request->id_ibu;
        $data_anaks->nama_anak      = $request->nama_anak;
        $data_anaks->tanggal_lahir  = $request->tanggal_lahir;
        $data_anaks->umur           = $request->umur;
        $data_anaks->berat_badan    = $request->berat_badan;
        $data_anaks->tinggi_badan   = $request->tinggi_badan;
        $data_anaks->lingkar_kepala = $request->lingkar_kepala;
        $data_anaks->save();

    

        toast('Data Berhasil Diubah','success');
        return redirect()->route('data-ibu-hamil.detail', ['id' => $request->id_ibu]);
    }

    public function delete($id)
    {
        $data_anaks = DataAnak::find($id);
        $data_anaks->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route('data-ibu-hamil.detail', ['id' => $data_anaks->id_ibu]));
    }

    public function download()
    {
        $data_anaks = DataAnak::all();

        $csvData = $this->generateCSV($data_anaks);

        $headers = array(
            'Content-Type' => 'text/csv',
            'Content-Disposition' => 'attachment; filename=data_anak_mibu.csv',
        );

        return Response::make($csvData, 200, $headers);
    }

    private function generateCSV($data)
    {
        $csv = '';

        // Header
        $csv .= "Data Anak - MIBU \n\n";
        $csv .= "No,Tanggal Periksa,Nama Ibu,Nama Anak,Tanggal Lahir,Umur,Berat Badan,Tinggi Badan,Lingkar Kepala,"
            . "Hepatitis B,Tanggal Imunisasi Hepatitis B,BCG,Tanggal Imunisasi BCG,Polio Tetes 1,Tanggal Imunisasi Polio Tetes 1,"
            . "DPT-HB-HIB 1,Tanggal Imunisasi DPT-HB-HIB 1,Polio Tetes 2,Tanggal Imunisasi Polio Tetes 2,"
            . "Rota Virus 1,Tanggal Imunisasi Rota Virus 1,PCV 1,Tanggal Imunisasi PCV 1,DPT-HB-HIB 2,"
            . "Tanggal Imunisasi DPT-HB-HIB 2,Polio Tetes 3,Tanggal Imunisasi Polio Tetes 3,Rota Virus 2,"
            . "Tanggal Imunisasi Rota Virus 2,PCV 2,Tanggal Imunisasi PCV 2,DPT-HB-HIB 3,Tanggal Imunisasi DPT-HB-HIB 3,"
            . "Polio Tetes 4,Tanggal Imunisasi Polio Tetes 4,Polio Suntik 1,Tanggal Imunisasi Polio Suntik 1,"
            . "Rota Virus 3,Tanggal Imunisasi Rota Virus 3,Campak Rubella,Tanggal Imunisasi Campak Rubella,"
            . "Polio Suntik 2,Tanggal Imunisasi Polio Suntik 2,Japanese Encephalitis,Tanggal Imunisasi Japanese Encephalitis,"
            . "PCV 3,Tanggal Imunisasi PCV 3,DPT-HB-HIB Lanjutan,Tanggal Imunisasi DPT-HB-HIB Lanjutan,"
            . "Campak Rubella Lanjutan,Tanggal Imunisasi Campak Rubella Lanjutan\n";

        $counter = 1;

        foreach ($data as $row) {
            // Format fields
            $berat_badan = $row->berat_badan . " Kg";
            $tinggi_badan = $row->tinggi_badan . " Cm";
            $lingkar_kepala = $row->lingkar_kepala . " Cm";

            // CSV row
            $csv .= "{$counter},{$row->tanggal},{$row->nama_ibu},{$row->nama_anak},{$row->tanggal_lahir},{$row->umur},"
                . "{$berat_badan},{$tinggi_badan},{$lingkar_kepala},{$row->hepatitis_b},{$row->tanggal_imunisasi_hepatitis_b},"
                . "{$row->bcg},{$row->tanggal_imunisasi_bcg},{$row->polio_tetes_1},{$row->tanggal_imunisasi_polio_tetes_1},"
                . "{$row->dpt_hb_hib_1},{$row->tanggal_imunisasi_dpt_hb_hib_1},{$row->polio_tetes_2},{$row->tanggal_imunisasi_polio_tetes_2},"
                . "{$row->rota_virus_1},{$row->tanggal_imunisasi_rota_virus_1},{$row->pcv_1},{$row->tanggal_imunisasi_pcv_1},"
                . "{$row->dpt_hb_hib_2},{$row->tanggal_imunisasi_dpt_hb_hib_2},{$row->polio_tetes_3},{$row->tanggal_imunisasi_polio_tetes_3},"
                . "{$row->rota_virus_2},{$row->tanggal_imunisasi_rota_virus_2},{$row->pcv_2},{$row->tanggal_imunisasi_pcv_2},"
                . "{$row->dpt_hb_hib_3},{$row->tanggal_imunisasi_dpt_hb_hib_3},{$row->polio_tetes_4},{$row->tanggal_imunisasi_polio_tetes_4},"
                . "{$row->polio_suntik_1},{$row->tanggal_imunisasi_polio_suntik_1},{$row->rota_virus_3},{$row->tanggal_imunisasi_rota_virus_3},"
                . "{$row->campak_rubella},{$row->tanggal_imunisasi_campak_rubella},{$row->polio_suntik_2},{$row->tanggal_imunisasi_polio_suntik_2},"
                . "{$row->japanese_encephalitis},{$row->tanggal_imunisasi_japanese_encephalitis},{$row->pcv_3},{$row->tanggal_imunisasi_pcv_3},"
                . "{$row->dpt_hb_hib_lanjutan},{$row->tanggal_imunisasi_dpt_hb_hib_lanjutan},{$row->campak_rubella_lanjutan},"
                . "{$row->tanggal_imunisasi_campak_rubella_lanjutan}\n";

            $counter++;
        }

        return $csv;
    }


}
