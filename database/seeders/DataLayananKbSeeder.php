<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class DataLayananKbSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // Data layanan KB
        DB::table('data_layanan_kbs')->insert([
            [
                'tanggal_praktik' => '2024-01-01',
                'nama_ibu'        => 'Ibu tes',
                'tekanan_darah'   => '120',
                'berat_badan'     => 60,
                'jenis_kb'        => 'KB Suntik',
                'tanggal_kembali' => '2024-02-01',
                'id_ibu'          => 1,
                'keluhan'         => 'Tidak ada keluhan',
            ],
        ]);
    }
}
