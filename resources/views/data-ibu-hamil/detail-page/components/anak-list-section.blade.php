<div class="container-title-riwayat-anak">
   <div class="row align-items-center">
      <div class="col-sm-6">
         <h3>List Anak</h3>
      </div>
      <div class="col-sm-6 d-flex justify-content-end align-items-center">
         <div class="btn-group me-2">
            <a type="button" class="btn btn-sort dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span>Bulan</span>
            </a>
            <div class="dropdown-menu">
               <a class="dropdown-item" href="#">Januari</a>
               <a class="dropdown-item" href="#">Februari</a>
               <a class="dropdown-item" href="#">Maret</a>
               <a class="dropdown-item" href="#">April</a>
               <a class="dropdown-item" href="#">Mei</a>
               <a class="dropdown-item" href="#">Juni</a>
               <a class="dropdown-item" href="#">Juli</a>
               <a class="dropdown-item" href="#">Agustus</a>
               <a class="dropdown-item" href="#">September</a>
               <a class="dropdown-item" href="#">Oktober</a>
               <a class="dropdown-item" href="#">November</a>
               <a class="dropdown-item" href="#">Desember</a>
            </div>
         </div>
         <a href="{{ route('data-anak.create') }}" class="btn btn-create-data-anak ml-2 d-flex align-items-center justify-content-center">
         <i class="flaticon2-add-1"></i>
         <span>Tambah List Anak</span> 
         </a>
      </div>
   </div>
</div>

@if ($anakRecords->isEmpty())
    <p style="text-align: center;">No record found</p>
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
                    <button type="button" class="btn btn-outline-info status-badge" style="font-size: 12px; border-radius: 8px;">View</button>
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

    </style>
</head>