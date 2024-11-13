<div class="container-title-riwayat-anak">
   <div class="row align-items-center">
      <div class="col-sm-6">
         <h3>List Anak</h3>
      </div>
      <div class="col-sm-6 d-flex justify-content-end align-items-center">
        <div class="btn-group me-2">
            <button type="button" class="btn btn-sort dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span>{{ $anakMonthName }}</span>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '']) }}">Semua</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '1']) }}">Januari</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '2']) }}">Februari</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '3']) }}">Maret</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '4']) }}">April</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '5']) }}">Mei</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '6']) }}">Juni</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '7']) }}">Juli</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '8']) }}">Agustus</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '9']) }}">September</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '10']) }}">Oktober</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '11']) }}">November</a>
                <a class="dropdown-item" href="{{ request()->fullUrlWithQuery(['anak_month' => '12']) }}">Desember</a>
            </div>
        </div>
         <a href="{{ route('data-anak.create', $ibuHamil -> id) }}" class="btn btn-create-data-anak ml-2 d-flex align-items-center justify-content-center">
         <i class="flaticon2-add-1"></i>
         <span>Tambah List Anak</span> 
         </a>
      </div>
   </div>
</div>

@if ($anakRecords->isEmpty())
    <p 
    style="text-align: center; 
    width: 100%; 
    padding: 30px; 
    border-radius: 8px;
    margin-top: 20px;
    box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);">-- No record found --</p>
@else
    @foreach ($anakRecords as $record)
<!-- card list riwayat anak -->
        <div class="card-list-anak">
        <div class="container">
            
            <div class="row">
                <!-- Margin for spacing -->
                <div class="col-sm-5">
                    <p>{{ \Carbon\Carbon::parse($record->tanggal_lahir)->format('l') }}</p>
                    <h4>{{ \Carbon\Carbon::parse($record->tanggal_lahir)->format('d F Y') }}</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge">Nama: {{ $record->nama_anak }}</span>
                </div>
                <div class="col-sm">
                    <span class="status-badge">Umur: {{ $record->umur }}</span>
                </div>
                <div class="col-sm-1 text-end">
                    <button 
                    type="button" 
                    class="btn btn-outline-info status-badge" 
                    onclick="window.location.href='{{ route('data-anak.detail', $record->id) }}'" 
                    style="font-size: 12px; border-radius: 8px;">
                        View
                    </button>
                </div>

                <div class="dropdown ml-2">
                    <button class="btn btn-light" type="button" id="dropdownMenuButton{{ $record->id }}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-ellipsis-v"></i>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton{{ $record->id }}">
                        <a class="dropdown-item" href="{{ route('data-anak.edit', [$record -> id, $record->id_ibu]) }}">Edit</a>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#deleteModal-{{ $record->id }}">Delete</a>
                    </div>
                </div>

                <div class="modal fade" id="deleteModal-{{ $record->id }}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteModalLabel">Confirm Delete</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Are you sure you want to delete this record?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <form id="deleteForm-{{ $record->id }}" action="{{ route('data-anak.delete', $record->id) }}" method="POST">
                                    @csrf
                                    @method('DELETE')
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        </div>
    @endforeach
@endif

<head>
    <style>
        .card-list-anak {
            position: relative;
            max-width: 100%;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 20px;
            padding: 20px;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
            background-color: #f8f9fa;
        }

        .card-list-anak .row {
            align-items: center;
        }

        .card-list-anak p {
            margin-bottom: 0;
            color: #6c757d;
        }

        .card-list-anak h4 {
            margin-bottom: 0;
            font-weight: bold;
            color: #495057;
        }

        .status-badge {
            display: inline-block;
            padding: 12px;
            border-radius: 8px;
            font-size: 13px;
            opacity: 0.9;
            font-weight: bold;
            text-align: center;
            background-color: #FDECC9;
            color: black;
        }

        @media (max-width: 768px) {
            .card-list-kesehatan .col-sm {
                margin-bottom: 10px;
            }
        }

        .btn-create-data-anak {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #EBF8FF;
            border: 1px solid #4DBEFF;
            color: #4DBEFF;
        }

        .btn-create-data-anak:hover {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #4DBEFF;
            border: 1px solid #4DBEFF;
            color: white;
        }

        .btn-create-data-anak:hover .flaticon2-add-1 {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #4DBEFF;
            border: 1px solid #4DBEFF;
            color: white;
        }

        .flaticon2-add-1 {
            color: #4DBEFF;
        }

        .container-title-riwayat-anak {
            margin-top: 30px;
        }

        .btn-sort {
            border-radius: 8px;
            border: 1px solid #DDE1EB;
            color: #A0A8B5;
            height: 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 150px;
            text-align: left;
            font-weight: bold;
        }

        .btn-sort::after {
            margin-left: 10px;
        }

        .card-list-anak, .container, .row {
            overflow: visible !important;
        }

    </style>
</head>