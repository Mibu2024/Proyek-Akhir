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
                                <th><b>No</b></th>
                                <th><b>Tanggal Periksa</b></th>
                                <th><b>Nama Ibu</b></th>
                                <th><b>Masalah</b></th>
                                <th><b>Tindakan</b></th>
                                <th><b>#</b></th>
                                <th><b>Aksi</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            @forelse ($data_nifas as $index => $dn)
                                <tr>
                                    <td>{{ $index + 1 + ($data_nifas->currentPage() - 1) * $data_nifas->perPage() }}</td>
                                    <td>{{ $dn->tanggal }}</td>
                                    <td>{{ $dn->nama_ibu }}</td>
                                    <td>{{ $dn->masalah }}</td>
                                    <td>{{ $dn->tindakan }}</td>
                                    <td><button class="btn btn-m btn-primary view-detail-btn" style="background-color: #E7FFEA; color: #45A350; outline: none; box-shadow: none; border: 1px solid transparent;" data-id="{{ $dn->id }}" data-toggle="modal"><b>Lihat Detail</b></button>
                                    </td>
                                    <td>
                                        @include('data-catatan-nifas.components.action_buttons', ['dn' => $dn])
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
                
                @include('data-catatan-nifas.components.pagination')
            </div>
        </div>
    </div>
</div>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush