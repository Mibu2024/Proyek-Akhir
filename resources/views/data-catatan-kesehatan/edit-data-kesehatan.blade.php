@extends('layouts.app')

@section('content')

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="{{ asset('data-ibu-hamil/detail-page/detail-ibu.css') }}"> 
    <style>
        .banner-container {
            position: relative;
            max-width: 100%;
            height: 100px;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
        }

        .detail-ibu-container {
            position: relative;
            max-width: 100%;
            height: relative;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 40px;
            padding: 10px;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
        }

        .banner-background {
            background: linear-gradient(to right, #34B28D, #0399AF, #2AAD94, #7DD957); /* Change colors as needed */
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .banner-text {
            text-align: center;
            color: white;
        }

        .banner-text h1 {
            font-size: 2rem;
            font-weight: bold;
        }

        .breadcrumb {
            background-color: transparent;
        }

        .breadcrumb .breadcrumb-item a {
            font-weight: bold;
            color: #1F2024;
            font-size: 24px;
            opacity: 0.7;
        }

        .breadcrumb-item.active {
            font-weight: bolder;
            color: #1F2024;
            font-size: 24px;
            opacity: 0.9;
        }

        hr {
            height: 20px;
        }

        .col-sm-3 {
        margin-top: 20px;
        }

        .col-sm-3 h1{
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="content d-flex flex-column flex-column-fluid" id="kt_content">
        <div class="container">
            <div class="card card-custom">
                <div class="card-body">

                    <!-- Breadcrumbs -->
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="{{ route('home') }}">Data Ibu Hamil</a></li>
                            <li class="breadcrumb-item"><a href="{{ route('data-ibu-hamil.detail', $data_ibu_hamils->id) }}">Detail Ibu Hamil</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Create Data Kehamilan</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- form section -->
                    <form action="{{ route('data-kesehatan.update', $data_kesehatans->id) }}" method="POST">
                    @csrf
                    @method('PUT')
                    
                    <input type="hidden" name="id_ibu" value="{{ $data_ibu_hamils -> id }}">
    
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for="tanggal"><strong>Tanggal Periksa</strong></label>
                                    <input type="date" name="tanggal" id="tanggal" class="form-control form-control-lg  @error('tanggal') is-invalid @enderror" value="{{ $data_kesehatans->tanggal }}"
                                        placeholder="Pilih Tanggal Periksa">
                                    @error('tanggal')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Keluhan yang Dialami</strong></label>
                                    <input type="text" name="keluhan" id="keluhan" class="form-control form-control-lg @error('keluhan') is-invalid @enderror" value="{{ $data_kesehatans->keluhan }}"
                                        placeholder="Masukkan Keluhan yang Dialami">
                                    @error('keluhan')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Tinggi badan</strong></label>
                                    <input type="text" name="tinggi_badan" id="tinggi_badan" class="form-control form-control-lg @error('tinggi_badan') is-invalid @enderror" value="{{ $data_kesehatans->tinggi_badan }}"
                                        placeholder="Masukkan Tinggi Badan">
                                    @error('tinggi_badan')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Lingkar Perut</strong></label>
                                    <input type="text" name="lingkar_perut" id="lingkar_perut" class="form-control form-control-lg @error('lingkar_perut') is-invalid @enderror" value="{{ $data_kesehatans->lingkar_perut }}"
                                        placeholder="Masukkan Lingkar Perut">
                                    @error('lingkar_perut')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Lingkar Lengan Atas</strong></label>
                                    <input type="text" name="lingkar_lengan_atas" id="lingkar_lengan_atas" class="form-control form-control-lg @error('lingkar_lengan_atas') is-invalid @enderror" value="{{ $data_kesehatans->lingkar_lengan_atas }}"
                                        placeholder="Masukkan Lingkar Lengan Atas">
                                    @error('lingkar_lengan_atas')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Tekanan Darah (<span class="text-success">mmHg</span>)</strong></label>
                                    <input type="text" name="tekanan_darah" id="tekanan_darah" class="form-control form-control-lg @error('tekanan_darah') is-invalid @enderror" value="{{ $data_kesehatans->tekanan_darah }}"
                                        placeholder="Masukkan Tekanan Darah Ibu Hamil (Hanya Angka Saja)">
                                    @error('tekanan_darah')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Berat Badan (<span class="text-success">Kg</span>)</strong></label>
                                    <input type="text" name="berat_badan" id="berat_badan" class="form-control form-control-lg @error('berat_badan') is-invalid @enderror" value="{{ $data_kesehatans->berat_badan }}"
                                        placeholder="Masukkan Berat Badan Ibu Hamil (Hanya Angka Saja)">
                                    @error('berat_badan')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Umur Kehamilan (<span class="text-success">Hari/Minggu/bulan</span>)</strong></label>
                                    <input type="text" name="umur_kehamilan" id="umur_kehamilan" class="form-control form-control-lg @error('umur_kehamilan') is-invalid @enderror" value="{{ $data_kesehatans->umur_kehamilan }}"
                                        placeholder="Masukkan Umur Kehamilan (Contoh: 1 Hari/1 Minggu/1 Bulan)">
                                    @error('umur_kehamilan')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Tinggi Fundus (<span class="text-success">Cm</span>)</strong></label>
                                    <input type="text" name="tinggi_fundus" id="tinggi_fundus" class="form-control form-control-lg @error('tinggi_fundus') is-invalid @enderror" value="{{ $data_kesehatans->tinggi_fundus }}"
                                        placeholder="Masukkan Tinggi Fundus (Hanya Angka Saja)">
                                    @error('tinggi_fundus')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Letak Janin (<span class="text-success">Kep/Su/Li</span>)</strong></label>
                                    <input type="text" name="letak_janin" id="letak_janin" class="form-control form-control-lg @error('letak_janin') is-invalid @enderror" value="{{ $data_kesehatans->letak_janin }}"
                                        placeholder="Masukkan Letak Janin">
                                    @error('letak_janin')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Denyut Jantung Janin (<span class="text-success">BPM</span>)</strong></label>
                                    <input type="text" name="denyut_jantung_janin" id="denyut_jantung_janin" class="form-control form-control-lg @error('denyut_jantung_janin') is-invalid @enderror" value="{{ $data_kesehatans->denyut_jantung_janin }}"
                                        placeholder="Masukkan Denyut Jantung Janin (Hanya Angka Saja)">
                                    @error('denyut_jantung_janin')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Hasil Pemeriksaan Lab</strong></label>
                                    <input type="text" name="hasil_lab" id="hasil_lab" class="form-control form-control-lg @error('hasil_lab') is-invalid @enderror" value="{{ $data_kesehatans->hasil_lab }}"
                                        placeholder="Masukkan Hasil Pemeriksaan Lab">
                                    @error('hasil_lab')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Tindakan yang Dilakukan</strong></label>
                                    <input type="text" name="tindakan" id="tindakan" class="form-control form-control-lg @error('tindakan') is-invalid @enderror" value="{{ $data_kesehatans->tindakan }}"
                                        placeholder="Masukkan Tindakan yang Dilakukan">
                                    @error('tindakan')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Apakah Kaki Bengkak?</strong></label>
                                    <input type="text" name="kaki_bengkak" id="kaki_bengkak" class="form-control form-control-lg @error('kaki_bengkak') is-invalid @enderror" value="{{ $data_kesehatans->kaki_bengkak }}"
                                        placeholder="Kaki Ibu Hamil bengkak atau tidak?">
                                    @error('kaki_bengkak')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Nasihat untuk Ibu Hamil</strong></label>
                                    <input type="text" name="nasihat" id="nasihat" class="form-control form-control-lg @error('nasihat') is-invalid @enderror" value="{{ $data_kesehatans->nasihat }}"
                                        placeholder="Masukkan Nasihat untuk Ibu Hamil">
                                    @error('nasihat')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Nama Pemeriksa</strong></label>
                                    <input type="text" name="nama_pemeriksa" id="nama_pemeriksa" class="form-control form-control-lg @error('nama_pemeriksa') is-invalid @enderror" value="{{ $data_kesehatans->nama_pemeriksa }}"
                                        placeholder="Masukkan Nama Bidan Pemeriksa">
                                    @error('nama_pemeriksa')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="text-right">
                            <a href="{{ route('data-ibu-hamil.detail', $data_ibu_hamils->id) }}" class="btn btn-outline-danger mr-2"
                            role="button">Batal</a>
                            <button type="submit" class="btn btn-success">Simpan</button>
                        </div>

                    </form>


                    </div>
            </div>
        </div>
    </div>
</body>
</html>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush