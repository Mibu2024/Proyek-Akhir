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
                            <li class="breadcrumb-item active" aria-current="page">Create Data Ibu</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- form section -->
                    <form action="{{ route('data-ibu-hamil.store') }}" method="post">
                        @csrf
                        
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group mt-4">
                                    <label><strong>Nama Ibu Hamil</strong></label>
                                    <input type="text" name="nama_ibu" id="nama_ibu" class="form-control form-control-lg @error('nama_ibu') is-invalid @enderror"
                                        placeholder="Masukkan Nama Ibu Hamil" value="{{ old('nama_ibu') }}">
                                    @error('nama_ibu')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Umur Ibu (<span class="text-success">Tahun</span>)</strong></label>
                                    <input type="text" name="umur_ibu" id="umur_ibu" class="form-control form-control-lg @error('umur_ibu') is-invalid @enderror"
                                        placeholder="Masukkan Umur Ibu Hamil (Hanya Angka Saja)" value="{{ old('umur_ibu') }}">
                                    @error('umur_ibu')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Alamat</strong></label>
                                    <input type="text" name="alamat" id="alamat" class="form-control form-control-lg @error('alamat') is-invalid @enderror"
                                        placeholder="Masukkan Alamat" value="{{ old('alamat') }}">
                                    @error('alamat')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Email</strong></label>
                                    <input type="email" name="email" id="email" class="form-control form-control-lg @error('email') is-invalid @enderror"
                                        placeholder="Masukkan Email" value="{{ old('email') }}">
                                    @error('email')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>NIK</strong></label>
                                    <input type="text" name="nik" id="nik" class="form-control form-control-lg @error('nik') is-invalid @enderror"
                                        placeholder="Masukkan NIK / Nomor Induk Kependudukan (Hanya Angka Saja)" value="{{ old('nik') }}">
                                    @error('nik')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Nomor Telepon</strong></label>
                                    <input type="text" name="no_telepon" id="no_telepon" class="form-control form-control-lg @error('no_telepon') is-invalid @enderror"
                                        placeholder="Masukkan Nomor Telepon (Hanya Angka Saja)" value="{{ old('no_telepon') }}">
                                    @error('no_telepon')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Kehamilan Ke</strong></label>
                                    <input type="text" name="kehamilan_ke" id="kehamilan_ke" class="form-control form-control-lg @error('kehamilan_ke') is-invalid @enderror"
                                        placeholder="Masukkan Kehamilan Ke Berapa (Hanya Angka Saja)" value="{{ old('kehamilan_ke') }}">
                                    @error('kehamilan_ke')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-sm-6">
                                <div class="form-group mt-4">
                                    <label><strong>Nama Suami</strong></label>
                                    <input type="text" name="nama_suami" id="nama_suami" class="form-control form-control-lg @error('nama_suami') is-invalid @enderror"
                                        placeholder="Masukkan Nama Suami" value="{{ old('nama_suami') }}">
                                    @error('nama_suami')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Umur Suami (<span class="text-success">Tahun</span>)</strong></label>
                                    <input type="text" name="umur_suami" id="umur_suami" class="form-control form-control-lg @error('umur_suami') is-invalid @enderror"
                                        placeholder="Masukkan Umur Suami (Hanya Angka Saja)" value="{{ old('umur_suami') }}">
                                    @error('umur_suami')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>No JKN Faskes Tk 1</strong></label>
                                    <input type="text" name="no_jkn_faskes_tk_1" id="no_jkn_faskes_tk_1" class="form-control form-control-lg @error('no_jkn_faskes_tk_1') is-invalid @enderror"
                                        placeholder="Masukkan No JKN Faskes Tk 1" value="{{ old('no_jkn_faskes_tk_1') }}">
                                    @error('no_jkn_faskes_tk_1')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>No JKN Rujukan</strong></label>
                                    <input type="text" name="no_jkn_rujukan" id="no_jkn_rujukan" class="form-control form-control-lg @error('no_jkn_rujukan') is-invalid @enderror"
                                        placeholder="Masukkan No JKN Rujukan" value="{{ old('no_jkn_rujukan') }}">
                                    @error('no_jkn_rujukan')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Golongan Darah</strong></label>
                                    <input type="text" name="gol_darah" id="gol_darah" class="form-control form-control-lg @error('gol_darah') is-invalid @enderror"
                                        placeholder="Masukkan Golongan Darah" value="{{ old('gol_darah') }}">
                                    @error('gol_darah')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Pekerjaan</strong></label>
                                    <input type="text" name="pekerjaan" id="pekerjaan" class="form-control form-control-lg @error('pekerjaan') is-invalid @enderror"
                                        placeholder="Masukkan Pekerjaan" value="{{ old('pekerjaan') }}">
                                    @error('pekerjaan')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label><strong>Password</strong></label>
                                    <input type="password" name="password" id="password" class="form-control form-control-lg @error('password') is-invalid @enderror"
                                        placeholder="Password" value="{{ old('password') }}">
                                    @error('password')
                                        <span class="invalid-feedback" role="alert">
                                            <strong>{{ $message }}</strong>
                                        </span>
                                    @enderror
                                </div>

                                <div class="form-group mt-4">
                                    <label for="user_id"><strong>Pilih Puskesmas</strong></label>
                                    <select name="user_id" id="user_id" class="form-control form-control-lg @error('user_id') is-invalid @enderror">
                                        <option value="">Pilih Puskesmas</option>
                                        @foreach($users as $user)
                                            <option value="{{ $user->id }}">{{ $user->name }}</option>
                                        @endforeach
                                    </select>
                                    @error('user_id')
                                        <div class="invalid-feedback">{{ $message }}</div>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col-12 text-right">
                                <a href="{{ route('home') }}" class="btn btn-outline-danger mr-2" role="button">Batal</a>
                                <button type="submit" class="btn btn-success">Simpan</button>
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