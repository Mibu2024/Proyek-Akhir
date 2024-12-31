<!-- title riwayat kesehatan -->
<div class="container-title-status-imunisasi">
   <div class="row align-items-center">
        <div class="col-sm-6">
            <h3>Status Imunisasi</h3>
        </div>

        <div class="col-sm-6 d-flex justify-content-end align-items-center">
            <a href="#" class="btn btn-create-data-imunisasi ml-2 d-flex align-items-center justify-content-center" data-toggle="modal" data-target="#imunisasiModal">
                <i class="flaticon2-add-1"></i>
                <span>Tambah Imunisasi</span>
            </a>
        </div>
   </div>
</div>

<!-- Modal for Adding Immunization Date -->
<div class="modal fade" id="imunisasiModal" tabindex="-1" aria-labelledby="imunisasiModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="{{ route('data-anak.updateImunisasi', $anakRecords->id) }}" method="POST">
            @csrf
            @method('PUT')
            <input type="hidden" name="imunisasi_column" id="imunisasi_column">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="imunisasiModalLabel">Tambah Imunisasi</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="imunisasi_type">Jenis Imunisasi</label>
                        <select name="imunisasi_type" id="imunisasi_type" class="form-control" required>
                            <option value="">Pilih Jenis Imunisasi</option>
                            @foreach($jenisImunisasi as $jenis)
                                <option value="{{ $jenis->jenis_imunisasi }}">{{ $jenis->jenis_imunisasi }}</option>
                            @endforeach
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="tanggal_imunisasi">Tanggal Imunisasi</label>
                        <input type="date" name="tanggal_imunisasi" id="tanggal_imunisasi" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="merek_imunisasi">Merek Imunisasi</label>
                        <input type="text" name="merek_imunisasi" id="merek_imunisasi" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="nama_pemeriksa">Nama Pemeriksa</label>
                        <input type="text" name="nama_pemeriksa" id="nama_pemeriksa" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Submit</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="container">
    <!-- Hepatitis B -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <p>Tanggal Imunisasi</p>
                    <h4>31 Desember 2024</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge">
                        Jenis: Hepatitis B
                    </span>
                </div>
                <div class="col-sm-5 d-flex justify-content-end"> <!-- Menggunakan d-flex dan justify-content-end -->
                    <button
                        type="button"
                        class="btn btn-outline-info status-badge mr"
                        style="font-size: 12px; border-radius: 8px;">
                            View
                    </button>
                    <div class="dropdown">
                        <button class="btn btn-light" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-ellipsis-v"></i>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="#">Edit</a>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#">Delete</a>
                            <a class="dropdown-item" href="#">View Logs</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal for Logs -->
<div class="modal fade" id="logsModal" tabindex="-1" aria-labelledby="logsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="logsModalLabel">Logs</h5

<div class="container">
    <!-- Hepatitis B -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <p>Tanggal Imunisasi</p>
                    <h4>31 Desember 2024</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge">
                        Jenis: Hepatitis B
                    </span>
                </div>
                <div class="col-sm-5 d-flex justify-content-end"> <!-- Menggunakan d-flex dan justify-content-end -->
                    <button
                        type="button"
                        class="btn btn-outline-info status-badge mr"
                        style="font-size: 12px; border-radius: 8px;">
                            View
                    </button>
                    <div class="dropdown">
                        <button class="btn btn-light" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-ellipsis-v"></i>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="#">Edit</a>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<head>
    <style>
        .card-list-imunisasi {
            position: relative;
            max-width: 100%;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 20px;
            padding: 20px;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
            background-color: #f8f9fa;
        }

        .card-list-imunisasi .row {
            align-items: center;
        }

        .card-list-imunisasi p {
            margin-bottom: 0;
            color: #6c757d;
        }

        .card-list-imunisasi h4 {
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

        .btn-create-data-imunisasi {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #EBF8FF;
            border: 1px solid #4DBEFF;
            color: #4DBEFF;
        }

        .btn-create-data-imunisasi:hover {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #4DBEFF;
            border: 1px solid #4DBEFF;
            color: white;
        }

        .btn-create-data-imunisasi:hover .flaticon2-add-1 {
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

        .container-title-status-imunisasi {
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

        .card-list-imunisasi, .container, .row {
            overflow: visible !important;
        }

        .status-badge.sudah {
            background-color: #4CAF50;
            color: white;
         }

         .status-badge.belum {
            background-color: #F44336;
            color: white;
         }
         .mr {
            margin-right: 16px;
        }
        .modal-backdrop {
        background-color: rgba(0, 0, 0, 0.5);
    }
    </style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    $('#imunisasiModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var imunisasiType = button.data('imunisasi');
        var imunisasiColumn = button.data('column');

        var modal = $(this);
        modal.find('#imunisasi_type').val(imunisasiType);
        modal.find('#imunisasi_column').val(imunisasiColumn);
    });
</script>

</head>

