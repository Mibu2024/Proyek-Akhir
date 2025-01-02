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
        Schema::create('anak_imunisasis', function (Blueprint $table) {
            $table->id();
            $table->date('tanggal');
            $table->foreignId('id_anak')->constrained('data_anaks')->onDelete('cascade');
            $table->foreignId('id_jenis')->constrained('jenis_imunisasis')->onDelete('cascade');
            $table->string('merek');
            $table->string('nama_pemeriksa');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('anak_imunisasis');
    }
};
