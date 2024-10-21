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
        Schema::create('data_kehamilans', function (Blueprint $table) {
            $table->id();
            $table->bigInteger('id_ibu');
            $table->date('tanggal_kehamilan');
            $table->date('tanggal_hpl');
            $table->rememberToken();
            $table->timestamps();
            });

    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('data_kehamilans');
    }
};
