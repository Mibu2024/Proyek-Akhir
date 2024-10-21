<!-- title riwayat kesehatan -->
<div class="container-title-riwayat-kesehatan">
   <div class="row align-items-center">
      <div class="col-sm-6">
         <h3>Riwayat Pemeriksaan</h3>
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
         <a href="{{ route('data-kesehatan.create') }}" class="btn btn-create-data-kesehatan ml-2 d-flex align-items-center justify-content-center">
         <i class="flaticon2-add-1"></i>
         <span>Tambah Catatan Kesehatan</span> 
         </a>
      </div>
   </div>
</div>



<!-- card list riwayat kesehatan -->
@if ($healthRecords->isEmpty())
    <p style="text-align: center;">-- No record found --</p>
@else
    @foreach ($healthRecords as $record)
        <div class="card-list-kesehatan">
        <div class="container">
            <div class="row">
                <div class="col-sm-5">
                    <p>{{ \Carbon\Carbon::parse($record->tanggal)->format('l') }}</p>
                    <h4>{{ \Carbon\Carbon::parse($record->tanggal)->format('d F Y') }}</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge">Pemeriksa: {{ $record->nama_pemeriksa}}</span>
                </div>
                <div class="col-sm col-hpl">
                    <span class="status-badge">Tindakan: {{ $record->tindakan }}</span>
                </div>
                <div class="col-sm-1 justify-content-end">
                    <button href="{{ route('data-ibu-hamil.detail', $record->id) }}" type="button" class="btn btn-outline-info status-badge">View</button>
                </div>
            </div>  
        </div>
        </div>
    @endforeach
@endif

<head>
    <style>
        .card-list-kesehatan {
            position: relative;
            max-width: 100%;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 20px;
            padding: 20px;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
            background-color: #f8f9fa;
        }

        .card-list-kesehatan .row {
            align-items: center;
        }

        .card-list-kesehatan p {
            margin-bottom: 0;
            color: #6c757d;
        }

        .card-list-kesehatan h4 {
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

        .btn-create-data-kesehatan {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #EBF8FF;
            border: 1px solid #4DBEFF;
            color: #4DBEFF;
        }

        .btn-create-data-kesehatan:hover {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #4DBEFF;
            border: 1px solid #4DBEFF;
            color: white;
        }

        .btn-create-data-kesehatan:hover .flaticon2-add-1 {
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

        .container-title-riwayat-kesehatan {
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