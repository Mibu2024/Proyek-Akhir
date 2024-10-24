<?php

namespace App\Models;

use App\Models\DataIbuHamil;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class DataKesehatan extends Model
{
    use HasFactory;

    protected $table = 'data_kesehatans';
    
    protected $fillable =  [
        'tanggal',
        'nama_ibu',
        'id_ibu',
        'id_kehamilan',
        'keluhan',
        'tekanan_darah',
        'berat_badan',
        'umur_kehamilan',
        'tinggi_fundus',
        'letak_janin',
        'denyut_jantung_janin',
        'hasil_lab',
        'tindakan',
        'kaki_bengkak',
        'nasihat',
        'foto_usg',
        'nama_pemeriksa',
        'tanggal_hpl',
        'tinggi_badan',
        'lingkar_perut',
        'lingkar_lengan_atas'
    ];

    public function ibuHamil()
    {
        return $this->belongsTo(DataIbuHamil::class, 'id_ibu');
    }
}
