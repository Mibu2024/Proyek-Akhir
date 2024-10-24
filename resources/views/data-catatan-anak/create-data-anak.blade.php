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
                            <li class="breadcrumb-item active" aria-current="page">Create Data Kehamilan</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- form section -->
                    <form action="{{ route('data-anak.store') }}" method="post">
                    @csrf

                        <input type="hidden" name="id_ibu" value="{{ $data_ibu_hamils -> id }}">

                        <div class="form-anak-container">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for="tanggal"><strong>Tanggal Periksa</strong></label>
                                            <input type="date" name="tanggal" id="tanggal" class="form-control form-control-lg @error('tanggal') is-invalid @enderror"
                                                placeholder="Pilih Tanggal Periksa">
                                            @error('tanggal')
                                                <span class="invalid-feedback" role="alert">
                                                    <strong>{{ $message }}</strong>
                                                </span>
                                            @enderror
                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for=""><strong>Nama Anak</strong></label>
                                            <input type="text" name="nama_anak" id="nama_anak" class="form-control form-control-lg @error('nama_anak') is-invalid @enderror"
                                                placeholder="Masukkan Nama Anak">
                                            @error('nama_anak')
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
                                            <label for="tanggal"><strong>Tanggal Lahir Anak</strong></label>
                                            <input type="date" name="tanggal_lahir" id="tanggal_lahir" class="form-control form-control-lg @error('tanggal_lahir') is-invalid @enderror"
                                                placeholder="Pilih Tanggal Lahir Anak">
                                            @error('tanggal_lahir')
                                            <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for=""><strong>Umur Anak</strong></label>
                                            <input type="text" name="umur" id="umur" class="form-control form-control-lg @error('umur') is-invalid @enderror"
                                                placeholder="Masukkan Umur Anak">
                                            @error('umur')
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
                                            <label for=""><strong>Berat Badan Anak (<span class="text-success">Kg</span>)</strong></label>
                                            <input type="text" name="berat_badan" id="berat_badan" class="form-control form-control-lg @error('berat_badan') is-invalid @enderror"
                                                placeholder="Masukkan Berat Badan Anak (Hanya Angka Saja)">
                                            @error('berat_badan')
                                            <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>

                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for=""><strong>Tinggi Badan</strong></label>
                                            <input type="text" name="tinggi_badan" id="tinggi_badan" class="form-control form-control-lg @error('tinggi_badan') is-invalid @enderror"
                                                placeholder="Masukkan Tinggi Badan Anak (Cm)">
                                            @error('tinggi_badan')
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
                                            <label for=""><strong>Lingkar Kepala</strong></label>
                                            <input type="text" name="lingkar_kepala" id="lingkar_kepala" class="form-control form-control-lg @error('lingkar_kepala') is-invalid @enderror"
                                                placeholder="Masukkan Lingkar Kepala Anak (Cm)">
                                            @error('lingkar_kepala')
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