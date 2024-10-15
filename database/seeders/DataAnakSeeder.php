<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Carbon\Carbon;

class DataAnakSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('data_anaks')->insert([
            [
                'tanggal'        => '2024-01-01',
                'nama_ibu'       => 'Ibu tes',
                'id_ibu'         => 1,
                'nama_anak'      => 'Rizky Pratama',
                'tanggal_lahir'  => '2024-01-01',
                'umur'           => 6,
                'berat_badan'    => 7,
                'tinggi_badan'   => 68,
                'lingkar_kepala' => 42,
            ],
        ]);
    }
}
