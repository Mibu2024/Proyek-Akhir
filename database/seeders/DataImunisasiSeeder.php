<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Carbon\Carbon;

class DataImunisasiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('data_imunisasis')->insert([
            'tanggal' => Carbon::now()->format('Y-m-d'),
            'nama_anak' => 'Rizky Pratama',
            'id_anak' => 1,
            'nama_pemeriksa' => 'Dr. Susi Amalia',
            
            // Imunisasi dan tanggalnya
            'hepatitis_b' => 'Sudah',
            'tanggal_imunisasi_hepatitis_b' => Carbon::now()->subMonths(1)->format('Y-m-d'),

            'bcg' => 'Sudah',
            'tanggal_imunisasi_bcg' => Carbon::now()->subMonths(2)->format('Y-m-d'),

            'polio_tetes_1' => 'Sudah',
            'tanggal_imunisasi_polio_tetes_1' => Carbon::now()->subMonths(3)->format('Y-m-d'),

            'dpt_hb_hib_1' => 'Sudah',
            'tanggal_imunisasi_dpt_hb_hib_1' => Carbon::now()->subMonths(4)->format('Y-m-d'),

            'polio_tetes_2' => 'Sudah',
            'tanggal_imunisasi_polio_tetes_2' => Carbon::now()->subMonths(5)->format('Y-m-d'),

            'rota_virus_1' => 'Belum',
            'tanggal_imunisasi_rota_virus_1' => null,

            'pcv_1' => 'Sudah',
            'tanggal_imunisasi_pcv_1' => Carbon::now()->subMonths(6)->format('Y-m-d'),

            'dpt_hb_hib_2' => 'Sudah',
            'tanggal_imunisasi_dpt_hb_hib_2' => Carbon::now()->subMonths(7)->format('Y-m-d'),

            'polio_tetes_3' => 'Belum',
            'tanggal_imunisasi_polio_tetes_3' => null,

            'rota_virus_2' => 'Sudah',
            'tanggal_imunisasi_rota_virus_2' => Carbon::now()->subMonths(8)->format('Y-m-d'),

            'pcv_2' => 'Sudah',
            'tanggal_imunisasi_pcv_2' => Carbon::now()->subMonths(9)->format('Y-m-d'),

            'dpt_hb_hib_3' => 'Belum',
            'tanggal_imunisasi_dpt_hb_hib_3' => null,

            'polio_tetes_4' => 'Sudah',
            'tanggal_imunisasi_polio_tetes_4' => Carbon::now()->subMonths(10)->format('Y-m-d'),

            'polio_suntik_1' => 'Belum',
            'tanggal_imunisasi_polio_suntik_1' => null,

            'rota_virus_3' => 'Sudah',
            'tanggal_imunisasi_rota_virus_3' => Carbon::now()->subMonths(11)->format('Y-m-d'),

            'campak_rubella' => 'Sudah',
            'tanggal_imunisasi_campak_rubella' => Carbon::now()->subMonths(12)->format('Y-m-d'),

            'polio_suntik_2' => 'Belum',
            'tanggal_imunisasi_polio_suntik_2' => null,

            'japanese_encephalitis' => 'Sudah',
            'tanggal_imunisasi_japanese_encephalitis' => Carbon::now()->subMonths(13)->format('Y-m-d'),

            'pcv_3' => 'Belum',
            'tanggal_imunisasi_pcv_3' => null,

            'dpt_hb_hib_lanjutan' => 'Sudah',
            'tanggal_imunisasi_dpt_hb_hib_lanjutan' => Carbon::now()->subMonths(14)->format('Y-m-d'),

            'campak_rubella_lanjutan' => 'Belum',
            'tanggal_imunisasi_campak_rubella_lanjutan' => null,
        ]);
    }
}
