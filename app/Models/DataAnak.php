<?php

namespace App\Models;

use App\Models\DataIbuHamil;
use App\Models\DataImunisasi;
use App\Models\HistoryPemeriksaanAnak;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class DataAnak extends Model
{
    use HasFactory;

    protected $table = 'data_anaks';
    protected $primaryKey = 'id';
    
    protected $fillable =  [
        'id',
        'tanggal',
        'nama_ibu',
        'id_ibu',
        'nama_anak',
        'tanggal_lahir',
        'umur',
        'berat_badan',
        'tinggi_badan',
        'lingkar_kepala',
        'hepatitis_b',
        'tanggal_imunisasi_hepatitis_b',
        'bcg',
        'tanggal_imunisasi_bcg',
        'polio_tetes_1',
        'tanggal_imunisasi_polio_tetes_1',
        'dpt_hb_hib_1',
        'tanggal_imunisasi_dpt_hb_hib_1',
        'polio_tetes_2',
        'tanggal_imunisasi_polio_tetes_2',
        'rota_virus_1',
        'tanggal_imunisasi_rota_virus_1',
        'pcv_1',
        'tanggal_imunisasi_pcv_1',
        'dpt_hb_hib_2',
        'tanggal_imunisasi_dpt_hb_hib_2',
        'polio_tetes_3',
        'tanggal_imunisasi_polio_tetes_3',
        'rota_virus_2',
        'tanggal_imunisasi_rota_virus_2',
        'pcv_2',
        'tanggal_imunisasi_pcv_2',
        'dpt_hb_hib_3',
        'tanggal_imunisasi_dpt_hb_hib_3',
        'polio_tetes_4',
        'tanggal_imunisasi_polio_tetes_4',
        'polio_suntik_1',
        'tanggal_imunisasi_polio_suntik_1',
        'rota_virus_3',
        'tanggal_imunisasi_rota_virus_3',
        'campak_rubella',
        'tanggal_imunisasi_campak_rubella',
        'polio_suntik_2',
        'tanggal_imunisasi_polio_suntik_2',
        'japanese_encephalitis',
        'tanggal_imunisasi_japanese_encephalitis',
        'pcv_3',
        'tanggal_imunisasi_pcv_3',
        'dpt_hb_hib_lanjutan',
        'tanggal_imunisasi_dpt_hb_hib_lanjutan',
        'campak_rubella_lanjutan',
        'tanggal_imunisasi_campak_rubella_lanjutan',
    ];

    public function imunisasi()
    {
        return $this->hasMany(DataImunisasi::class, 'nama_anak');
    }

    public function ibuHamil()
    {
        return $this->belongsTo(DataIbuHamil::class, 'id_ibu');
    }

    public function historyPemeriksaan()
    {
        return $this->hasMany(HistoryPemeriksaanAnak::class, 'id_anak');
    }
}
