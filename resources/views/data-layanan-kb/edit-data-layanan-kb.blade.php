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
                    <form action="{{ route('data-layanan-kb.update', $data_layanan_kbs->id) }}" method="POST">
                    @csrf
                    @method('PUT')

                    <input type="hidden" name="id_ibu" value="{{ $data_ibu_hamils -> id }}">
                    <div class="row">
                        <!-- Tanggal Praktik -->
                        <div class="form-group col-md-6 mt-5">
                            <label for="tanggal_praktik"><strong>Tanggal Praktik</strong></label>
                            <input type="date" name="tanggal_praktik" id="tanggal_praktik" class="form-control form-control-lg @error('tanggal_praktik') is-invalid @enderror" value="{{ $data_layanan_kbs->tanggal_praktik }}" placeholder="Pilih Tanggal Praktik">
                            @error('tanggal_praktik')
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>

                        <!-- Tekanan Darah -->
                        <div class="form-group col-md-6 mt-5">
                            <label for="tekanan_darah"><strong>Tekanan Darah (<span class="text-success">mmHg</span>)</strong></label>
                            <input type="text" name="tekanan_darah" id="tekanan_darah" class="form-control form-control-lg @error('tekanan_darah') is-invalid @enderror" value="{{ $data_layanan_kbs->tekanan_darah }}" placeholder="Masukkan Tekanan Darah Ibu Hamil (Hanya Angka Saja)">
                            @error('tekanan_darah')
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>

                        <!-- Berat Badan -->
                        <div class="form-group col-md-6 mt-5">
                            <label for="berat_badan"><strong>Berat Badan (<span class="text-success">Kg</span>)</strong></label>
                            <input type="text" name="berat_badan" id="berat_badan" class="form-control form-control-lg @error('berat_badan') is-invalid @enderror" value="{{ $data_layanan_kbs->berat_badan }}" placeholder="Masukkan Berat Badan Ibu Hamil (Hanya Angka Saja)">
                            @error('berat_badan')
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>

                        <!-- Jenis Kontrasepsi -->
                        <div class="form-group col-md-6 mt-5">
                            <label for="jenis_kb"><strong>Jenis Kontrasepsi</strong></label>
                            <select name="jenis_kb" id="jenis_kb" class="form-control form-control-lg @error('jenis_kb') is-invalid @enderror">
                                <option value="">Pilih Jenis Kontrasepsi</option>
                                <option value="Pil" {{ $data_layanan_kbs->jenis_kb == 'Pil' ? 'selected' : '' }}>Pil</option>
                                <option value="IUD" {{ $data_layanan_kbs->jenis_kb == 'IUD' ? 'selected' : '' }}>IUD</option>
                                <option value="Suntik" {{ $data_layanan_kbs->jenis_kb == 'Suntik' ? 'selected' : '' }}>Suntik</option>
                            </select>
                            @error('jenis_kb')
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>

                        <!-- Tanggal Kembali -->
                        <div class="form-group col-md-6 mt-5">
                            <label for="tanggal_kembali"><strong>Tanggal Kembali</strong></label>
                            <input type="date" name="tanggal_kembali" id="tanggal_kembali" class="form-control form-control-lg @error('tanggal_kembali') is-invalid @enderror" value="{{ $data_layanan_kbs->tanggal_kembali }}" placeholder="Pilih Tanggal Kembali">
                            @error('tanggal_kembali')
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>

                        <!-- Keluhan -->
                        <div class="form-group col-md-6 mt-5">
                            <label for="keluhan"><strong>Keluhan</strong></label>
                            <input type="text" name="keluhan" id="keluhan" class="form-control form-control-lg @error('keluhan') is-invalid @enderror" value="{{ $data_layanan_kbs->keluhan }}" placeholder="Masukkan Keluhan (Apabila Ada)">
                            @error('keluhan')
                                <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                </span>
                            @enderror
                        </div>
                    </div>

                    <div class="text-right mt-5">
                        <a href="{{ route('data-kesehatan.index') }}" class="btn btn-outline-danger btn-lg mr-2" role="button">Batal</a>
                        <button type="submit" class="btn btn-success btn-lg">Simpan</button>
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