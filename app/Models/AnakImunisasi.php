<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class AnakImunisasi extends Model
{
    use HasFactory;

    protected $table = 'anak_imunisasis';
    protected $primaryKey = 'id';

    protected $fillable = [
        'id',
        'tanggal',
        'nama_anak',
        'id_anak',
        'id_jenis',
        'merek',
        'nama_pemeriksa',
    ];

    // Relasi ke model Anak
    public function anak()
    {
        return $this->belongsTo(DataAnak::class, 'id_anak');
    }

    // Relasi ke model JenisImunisasi
    public function jenisImunisasi()
    {
        return $this->belongsTo(JenisImunisasi::class, 'id_jenis');
    }
}
