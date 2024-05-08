<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DataKesehatan extends Model
{
    use HasFactory;

    protected $table = 'data_kesehatans';
    
    protected $fillable =  [
        'tanggal',
        'nama_ibu',
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
    ];
}
