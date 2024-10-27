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
                    <form action="{{ route('data-nifas.update', $data_nifas->id) }}" method="POST">
                    @csrf
                    @method('PUT')
                    
                    <input type="hidden" name="id_ibu" value="{{ $data_ibu_hamils -> id }}">
    
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for="tanggal"><strong>Tanggal Periksa</strong></label>
                                    <input type="date" name="tanggal" id="tanggal" class="form-control @error('tanggal') is-invalid @enderror" value="{{ $data_nifas->tanggal }}"
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
                                    <label for=""><strong>Kunjungan Nifas Ke</strong></label>
                                    <input type="text" name="kunjungan_nifas" id="kunjungan_nifas" class="form-control @error('kunjungan_nifas') is-invalid @enderror" value="{{ $data_nifas->kunjungan_nifas }}"
                                        placeholder="Masukkan Kunjungan Nifas yang Ke Berapa">
                                    @error('kunjungan_nifas')
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
                                    <label for=""><strong>Hasil Periksa Payudara (<span class="text-success">ASI</span>)</strong></label>
                                    <input type="text" name="hasil_periksa_payudara" id="hasil_periksa_payudara" class="form-control @error('hasil_periksa_payudara') is-invalid @enderror" value="{{ $data_nifas->hasil_periksa_payudara }}"
                                        placeholder="Masukkan Hasil Periksa Payudara">
                                    @error('hasil_periksa_payudara')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Hasil Periksa Pendarahan</strong></label>
                                    <input type="text" name="hasil_periksa_pendarahan" id="hasil_periksa_pendarahan" class="form-control @error('hasil_periksa_pendarahan') is-invalid @enderror" value="{{ $data_nifas->hasil_periksa_pendarahan }}"
                                        placeholder="Masukkan Hasil Periksa Pendarahan">
                                    @error('hasil_periksa_pendarahan')
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
                                    <label for=""><strong>Hasil Periksa Jalan Lahir</strong></label>
                                    <input type="text" name="hasil_periksa_jalan_lahir" id="hasil_periksa_jalan_lahir" class="form-control @error('hasil_periksa_jalan_lahir') is-invalid @enderror" value="{{ $data_nifas->hasil_periksa_jalan_lahir }}"
                                        placeholder="Masukkan Hasil Periksa Jalan Lahir">
                                    @error('hasil_periksa_jalan_lahir')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Apakah Sudah Mendapatkan Vitamin A? (<span class="text-success">Sudah/Belum</span>)</strong></label>
                                    <input type="text" name="vitamin_a" id="vitamin_a" class="form-control @error('vitamin_a') is-invalid @enderror" value="{{ $data_nifas->vitamin_a }}"
                                        placeholder="Apakah Ibu Hamil Sudah Mendapatkan Vitamin A? (Sudah/Belum)">
                                    @error('vitamin_a')
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
                                    <label for=""><strong>Masalah</strong></label>
                                    <input type="text" name="masalah" id="masalah" class="form-control @error('masalah') is-invalid @enderror" value="{{ $data_nifas->masalah }}"
                                        placeholder="Ceritakan Masalah yang dialami (Jika tidak ada, isi dengan Tidak Ada)">
                                    @error('masalah')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Tindakan yang Dilakukan</strong></label>
                                    <input type="text" name="tindakan" id="tindakan" class="form-control @error('tindakan') is-invalid @enderror" value="{{ $data_nifas->tindakan }}"
                                        placeholder="Masukkan Tindakan yang Dilakukan">
                                    @error('tindakan')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="text-right">
                            <a href="{{ route('data-kehamilan.detail', [$data_ibu_hamils->id, $data_nifas->id_kehamilan]) }}" class="btn btn-outline-primary mr-2"
                            role="button">Batal</a>
                            <button type="submit" class="btn btn-primary">Simpan</button>
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