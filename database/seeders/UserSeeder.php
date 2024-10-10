<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;

class UserSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert([
            [
                'id' => 1,
                'name' => 'Admin',
                'email' => 'admin@mail.com',
                'password' => Hash::make('password'),
                'alamat' => 'Bandung',
                'no_telepon' => '911',
                'no_str' => 'str911',
                'nik' => 'nik911',
            ],
            // Tambahkan data lain jika diperlukan
        ]);
    }
}
