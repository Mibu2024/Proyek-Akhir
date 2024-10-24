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

        .detail-anak-container {
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
            font-size: 20px;
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
                            <li class="breadcrumb-item"><a href="{{ route('data-ibu-hamil.detail', $ibuHamil->id) }}">Detail Ibu Hamil</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Detail Anak</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- Banner Section -->
                    <div class="banner-container">
                        <div class="banner-background">
                            <div class="banner-text">
                                <h1>Detail Anak</h1>
                            </div>
                        </div>
                    </div>

                    <!-- Data Anak Section -->
                    <div class="detail-anak-container">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-3">
                                    Nama Anak
                                    <h1>{{ $anakRecords->nama_anak }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Nama Ibu
                                    <h1>{{ $anakRecords->nama_ibu }} Tahun</h1>
                                </div>
                                <div class="col-sm-3">
                                    Tanggal Lahir
                                    <h1>{{ $anakRecords->tanggal_lahir }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Umur (Bulan)
                                    <h1>{{ $anakRecords->umur }}</h1>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-3">
                                    Berat Badan (Kg)
                                    <h1>{{ $anakRecords->berat_badan }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Lingkar Kepala (Cm)
                                    <h1>{{ $anakRecords->lingkar_kepala }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Tinggi Badan (Cm)
                                    <h1>{{ $anakRecords->tinggi_badan }}</h1>
                                </div>
                            </div>
                        </div>
                    </div>

                    @include('data-ibu-hamil.detail-page.components.status-imunisasi-section', ['imunisasiRecords' => $imunisasiRecords])

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