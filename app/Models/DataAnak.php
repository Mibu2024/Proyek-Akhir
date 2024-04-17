<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DataAnak extends Model
{
    use HasFactory;

    protected $table = 'data_anaks';
    
    protected $fillable =  [
        'tanggal',
        'nama_ibu',
        'nama_anak',
        'tanggal_lahir',
        'umur',
        'berat_badan',
    ];
}
