<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DataNifas extends Model
{
    use HasFactory;

    protected $table = 'data_nifas';
    
    protected $fillable =  [
        'tanggal',
        'nama',
        'kunjungan_nifas',
        'hasil_periksa_payudara',
        'hasil_periksa_pendarahan',
        'hasil_periksa_jalan_lahir',
        'vitamin_a',
        'masalah',
        'tindakan',
    ];
}
