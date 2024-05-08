<?php

use Illuminate\Support\Facades\Route;
use RealRashid\SweetAlert\Facades\Alert;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('auth.login');
});

Auth::routes();

// route buat halaman data ibu hamil
Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home'); //ini buat halaman index data ibu hamil
Route::get('/create-data-ibu-hamil', [App\Http\Controllers\HomeController::class, 'create'])->name('data-ibu-hamil.create');
Route::post('/store-data-ibu-hamil', [App\Http\Controllers\HomeController::class, 'store'])->name('data-ibu-hamil.store');
Route::get('/data-ibu-hamil/{id}/edit', [App\Http\Controllers\HomeController::class, 'edit'])->name('data-ibu-hamil.edit');
Route::put('/data-ibu-hamil/{id}', [App\Http\Controllers\HomeController::class, 'update'])->name('data-ibu-hamil.update');
Route::delete('/data-ibu-hamil/{id}', [App\Http\Controllers\HomeController::class, 'delete'])->name('data-ibu-hamil.delete');
Route::get('data-ibu-hamil/download', [App\Http\Controllers\HomeController::class, 'download'])->name('data-ibu-hamil.download');

// route buat halaman data kesehatan
Route::get('/data-kesehatan', [App\Http\Controllers\DataKesehatanController::class, 'index'])->name('data-kesehatan.index');
Route::get('/create-data-kesehatan', [App\Http\Controllers\DataKesehatanController::class, 'create'])->name('data-kesehatan.create');
Route::post('/store-data-kesehatan', [App\Http\Controllers\DataKesehatanController::class, 'store'])->name('data-kesehatan.store');
Route::get('/data-kesehatan/{id}/edit', [App\Http\Controllers\DataKesehatanController::class, 'edit'])->name('data-kesehatan.edit');
Route::put('/data-kesehatan/{id}', [App\Http\Controllers\DataKesehatanController::class, 'update'])->name('data-kesehatan.update');
Route::delete('/data-kesehatan/{id}', [App\Http\Controllers\DataKesehatanController::class, 'delete'])->name('data-kesehatan.delete');
Route::get('data-kesehatan/download', [App\Http\Controllers\DataKesehatanController::class, 'download'])->name('data-kesehatan.download');

// route buat halaman data nifas
Route::get('/data-nifas', [App\Http\Controllers\DataNifasController::class, 'index'])->name('data-nifas.index');
Route::get('/create-data-nifas', [App\Http\Controllers\DataNifasController::class, 'create'])->name('data-nifas.create');
Route::post('/store-data-nifas', [App\Http\Controllers\DataNifasController::class, 'store'])->name('data-nifas.store');
Route::get('/data-nifas/{id}/edit', [App\Http\Controllers\DataNifasController::class, 'edit'])->name('data-nifas.edit');
Route::put('/data-nifas/{id}', [App\Http\Controllers\DataNifasController::class, 'update'])->name('data-nifas.update');
Route::delete('/data-nifas/{id}', [App\Http\Controllers\DataNifasController::class, 'delete'])->name('data-nifas.delete');
Route::get('data-nifas/download', [App\Http\Controllers\DataNifasController::class, 'download'])->name('data-nifas.download');

// route buat halaman data anak
Route::get('/data-anak', [App\Http\Controllers\DataAnakController::class, 'index'])->name('data-anak.index');
Route::get('/create-data-anak', [App\Http\Controllers\DataAnakController::class, 'create'])->name('data-anak.create');
Route::post('/store-data-anak', [App\Http\Controllers\DataAnakController::class, 'store'])->name('data-anak.store');
Route::get('/data-anak/{id}/edit', [App\Http\Controllers\DataAnakController::class, 'edit'])->name('data-anak.edit');
Route::put('/data-anak/{id}', [App\Http\Controllers\DataAnakController::class, 'update'])->name('data-anak.update');
Route::delete('/data-anak/{id}', [App\Http\Controllers\DataAnakController::class, 'delete'])->name('data-anak.delete');
Route::get('data-anak/download', [App\Http\Controllers\DataAnakController::class, 'download'])->name('data-anak.download');

// route buat halaman data imunisasi
Route::get('/data-imunisasi', [App\Http\Controllers\DataImunisasiController::class, 'index'])->name('data-imunisasi.index');
Route::get('/create-data-imunisasi', [App\Http\Controllers\DataImunisasiController::class, 'create'])->name('data-imunisasi.create');
Route::post('/store-data-imunisasi', [App\Http\Controllers\DataImunisasiController::class, 'store'])->name('data-imunisasi.store');
Route::get('/data-imunisasi/{id}/edit', [App\Http\Controllers\DataImunisasiController::class, 'edit'])->name('data-imunisasi.edit');
Route::put('/data-imunisasi/{id}', [App\Http\Controllers\DataImunisasiController::class, 'update'])->name('data-imunisasi.update');
Route::delete('/data-imunisasi/{id}', [App\Http\Controllers\DataImunisasiController::class, 'delete'])->name('data-imunisasi.delete');
Route::get('data-imunisasi/download', [App\Http\Controllers\DataImunisasiController::class, 'download'])->name('data-imunisasi.download');


Route::post('/logout', [App\Http\Controllers\Auth\LoginController::class, 'logout'])->name('logout');


