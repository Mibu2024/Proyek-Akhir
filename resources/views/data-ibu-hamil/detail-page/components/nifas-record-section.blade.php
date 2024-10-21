<!-- title riwayat nifas -->
<div class="container-title-riwayat-nifas">
   <div class="row align-items-center">
      <div class="col-sm-6">
         <h3>Riwayat Catatan Nifas</h3>
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
         <a href="{{ route('data-nifas.create') }}" class="btn btn-create-data-nifas ml-2 d-flex align-items-center justify-content-center">
         <i class="flaticon2-add-1"></i>
         <span>Tambah Catatan Nifas</span> 
         </a>
      </div>
   </div>
</div>

<!-- Modal -->
<div class="modal fade" id="nifasRecordModal" tabindex="-1" aria-labelledby="nifasRecordModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="nifasRecordModalLabel">Nifas Record Details</h5>
      </div>
      <div class="modal-body">
        <div class="container">
          <div class="row">
            <!-- First Column -->
            <div class="col-md-6">
              <p><strong>Tanggal:</strong> <span id="modalTanggalKunjungan"></span></p>
              <p><strong>Nama Ibu:</strong> <span id="modalNamaIbuNifas"></span></p>
              <p><strong>Kunjungan Nifas:</strong> <span id="modalKunjunganNifas"></span></p>
              <p><strong>Hasil Periksa Payudara:</strong> <span id="modalPeriksaPayudara"></span></p>
              <p><strong>Hasil Periksa Jalan Lahir:</strong> <span id="modalPeriksaJalanLahir"></span></p>
            </div>
            <!-- Second Column -->
            <div class="col-md-6">
              <p><strong>Hasil Periksa Pendarahan:</strong> <span id="modalPeriksaPendarahan"></span></p>
              <p><strong>Vitamin A:</strong> <span id="modalVitaminA"></span></p>
              <p><strong>Masalah:</strong> <span id="modalMasalahNifas"></span></p>
              <p><strong>Tindakan:</strong> <span id="modalTindakanNifas"></span></p>
              </div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

@if ($nifasRecords->isEmpty())
    <p style="text-align: center;">No record found</p>
@else
    @foreach ($nifasRecords as $record)
<!-- card list riwayat nifas -->
        <div class="card-list-nifas">
            <div class="container">
                
                <div class="row">
                    <!-- Margin for spacing -->
                    <div class="col-sm-5">
                        <p>{{ \Carbon\Carbon::parse($record->tanggal)->format('l') }}</p>
                        <h4>{{ \Carbon\Carbon::parse($record->tanggal)->format('d F Y') }}</h4>
                    </div>
                    <div class="col-sm-2">
                        <span class="status-badge">Masalah: {{ $record->masalah }}</span>
                    </div>
                    <div class="col-sm">
                        <span class="status-badge">Tindakan: {{ $record->tindakan }}</span>
                    </div>
                    <div class="col-sm-1 text-end">
                        <button 
                            data-toggle="modal" 
                            data-target="#nifasRecordModal" 
                            type="button" 
                            class="btn btn-outline-info status-badge"
                            onclick="setNifasData({{ json_encode($record) }})"
                            >
                            View
                        </button>
                    </div>
                </div>
            </div>
        </div>
    @endforeach
@endif

<!-- modal script -->
<script>
    function setNifasData(record) {
    document.getElementById('modalTanggalKunjungan').textContent = record.tanggal;
    document.getElementById('modalNamaIbuNifas').textContent = record.nama_ibu;
    document.getElementById('modalKunjunganNifas').textContent = record.kunjungan_nifas;
    document.getElementById('modalPeriksaPayudara').textContent = record.hasil_periksa_payudara;
    document.getElementById('modalPeriksaJalanLahir').textContent = record.hasil_periksa_jalan_lahir;
    document.getElementById('modalPeriksaPendarahan').textContent = record.hasil_periksa_pendarahan;
    document.getElementById('modalVitaminA').textContent = record.vitamin_a;
    document.getElementById('modalMasalahNifas').textContent = record.masalah;
    document.getElementById('modalTindakanNifas').textContent = record.tindakan;
}
</script>

<head>
    <style>
        .card-list-nifas {
            position: relative;
            max-width: 100%;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 20px;
            padding: 20px;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
            background-color: #f8f9fa;
        }

        .card-list-nifas .row {
            align-items: center;
        }

        .card-list-nifas p {
            margin-bottom: 0;
            color: #6c757d;
        }

        .card-list-nifas h4 {
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

        .btn-create-data-nifas {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #EBF8FF;
            border: 1px solid #4DBEFF;
            color: #4DBEFF;
        }

        .btn-create-data-nifas:hover {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #4DBEFF;
            border: 1px solid #4DBEFF;
            color: white;
        }

        .btn-create-data-nifas:hover .flaticon2-add-1 {
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

        .container-title-riwayat-nifas {
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