<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('data_imunisasis', function (Blueprint $table) {
            $table->id();
            $table->date('tanggal');
            $table->string('nama_anak');
            $table->bigInteger('id_anak');
            $table->string('nama_pemeriksa');
            
            // Imunisasi dan tanggal terkait
            $table->string('hepatitis_b')->nullable();
            $table->date('tanggal_imunisasi_hepatitis_b')->nullable();

            $table->string('bcg')->nullable();
            $table->date('tanggal_imunisasi_bcg')->nullable();

            $table->string('polio_tetes_1')->nullable();
            $table->date('tanggal_imunisasi_polio_tetes_1')->nullable();

            $table->string('dpt_hb_hib_1')->nullable();
            $table->date('tanggal_imunisasi_dpt_hb_hib_1')->nullable();

            $table->string('polio_tetes_2')->nullable();
            $table->date('tanggal_imunisasi_polio_tetes_2')->nullable();

            $table->string('rota_virus_1')->nullable();
            $table->date('tanggal_imunisasi_rota_virus_1')->nullable();

            $table->string('pcv_1')->nullable();
            $table->date('tanggal_imunisasi_pcv_1')->nullable();

            $table->string('dpt_hb_hib_2')->nullable();
            $table->date('tanggal_imunisasi_dpt_hb_hib_2')->nullable();

            $table->string('polio_tetes_3')->nullable();
            $table->date('tanggal_imunisasi_polio_tetes_3')->nullable();

            $table->string('rota_virus_2')->nullable();
            $table->date('tanggal_imunisasi_rota_virus_2')->nullable();

            $table->string('pcv_2')->nullable();
            $table->date('tanggal_imunisasi_pcv_2')->nullable();

            $table->string('dpt_hb_hib_3')->nullable();
            $table->date('tanggal_imunisasi_dpt_hb_hib_3')->nullable();

            $table->string('polio_tetes_4')->nullable();
            $table->date('tanggal_imunisasi_polio_tetes_4')->nullable();

            $table->string('polio_suntik_1')->nullable();
            $table->date('tanggal_imunisasi_polio_suntik_1')->nullable();

            $table->string('rota_virus_3')->nullable();
            $table->date('tanggal_imunisasi_rota_virus_3')->nullable();

            $table->string('campak_rubella')->nullable();
            $table->date('tanggal_imunisasi_campak_rubella')->nullable();

            $table->string('polio_suntik_2')->nullable();
            $table->date('tanggal_imunisasi_polio_suntik_2')->nullable();

            $table->string('japanese_encephalitis')->nullable();
            $table->date('tanggal_imunisasi_japanese_encephalitis')->nullable();

            $table->string('pcv_3')->nullable();
            $table->date('tanggal_imunisasi_pcv_3')->nullable();

            $table->string('dpt_hb_hib_lanjutan')->nullable();
            $table->date('tanggal_imunisasi_dpt_hb_hib_lanjutan')->nullable();

            $table->string('campak_rubella_lanjutan')->nullable();
            $table->date('tanggal_imunisasi_campak_rubella_lanjutan')->nullable();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('data_imunisasis');
    }
};
