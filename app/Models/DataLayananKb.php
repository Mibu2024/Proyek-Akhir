<?php

namespace App\Models;

use App\Models\DataIbuHamil;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DataLayananKb extends Model
{
    use HasFactory;

    protected $table = 'data_layanan_kbs';
    protected $primaryKey = 'id';
    
    protected $fillable =  [
        'id',
        'tanggal_praktik',
        'nama_ibu',
        'tekanan_darah',
        'berat_badan',
        'jenis_kb',
        'merk_kb',
        'tanggal_kembali',
        'id_ibu',
        'keluhan'
    ];

    public function ibuHamil()
    {
        return $this->belongsTo(DataIbuHamil::class, 'id_ibu');
    }
}
