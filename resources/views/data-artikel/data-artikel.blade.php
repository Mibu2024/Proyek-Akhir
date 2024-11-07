@extends('layouts.app')

@section('title', 'Data Artikel')

@section('content')
<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
    <div class="container">
        <div class="card card-custom">
            <div class="card-body">
                <!-- Include search and action buttons -->
                @include('data-artikel.components.search_and_actions')
                
                <div class="table-responsive">
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th><b>No</b></th>
                                <th><b>Tanggal Upload</b></th>
                                <th><b>Judul</b></th>
                                <th><b>Isi Artikel</b></th>
                                <th><b>Author</b></th>
                                <th><b>Foto Artikel</b></th>
                                <th><b>Aksi</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            @php
                                $num = ($data_artikels->currentPage() - 1) * $data_artikels->perPage() + 1;
                            @endphp
                            @forelse ($data_artikels as $dk)
                                <tr>
                                    <td>{{ $num++ }}</td>
                                    <td>{{ $dk->tanggal }}</td>
                                    <td>{{ $dk->judul }}</td>
                                    <td>{{ $dk->isi }}</td>
                                    <td>{{ $dk->author }}</td>
                                    <td>
                                        <!-- Display eye icon for viewing the image -->
                                        @if($dk->foto)
                                            <a href="{{ route('data-artikel.view-foto-artikel', $dk->id) }}" target="blank">
                                                <i class="flaticon-eye"></i>
                                            </a>
                                        @else
                                            <span>No Image</span>
                                        @endif
                                    </td>
                                    <td>
                                        <!-- Include action buttons -->
                                        @include('data-artikel.components.action_buttons', ['dk' => $dk])
                                    </td>
                                </tr>
                            @empty
                                <tr>
                                    <td colspan="7" class="text-center">Tidak Ada Data Ditemukan</td>
                                </tr>
                            @endforelse
                        </tbody>
                    </table>
                </div>
                
                <!-- Include pagination -->
                @include('data-artikel.components.pagination')
            </div>
        </div>
    </div>
</div>
@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush
