<?php

namespace App\Http\Controllers;

use App\Models\DataArtikel;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Response;
use Illuminate\Support\Facades\File;

class DataArtikelController extends Controller
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

        $query = DataArtikel::where('judul', 'like', "%$search%");

        switch ($sort) {
            case 'oldest':
                $query->orderBy('created_at', 'asc');
                break;
            case 'a-z':
                $query->orderBy('judul', 'asc');
                break;
            case 'z-a':
                $query->orderBy('judul', 'desc');
                break;
            default:
                $query->orderBy('created_at', 'desc');
        }

        $data_artikels = $query->paginate($perPage);
        $currentPage = $data_artikels->currentPage();
        
        return view('data-artikel/data-artikel', compact('data_artikels', 'currentPage', 'sort'));
    }


    public function create()
    {
        $data_artikels = DataArtikel::all();

        return view('data-artikel/create-data-artikel', compact('data_artikels'));
    }

    public function store(Request $request)
    {
        $request->validate([
            'tanggal'              => 'required',
            'judul'                => 'required',
            'isi'                  => 'required',
            'author'               => 'required',
            'image'                => 'required|mimes:jpeg,png,jpg,gif|max:10240',
        ], [
            'tanggal.required'              => 'Pilih tanggal',
            'judul.required'                => 'Isi Judul',
            'isi.required'                  => 'Masukkkan Isi artikel',
            'author.required'               => 'Masukkan nama author',
            'image.required'                => 'Upload foto artikel',
            'image.mimes'                   => 'Format gambar harus jpeg, png, jpg, atau gif',
            'image.max'                     => 'Ukuran gambar maksimal 10MB',
        ]);

        $data = $request->all();

        if ($request->hasFile('image')) {
            $image = $request->file('image');
            $imageName = time() . '.' . $image->extension();

            // Store the image in the 'public/foto_artikel' directory
            $imagePath = $image->storeAs('public/foto_artikel', $imageName);

            // Generate the full URL for the image
            $data['foto'] = asset('public/foto_artikel/' . $imageName);
        }

        DataArtikel::create($data);

        toast('Data Berhasil Ditambahkan', 'success');
        return redirect()->route('data-artikel.index');
    }



    public function edit($id)
    {
        $data_artikels = DataArtikel::find($id);

        return view('data-artikel/edit-data-artikel', compact('data_artikels'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'tanggal'              => 'required',
            'judul'                => 'required',
            'isi'                  => 'required',
            'author'               => 'required',
        ], [
            'tanggal.required'              => 'Pilih tanggal',
            'judul.required'                => 'Isi Judul',
            'isi.required'                  => 'Masukkkan Isi artikel',
            'author.required'               => 'Masukkan nama author',
            
        ]);
        
        $data_artikels                       = DataArtikel::find($id);
        $data_artikels->tanggal              = $request->tanggal;
        $data_artikels->judul                = $request->judul;
        $data_artikels->isi                  = $request->isi;
        $data_artikels->author               = $request->author;
        $data_artikels->save();

        toast('Data Berhasil Diubah','success');
        return redirect()->route('data-artikel.index');
    }

    public function delete($id)
    {
        $data_artikels = DataArtikel::find($id);
        $data_artikels->delete();
        toast('Data Berhasil Dihapus','success');
        return redirect(route('data-artikel.index'));
    }

    public function viewFotoArtikel($id)
    {
        $dataArtikel = DataArtikel::find($id);

        if ($dataArtikel && $dataArtikel->foto) {
            // Extract the file name from the URL
            $fileName = basename(parse_url($dataArtikel->foto, PHP_URL_PATH));
            $filePath = public_path('foto_artikel/' . $fileName);
            
            if (File::exists($filePath)) {
                return response()->file($filePath);
            } else {
                return redirect()->back()->with('error', 'Foto Artikel tidak ditemukan.');
            }
        }

        return redirect()->back()->with('error', 'Data artikel tidak ditemukan.');
    }
}
