<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DataIbuHamil extends Model
{
    use HasFactory;

    protected $table = 'data_ibu_hamils';
    
    protected $fillable =  [
        'nama_ibu',
        'umur_ibu',
        'alamat',
        'email',
        'nik',
        'no_telepon',
        'kehamilan_ke',
        'nama_suami',
        'umur_suami',
    ];
}
