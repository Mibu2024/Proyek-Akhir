@extends('layouts.app')

@section('content')
<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
    <div class="container">
        <div class="card card-custom">
            <div class="card-body">
                @include('data-ibu-hamil.components.search_and_actions')
                
                <div class="table-responsive">
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th><b>No</b></th>
                                <th><b>Nama Ibu</b></th>
                                <th><b>Umur Ibu</b></th>
                                <th><b>Nomor Telepon</b></th>
                                <th><b>Nama Suami</b></th>
                                <th><b>#</b></th>
                                <th><b>Aksi</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            @forelse ($data_ibu_hamils as $index => $dih)
                                <tr>
                                    <td>{{ $index + 1 + ($data_ibu_hamils->currentPage() - 1) * $data_ibu_hamils->perPage() }}</td>
                                    <td>{{ $dih->nama_ibu }}</td>
                                    <td>{{ $dih->umur_ibu }} Tahun</td>
                                    <td>{{ $dih->no_telepon }}</td>
                                    <td>{{ $dih->nama_suami }}</td>
                                    <td>
                                        <a href="{{ route('data-ibu-hamil.detail', $dih->id) }}" class="btn btn-m btn-primary" style="background-color: #E7FFEA; color: #45A350; outline: none; box-shadow: none; border: 1px solid transparent">
                                            <b>Lihat Detail</b>
                                        </a>
                                    </td>
                                    </td>
                                    <td>
                                        @include('data-ibu-hamil.components.action_buttons', ['dih' => $dih])
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
                
                @include('data-ibu-hamil.components.pagination')
            </div>
        </div>
    </div>
</div>

@include('data-ibu-hamil.components.tambah_hpl_modal')
@include('data-ibu-hamil.components.user_profile_panel')

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $('#entries').change(function() {
            window.location = "{{ route('home') }}?search={{ request('search') }}&per_page=" + $(this).val();
        });
        
        $('.tambah-hpl-btn').click(function() {
            var id = $(this).data('id');
            $('#ibuHamilId').val(id);
        });
    });
</script>
@endpush