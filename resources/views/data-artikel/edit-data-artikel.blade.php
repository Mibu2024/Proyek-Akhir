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

        .border-dashed {
            border-style: dashed !important;
        }

        .cursor-pointer {
            cursor: pointer;
        }

        .upload-box {
            transition: all 0.3s ease;
        }

        .upload-box:hover {
            background-color: rgba(0, 123, 255, 0.05);
        }

        .upload-box i {
            font-size: 24px;
            display: block;
            text-align: center;
        }

        .selected-filename {
            word-break: break-all;
            margin: 8px 0;
        }
    </style>

    <script>
        function showFileName(input) {
            const uploadContent = document.getElementById('upload-content');
            const fileSelected = document.getElementById('file-selected');
            const fileNameElement = fileSelected.querySelector('.selected-filename');
            
            if (input.files && input.files[0]) {
                const fileName = input.files[0].name;
                fileNameElement.textContent = fileName;
                uploadContent.classList.add('d-none');
                fileSelected.classList.remove('d-none');
            } else {
                uploadContent.classList.remove('d-none');
                fileSelected.classList.add('d-none');
            }
        }
    </script>
</head>
<body>
    <div class="content d-flex flex-column flex-column-fluid" id="kt_content">
        <div class="container">
            <div class="card card-custom">
                <div class="card-body">

                    <!-- Breadcrumbs -->
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="{{ route('data-artikel.index') }}">Artikel</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Edit Artikel</li>
                        </ol>
                    </nav>
                    <hr>

                    <!-- form section -->
                    <form action="{{ route('data-artikel.store') }}" method="post" enctype="multipart/form-data">
                        @csrf

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for="tanggal"><strong>Tanggal</strong></label>
                                    <input type="date" name="tanggal" id="tanggal" class="form-control  @error('tanggal') is-invalid @enderror" value="{{ $data_artikels->tanggal }}"
                                        placeholder="Pilih Tanggal">
                                    @error('tanggal')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Judul</strong></label>
                                    <input type="text" name="judul" id="judul" class="form-control @error('judul') is-invalid @enderror" value="{{ $data_artikels->judul }}"
                                        placeholder="Masukkan Judul Artikel">
                                    @error('judul')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for="isi"><strong>Isi Artikel</strong></label>
                                    <textarea name="isi" id="isi" class="form-control @error('isi') is-invalid @enderror" placeholder="Masukkan Isi Artikel" rows="5">{{ $data_artikels->isi }}</textarea>
                                    @error('isi')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group mt-5">
                                    <label for=""><strong>Author</strong></label>
                                    <input type="text" name="author" id="author" class="form-control @error('author') is-invalid @enderror" value="{{ $data_artikels->author }}"
                                        placeholder="Masukkan Isi Artikel">
                                    @error('author')
                                    <span class="invalid-feedback" role="alert">
                                    <strong>{{ $message }}</strong>
                                    </span>
                                    @enderror
                                </div>
                            </div>
                        </div>

                        <div class="text-right mt-4">
                            <a href="{{ route('data-artikel.index') }}" class="btn btn-outline-primary btn-lg mr-2" role="button">Batal</a>
                            <button type="submit" class="btn btn-primary btn-lg">Simpan</button>
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