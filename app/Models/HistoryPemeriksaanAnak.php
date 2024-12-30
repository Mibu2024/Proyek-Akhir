<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class HistoryPemeriksaanAnak extends Model
{
    use HasFactory;

    protected $table = 'history_pemeriksaan_anak';

    // Kolom yang dapat diisi
    protected $fillable = [
        'id',
        'id_anak',
        'tgl_pemeriksaan',
        'berat_badan',
        'tinggi_badan',
    ];
    
    public function anak()
{
    return $this->belongsTo(DataAnak::class, 'id_anak');
}

}
