<?php

namespace App\Models;

use App\Models\DataAnak;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class DataImunisasi extends Model
{
    use HasFactory;

    protected $table = 'data_imunisasis';
    
    protected $fillable =  [
        'tanggal',
        'nama_anak',
        'id_anak',
        'nama_pemeriksa',
        'hepatitis_b',
        'bcg',
        'polio_tetes_1',
        'dpt_hb_hib_1',
        'polio_tetes_2',
        'rota_virus_1',
        'pcv_1',
        'dpt_hb_hib_2',
        'polio_tetes_3',
        'rota_virus_2',
        'pcv_2',
        'dpt_hb_hib_3',
        'polio_tetes_4',
        'polio_suntik_1',
        'rota_virus_3',
        'campak_rubella',
        'polio_suntik_2',
        'japanese_encephalitis',
        'pcv_3',
        'dpt_hb_hib_lanjutan',
        'campak_rubella_lanjutan',
    ];

    public function anak()
    {
        return $this->belongsTo(DataAnak::class, 'nama_anak');
    }
}
