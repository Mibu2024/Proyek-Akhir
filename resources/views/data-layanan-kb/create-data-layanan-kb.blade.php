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
                            <li class="breadcrumb-item active" aria-current="page">Create Data Layanan KB</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- form section -->
                    <form action="{{ route('data-layanan-kb.store') }}" method="post">
                    @csrf

                        <input type="hidden" name="id_ibu" value="{{ $data_ibu_hamils -> id }}">

                        <div class="form-kb-container">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for="tanggal"><strong>Tanggal Periksa</strong></label>
                                            <input type="date" name="tanggal_praktik" id="tanggal_praktik" class="form-control form-control-lg @error('tanggal_praktik') is-invalid @enderror"
                                                placeholder="Pilih Tanggal Periksa">
                                            @error('tanggal_praktik')
                                            <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for=""><strong>Tekanan Darah (<span class="text-success">mmHg</span>)</strong></label>
                                            <input type="text" name="tekanan_darah" id="tekanan_darah" class="form-control form-control-lg @error('tekanan_darah') is-invalid @enderror"
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
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for=""><strong>Berat Badan (<span class="text-success">Kg</span>)</strong></label>
                                            <input type="text" name="berat_badan" id="berat_badan" class="form-control form-control-lg @error('berat_badan') is-invalid @enderror"
                                                placeholder="Masukkan Berat Badan Ibu Hamil (Hanya Angka Saja)">
                                            @error('berat_badan')
                                            <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for="jenis_kontrasepsi"><strong>Jenis Kontrasepsi</strong></label>
                                            <select name="jenis_kb" id="jenis_kb" class="form-control form-control-lg @error('jenis_kb') is-invalid @enderror">
                                                <option value="">Pilih Jenis Kontrasepsi</option>
                                                <option value="Pil">Pil</option>
                                                <option value="IUD">IUD</option>
                                                <option value="Suntik">Suntik</option>
                                            </select>
                                            @error('jenis_kb')
                                            <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>
                                </div>  
                                
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for="tanggal"><strong>Tanggal Kembali</strong></label>
                                            <input type="date" name="tanggal_kembali" id="tanggal_kembali" class="form-control form-control-lg @error('tanggal_kembali') is-invalid @enderror"
                                                placeholder="Pilih Tanggal Kembali">
                                            @error('tanggal_kembali')
                                            <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for=""><strong>Keluhan yang Dialami</strong></label>
                                            <input type="text" name="keluhan" id="keluhan" class="form-control form-control-lg @error('keluhan') is-invalid @enderror"
                                                placeholder="Masukkan Keluhan yang Dialami">
                                            @error('keluhan')
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

                            </div>
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