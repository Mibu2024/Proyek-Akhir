<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        $this->call([
            UserSeeder::class,
            DataIbuHamilSeeder::class,
            DataKesehatanSeeder::class,
            DataLayananKbSeeder::class,
            DataNifasSeeder::class,
            DataAnakSeeder::class,
            DataImunisasiSeeder::class,
            // Tambahkan seeder lain jika ada
        ]);
    }
}
