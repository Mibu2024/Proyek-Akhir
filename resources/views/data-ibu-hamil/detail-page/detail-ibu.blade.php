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
                            <li class="breadcrumb-item active" aria-current="page">Detail</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- Banner Section -->
                    <div class="banner-container">
                        <div class="banner-background">
                            <div class="banner-text">
                                <h1>Detail Ibu Hamil</h1>
                            </div>
                        </div>
                    </div>

                    <!-- detail ibu section -->
                    <div class="detail-ibu-container">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-3">
                                    Nama Ibu
                                    <h1>{{ $ibuHamil->nama_ibu }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Umur Ibu
                                    <h1>{{ $ibuHamil->umur_ibu }} Tahun</h1>
                                </div>
                                <div class="col-sm-3">
                                    Alamat
                                    <h1>{{ $ibuHamil->alamat }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Email
                                    <h1>{{ $ibuHamil->email }}</h1>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-3">
                                    NIK
                                    <h1>{{ $ibuHamil->nik }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Nomor Telepon
                                    <h1>{{ $ibuHamil->no_telepon }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Kehamilan Ke
                                    <h1>{{ $ibuHamil->kehamilan_ke }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Nama Suami
                                    <h1>{{ $ibuHamil->nama_suami }}</h1>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-3">
                                    Umur Suami
                                    <h1>{{ $ibuHamil->umur_suami }} Tahun</h1>
                                </div>
                                <div class="col-sm-3">
                                    No. JKN TK 1
                                    <h1>{{ $ibuHamil->no_jkn_faskes_tk_1 }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    No. JKN Rujukan
                                    <h1>{{ $ibuHamil->no_jkn_rujukan }}</h1>
                                </div>
                                <div class="col-sm-3">
                                    Golongan Darah
                                    <h1>{{ $ibuHamil->gol_darah }}</h1>
                                </div>
                            </div>
                        </div>
                    </div>

                    @include('data-ibu-hamil.detail-page.components.kehamilan-record-section', ['kehamilanRecords' => $kehamilanRecords])
                    @include('data-ibu-hamil.detail-page.components.nifas-record-section', ['nifasRecords' => $nifasRecords])
                    @include('data-ibu-hamil.detail-page.components.anak-list-section', ['anakRecords' => $anakRecords])
                    @include('data-ibu-hamil.detail-page.components.kb-record-section', ['kbRecords' => $kbRecords])
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