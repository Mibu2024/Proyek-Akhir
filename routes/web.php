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

// route buat halaman data nifas
Route::get('/data-nifas', [App\Http\Controllers\DataNifasController::class, 'index'])->name('data-nifas.index');
Route::get('/create-data-nifas', [App\Http\Controllers\DataNifasController::class, 'create'])->name('data-nifas.create');
Route::post('/store-data-nifas', [App\Http\Controllers\DataNifasController::class, 'store'])->name('data-nifas.store');
Route::get('/data-nifas/{id}/edit', [App\Http\Controllers\DataNifasController::class, 'edit'])->name('data-nifas.edit');
Route::put('/data-nifas/{id}', [App\Http\Controllers\DataNifasController::class, 'update'])->name('data-nifas.update');
Route::delete('/data-nifas/{id}', [App\Http\Controllers\DataNifasController::class, 'delete'])->name('data-nifas.delete');

// route buat halaman data anak
Route::get('/data-anak', [App\Http\Controllers\DataAnakController::class, 'index'])->name('data-anak.index');
Route::get('/create-data-anak', [App\Http\Controllers\DataAnakController::class, 'create'])->name('data-anak.create');
Route::post('/store-data-anak', [App\Http\Controllers\DataAnakController::class, 'store'])->name('data-anak.store');
Route::get('/data-anak/{id}/edit', [App\Http\Controllers\DataAnakController::class, 'edit'])->name('data-anak.edit');
Route::put('/data-anak/{id}', [App\Http\Controllers\DataAnakController::class, 'update'])->name('data-anak.update');
Route::delete('/data-anak/{id}', [App\Http\Controllers\DataAnakController::class, 'delete'])->name('data-anak.delete');

Route::post('/logout', [App\Http\Controllers\Auth\LoginController::class, 'logout'])->name('logout');


