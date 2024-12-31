<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\JenisImunisasi; // Pastikan Anda mengimpor model JenisImunisasi

class JenisImunisasiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $jenisImunisasi = [
            ['jenis_imunisasi' => 'BCG', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Campak Rubella', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Campak Rubella Lanjutan', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'DPT-HB-Hib 1', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'DPT-HB-Hib 2', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'DPT-HB-Hib 3', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'DPT-HB-Hib Lanjutan', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Hepatitis B', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Japanese Encephalitis', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'PCV 1', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'PCV 3', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Polio Suntik 1', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Polio Suntik 2', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Polio Tetes 1', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Polio Tetes 2', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Polio Tetes 3', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Polio Tetes 4', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Rota Virus 1', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Rota Virus 2', 'created_at' => now(), 'updated_at' => now()],
            ['jenis_imunisasi' => 'Rota Virus 3', 'created_at' => now(), 'updated_at' => now()],
        ];

        // Menyisipkan data ke dalam tabel
        foreach ($jenisImunisasi as $jenis) {
            JenisImunisasi::create($jenis);
        }
    }
}
