<?php

namespace App\Models;

use App\Models\DataImunisasi;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

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

    public function imunisasi()
    {
        return $this->hasMany(DataImunisasi::class, 'nama_anak');
    }
}
