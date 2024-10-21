<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Carbon\Carbon;

class DataNifasSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('data_nifas')->insert([
            [
                'tanggal'                   => '2024-01-01',
                'nama_ibu'                  => 'Ibu tes',
                'id_ibu'                    => 1,
                'kunjungan_nifas'           => 'Kunjungan pertama',
                'hasil_periksa_payudara'    => 'Normal',
                'hasil_periksa_pendarahan'  => 'Tidak ada pendarahan',
                'hasil_periksa_jalan_lahir' => 'Tidak ada masalah',
                'vitamin_a'                 => 'Diberikan',
                'masalah'                   => 'Tidak ada',
                'tindakan'                  => 'Tidak ada tindakan khusus',
            ],
        ]);
    }
}
