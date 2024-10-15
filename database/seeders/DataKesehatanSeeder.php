<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class DataKesehatanSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('data_kesehatans')->insert([
            'tanggal'              => '2024-10-01',
            'nama_ibu'             => 'Ibu tes',
            'id_ibu'               => 1,                         // Ganti dengan ID Ibu Hamil yang sesuai
            'keluhan'              => 'Tidak ada keluhan',
            'tekanan_darah'        => '120',
            'berat_badan'          => 60,
            'umur_kehamilan'       => 12,                        // Dalam minggu
            'tinggi_fundus'        => 20,
            'letak_janin'          => 'Head Down',
            'denyut_jantung_janin' => 140,
            'hasil_lab'            => 'Normal',
            'tindakan'             => 'Pemeriksaan Rutin',
            'kaki_bengkak'         => 'Tidak',
            'nasihat'              => 'Jaga pola makan sehat',
            'foto_usg'             => 'usg_ani.jpg',             // Nama file foto USG
            'nama_pemeriksa'       => 'Dr. Budi',
            'tanggal_hpl'          => '2024-11-01',
            'tinggi_badan'         => 160,
            'lingkar_perut'        => 90,
            'lingkar_lengan_atas'  => 25,
        ]);
    }
}
