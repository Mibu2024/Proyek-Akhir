<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class JenisImunisasi extends Model
{
    use HasFactory;

    protected $fillable = ['jenis_imunisasi', 'created_at', 'updated_at'];
}
