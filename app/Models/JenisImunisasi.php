<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class JenisImunisasi extends Model
{
    use HasFactory;

    protected $table = 'jenis_imunisasis';

    protected $guarded = [];

    public $timestamps = false;

    // Relasi dengan AnakImunisasi
    public function imunisasi()
    {
        return $this->hasMany(AnakImunisasi::class, 'id_jenis');
    }
}
