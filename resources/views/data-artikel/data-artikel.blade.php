@extends('layouts.app')

@section('content')

<head>
    <style>
       .table {
            width: 100%;
            border-collapse: collapse;
            font-size: 14px;
            color: #333;
        }

        .table thead th {
            font-weight: bold;
            color: #000;
        }

        .article-cell {
            display: flex;
            align-items: center;
            gap: 15px; /* Increase gap if needed */
        }

        .thumbnail-image {
            width: 150px; /* Make the image larger */
            height: 80px;
            object-fit: contain;
            border-radius: 4px;
            margin-right: 20px;
        }

        .article-details h6 {
            font-size: 16px;
            font-weight: 600;
            margin-bottom: 5px;
            text-align: justify;
        }

        .article-details {
            max-width: 500px;
        }

        .article-details p {
            font-size: 16px;
            font-weight: 600;
            margin-bottom: 5px;
            text-align: justify;
        }

        .article-details .description {
            font-size: 14px;
            color: #6c757d;
            text-align: justify; /* Justify the text */
            margin: 0;
        }

        .options-icon {
            font-size: 20px;
            cursor: pointer;
        }

        .table tbody tr {
            border-bottom: 1px solid #e9ecef;
            padding: 10px 0;
        }

        .table tbody tr:hover {
            background-color: #f8f9fa;
        }

        .table tbody td {
            vertical-align: middle;
        }

        .table thead th, .table tbody td {
            padding: 10px 15px;
        }


    </style>
</head>
<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
    <div class="container">
        <div class="card card-custom">
            <div class="card-body">
                @include('data-artikel.components.search_and_actions')
                
                <div class="table-responsive">
                <table class="table text-center">
                    <thead>
                        <tr>
                            <th class="text-left"><b>Artikel</b></th>
                            <th><b>Tanggal Unggah</b></th>
                            <th><b>Penulis</b></th>
                            <th><b>#</b></th>
                        </tr>
                    </thead>
                    <tbody>
                        @forelse ($data_artikels as $index => $da)
                            <tr>
                                <td class="article-cell d-flex align-items-center">
                                    <img src="{{ $da->foto }}" alt="Thumbnail" class="thumbnail-image">
                                    <div class="article-details">
                                        <h6 class="mb-0">{{ $da->judul }}</h6>
                                        <p class="text-muted description">{{ \Illuminate\Support\Str::words($da->isi, 50, '...') }}</p>
                                    </div>
                                </td>
                                <td>{{ $da->tanggal }}</td>
                                <td>{{ $da->author }}</td>
                                <td>
                                    <a href="#" class="options-icon">⋮</a>
                                </td>
                            </tr>
                        @empty
                            <tr>
                                <td colspan="4" class="text-center">Tidak Ada Data Ditemukan</td>
                            </tr>
                        @endforelse
                    </tbody>
                </table>


                </div>
                
                @include('data-artikel.components.pagination')
            </div>
        </div>
    </div>
</div>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $('#entries').change(function() {
            window.location = "{{ route('home') }}?search={{ request('search') }}&per_page=" + $(this).val();
        });
    });
</script>
@endpush