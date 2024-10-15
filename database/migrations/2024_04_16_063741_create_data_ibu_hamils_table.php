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
        Schema::create('data_ibu_hamils', function (Blueprint $table) {
            $table->id();
            $table->string('nama_ibu');
            $table->integer('umur_ibu');
            $table->string('alamat');
            $table->string('nik');
            $table->string('no_telepon');
            $table->integer('kehamilan_ke');
            $table->string('nama_suami');
            $table->integer('umur_suami');
            $table->string('no_jkn_faskes_tk_1');
            $table->string('no_jkn_rujukan');
            $table->string('gol_darah');
            $table->string('pekerjaan');
            $table->string('tanggal_hpl')->nullable();;
            $table->string('profile_photo')->nullable();
            $table->string('password');
            $table->string('email')->unique();
            $table->timestamp('email_verified_at')->nullable();
            $table->foreignId('user_id')->constrained('users')->onDelete('cascade');
            $table->rememberToken();
            $table->timestamps();
            });

    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('data_ibu_hamils');
    }
};
