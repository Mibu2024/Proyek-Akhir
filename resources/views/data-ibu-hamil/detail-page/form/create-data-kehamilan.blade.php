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
                            <li class="breadcrumb-item active" aria-current="page">Create Data Kehamilan</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- form section -->
                    <form action="{{ route('data-kehamilan.store') }}" method="post">
                    @csrf

                        <input type="hidden" name="id_ibu" value="{{ $ibuHamil -> id }}">

                        <div class="form-kehamilan-container">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for="tanggal_kehamilan"><strong>Tanggal Kehamilan</strong></label>
                                            <input type="date" name="tanggal_kehamilan" id="tanggal_kehamilan" class="form-control form-control-lg @error('tanggal_kehamilan') is-invalid @enderror"
                                                placeholder="Pilih Tanggal">
                                            @error('tanggal_kehamilan')
                                            <span class="invalid-feedback" role="alert">
                                                <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>
                                    
                                    <div class="col-sm-6">
                                        <div class="form-group mt-5">
                                            <label for=""><strong>Kehamilan Ke</strong></label>
                                            <input type="text" name="kehamilan_ke" id="kehamilan_ke" class="form-control form-control-lg @error('kehamilan_ke') is-invalid @enderror"
                                                placeholder="Masukkan Kehamilan Ke Berapa (Hanya Angka Saja)" value="{{ old('kehamilan_ke') }}">
                                            @error('kehamilan_ke')
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
                                            <label for="tanggal_hpl"><strong>Tanggal HPL</strong></label>
                                            <input type="date" name="tanggal_hpl" id="tanggal_hpl" class="form-control form-control-lg @error('tanggal_hpl') is-invalid @enderror"
                                                placeholder="Pilih Tanggal">
                                            @error('tanggal_hpl')
                                            <span class="invalid-feedback" role="alert">
                                                <strong>{{ $message }}</strong>
                                            </span>
                                            @enderror
                                        </div>
                                    </div>
                                </div>    

                                <div class="text-right">
                                    
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