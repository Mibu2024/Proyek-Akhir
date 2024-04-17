<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DataIbuHamil extends Model
{
    use HasFactory;

    protected $table = 'data_ibu_hamils';
    
    protected $fillable =  [
        'tanggal',
        'nama',
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
