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

            .history-pemeriksaan-container {
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
                background: linear-gradient(to right, #34B28D, #0399AF, #2AAD94, #7DD957);
                /* Change colors as needed */
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

            .col-sm-3 h1 {
                font-size: 18px;
            }

            .table-responsive {
                max-height: 200px;
                overflow-y: auto;
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
                                <li class="breadcrumb-item"><a
                                        href="{{ route('data-ibu-hamil.detail', $ibuHamil->id) }}">Detail Ibu Hamil</a></li>
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

                                <div class="row mt-4">
                                    <div class="col text-right">
                                        <a href="{{ route('data-anak.edit', [$anakRecords->id, $anakRecords->id_ibu]) }}"
                                            class="btn btn-info">Edit</a>

                                        <form action="{{ route('data-anak.delete', $anakRecords->id) }}" method="POST"
                                            style="display:inline-block;"
                                            onsubmit="return confirm('Are you sure you want to delete this item?');">
                                            @csrf
                                            @method('DELETE')
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="container-title-status-imunisasi mb-n4">
                            <div class="row align-items-center">
                                <div class="col-sm-6">
                                    <h3>History Pemeriksaan</h3>
                                </div>
                            </div>
                        </div>

                        <!-- History Pemeriksaan Section -->
                        <div class="history-pemeriksaan-container">
                            <div class="container">
                                <div class="table-responsive my-3 text-center">
                                    <table class="table table-bordered-secondary">
                                        <thead>
                                            <tr>
                                                <th>Tanggal Pemeriksaan</th>
                                                <th>Berat Badan (Kg)</th>
                                                <th>Tinggi Badan (Cm)</th>
                                                <th>Catatan</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            @forelse ($historyRecords as $history)
                                                <tr>
                                                    <td>{{ $history->tgl_pemeriksaan }}</td>
                                                    <td>{{ $history->berat_badan }}</td>
                                                    <td>{{ $history->tinggi_badan }}</td>
                                                    <td>{{ $history->catatan }}</td>
                                                </tr>
                                            @empty
                                                <tr>
                                                    <td colspan="3" class="text-center">Tidak Ada History Pemeriksaan
                                                    </td>
                                                </tr>
                                            @endforelse
                                        </tbody>
                                    </table>
                                </div>
                                <div class="row my-3">
                                    <div class="col text-right">
                                        <button class="btn btn-primary" data-toggle="modal" data-target="#modalTambahPemeriksaan">Tambah Pemeriksaan</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal new data history pemeriksaan anak -->
                        <div class="modal fade" id="modalTambahPemeriksaan" tabindex="-1" role="dialog" aria-labelledby="modalTambahPemeriksaanLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <form action="{{ route('data-anak.history.store') }}" method="POST">
                                        @csrf
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="modalTambahPemeriksaanLabel">Tambah Pemeriksaan Anak</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="id_anak" value="{{ $anakRecords->id }}">
                                            <div class="form-group">
                                                <label for="tgl_pemeriksaan">Tanggal Pemeriksaan</label>
                                                <input type="date" name="tgl_pemeriksaan" class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="berat_badan">Berat Badan (kg)</label>
                                                <input type="number" name="berat_badan" step="0.01" class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="tinggi_badan">Tinggi Badan (cm)</label>
                                                <input type="number" name="tinggi_badan" step="0.01" class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="catatan">Catatan</label>
                                                <textarea name="catatan" class="form-control" rows="3"></textarea>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Tutup</button>
                                            <button type="submit" class="btn btn-primary">Simpan</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        

                        @include('data-ibu-hamil.detail-page.components.status-imunisasi-section', [
                            'imunisasiRecords' => $imunisasiRecords,
                        ])

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
