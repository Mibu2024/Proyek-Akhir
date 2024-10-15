<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class DataIbuHamilSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('data_ibu_hamils')->insert([
            'nama_ibu'           => 'Ibu tes',
            'umur_ibu'           => 28,
            'alamat'             => 'Jl. Merdeka No. 1, Jakarta',
            'email'              => 'ibutes@mail.com',
            'nik'                => '1234567890123456',
            'no_telepon'         => '081234567890',
            'kehamilan_ke'       => 1,
            'nama_suami'         => 'Budi Setiawan',
            'umur_suami'         => 30,
            'password'           => bcrypt('password'),             // Password yang di-hash
            'profile_photo'      => 'default.jpg',                  // Atur nama file foto profil jika ada
            'no_jkn_faskes_tk_1' => '123456789',
            'no_jkn_rujukan'     => '987654321',
            'gol_darah'          => 'A',
            'pekerjaan'          => 'Karyawan',
            'tanggal_hpl'        => '2024-11-01',                   // Tanggal HPL
            'user_id'            => 1,                              // Ganti dengan ID user yang relevan
        ]);
    }
}
