@extends('layouts.app')

@section('content')
<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
    <div class="container">
        <div class="card card-custom">
            <div class="card-body">
                @include('data-catatan-nifas.components.search_and_actions')
                
                <div class="table-responsive">
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Nama Ibu</th>
                                <th>Tanggal Kunjungan</th>
                                <th>Tekanan Darah (mmHg)</th>
                                <th>Berat Badan (Kg)</th>
                                <th>Jenis KB</th>
                                <th>Tanggal Kembali</th>
                                <th>Keluhan</th>
                            </tr>
                        </thead>
                        <tbody>
                            @forelse ($data_layanan_kbs as $index => $dlk)
                                <tr>
                                    <td>{{ $index + 1 + ($data_layanan_kbs->currentPage() - 1) * $data_layanan_kbs->perPage() }}</td>
                                    <td>{{ $dlk->nama_ibu }}</td>
                                    <td>{{ $dlk->tanggal_praktik }}</td>
                                    <td>{{ $dlk->tekanan_darah }}</td>
                                    <td>{{ $dlk->berat_badan }}</td>
                                    <td>{{ $dlk->jenis_kb }}</td>
                                    <td>{{ $dlk->tanggal_kembali }}</td>
                                    <td>{{ $dlk->keluhan }}</td>
                                    <td><button class="btn btn-m btn-primary view-detail-btn" style="background-color: #E7FFEA; color: #45A350; outline: none; box-shadow: none; border: 1px solid transparent;" data-id="{{ $dlk->id }}" data-toggle="modal"><b>Lihat Detail</b></button>
                                    </td>
                                    <td>
                                        @include('data-layanan-kb.components.action_buttons', ['dlk' => $dlk])
                                    </td>
                                </tr>
                            @empty
                                <tr>
                                    <td colspan="16" class="text-center">Tidak Ada Data Ditemukan</td>
                                </tr>
                            @endforelse
                        </tbody>
                    </table>
                </div>
                
                @include('data-layanan-kb.components.pagination')
            </div>
        </div>
    </div>
</div>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush