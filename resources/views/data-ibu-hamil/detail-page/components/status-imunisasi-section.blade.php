<!-- title status imunisasi -->
<div class="container-title-status-imunisasi">
    <div class="row align-items-center">
       <div class="col-sm-6">
            <h3>Status Imunisasi</h3>
       </div>
       <div class="col-sm-6 d-flex justify-content-end align-items-center">
            <a
                href="#"
                class="btn btn-create-data-imunisasi ml-2 d-flex align-items-center justify-content-center"
                data-toggle="modal"
                data-target="#imunisasiModal"
            >
                <i class="flaticon2-add-1"></i>
                <span>Tambah Imunisasi</span>
            </a>
        </div>
    </div>
</div>

<!-- Modal Tambah Imunisasi -->
<div class="modal fade" id="imunisasiModal" tabindex="-1" aria-labelledby="imunisasiModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="{{ route('imunisasi.store') }}" method="POST">
            @csrf
            <input type="hidden" name="id_anak" value="{{ $anakRecords->id }}">
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
                        <select name="jenis_imunisasi" id="imunisasi_type" class="form-control" required>
                            <option value="" disabled selected>Pilih Jenis Imunisasi</option>
                            @foreach ($jenisImunisasi as $jenis)
                                <option value="{{ $jenis->id }}">{{ $jenis->jenis_imunisasi }}</option>
                            @endforeach
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="tanggal_imunisasi">Tanggal Imunisasi</label>
                        <input type="date" name="tanggal_imunisasi" id="tanggal_imunisasi" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="merek">Merek Imunisasi</label>
                        <input type="text" name="merek" id="merek" class="form-control" required>
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

<!-- card list imunisasi-->
@if ($imunisasiRecords->isEmpty())
    <p style="text-align: center; width: 100%; padding: 30px; border-radius: 8px; margin-top: 20px; box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);">-- No record found --</p>
@else
    @foreach($imunisasiRecords as $imunisasi)
        <div class="card-list-imunisasi mb-3">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-sm-5">
                        <p>Tanggal Imunisasi</p><h4>{{ \Carbon\Carbon::parse($imunisasi->tanggal)->format('d F Y') }}</h4>
                    </div>
                    <div class="col-sm-2">
                        <span class="status-badge">
                            Jenis:
                            <br>
                            {{ $imunisasi->jenisImunisasi->jenis_imunisasi }}
                        </span>
                    </div>
                    <div class="col-sm">
                        <span class="status-badge">
                            Pemeriksa: {{ $imunisasi->nama_pemeriksa }}
                        </span>
                    </div>
                    <div class="col-sm-1 justify-content-end">
                        <button
                            type="button"
                            class="btn btn-outline-info status-badge"
                            data-toggle="modal"
                            data-target="#viewImunisasiModal"
                            data-tanggal="{{ \Carbon\Carbon::parse($imunisasi->tanggal)->format('d F Y') }}"
                            data-jenis="{{ $imunisasi->jenisImunisasi->jenis_imunisasi }}"
                            data-merek="{{ $imunisasi->merek }}"
                            data-pemeriksa="{{ $imunisasi->nama_pemeriksa }}"
                            style="font-size: 12px; border-radius: 8px;">
                                View
                        </button>
                    </div>
                    <div class="dropdown ml-2">
                        <button class="btn btn-light" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-ellipsis-v"></i>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                            <a
                                class="dropdown-item edit-imunisasi"
                                data-id="{{ $imunisasi->id }}"
                                data-jenis="{{ $imunisasi->jenisImunisasi->id }}"
                                data-tanggal="{{ $imunisasi->tanggal }}" data-merek="{{ $imunisasi->merek }}"
                                data-pemeriksa="{{ $imunisasi->nama_pemeriksa }}"
                            >
                                Edit</a>
                            <a class="dropdown-item" data-toggle="modal" data-target="#deleteModal-{{ $imunisasi->id }}">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal View Imunisasi -->
        <div class="modal fade" id="viewImunisasiModal" tabindex="-1" aria-labelledby="viewImunisasiModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="viewImunisasiModalLabel">Detail Imunisasi</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div>
                                    <p><strong>Jenis Imunisasi:</strong> <span id="modalJenisImunisasi"></span></p>
                                    <p><strong>Tanggal Imunisasi:</strong> <span id="modalTanggalImunisasi"></span></p>
                                    <p><strong>Merek Imunisasi:</strong> <span id="modalMerekImunisasi"></span></p>
                                    <p><strong>Nama Pemeriksa:</strong> <span id="modalNamaPemeriksa"></span></p>
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
        <!-- Modal Edit Imunisasi-->
        <div class="modal fade" id="editImunisasiModal" tabindex="-1" aria-labelledby="editImunisasiModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <form action="{{ route('imunisasi.update', 'imunisasi_id_placeholder') }}" method="POST" id="editImunisasiForm">
                    @csrf
                    @method('PUT')
                    <input type="hidden" name="id_anak" value="{{ $anakRecords->id }}"> <!-- ID anak -->
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editImunisasiModalLabel">Edit Imunisasi</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="edit_imunisasi_type">Jenis Imunisasi</label>
                                <select name="jenis_imunisasi" id="edit_imunisasi_type" class="form-control" required>
                                    <option value="" disabled>Pilih Jenis Imunisasi</option>
                                    @foreach ($jenisImunisasi as $jenis)
                                        <option value="{{ $jenis->id }}">{{ $jenis->jenis_imunisasi }}</option>
                                    @endforeach
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="edit_tanggal_imunisasi">Tanggal Imunisasi</label>
                                <input type="date" name="tanggal_imunisasi" id="edit_tanggal_imunisasi" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="edit_merek">Merek Imunisasi</label>
                                <input type="text" name="merek" id="edit_merek" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="edit_nama_pemeriksa">Nama Pemeriksa</label>
                                <input type="text" name="nama_pemeriksa" id="edit_nama_pemeriksa" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success">Update</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Modal Delete Imunisasi -->
        <div class="modal fade" id="deleteModal-{{ $imunisasi->id }}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
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
                        <form action="{{ route('imunisasi.delete', $imunisasi->id) }}" method="POST" style="display:inline;">
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    @endforeach
@endif

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
         .modal-backdrop {
            background-color: rgba(0, 0, 0, 0.5);
         }
    </style>

<script>
    // Event listener untuk view detail imunisasi
    $('#viewImunisasiModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var tanggalImunisasi = button.data('tanggal');
        var jenisImunisasi = button.data('jenis');
        var merekImunisasi = button.data('merek');
        var namaPemeriksa = button.data('pemeriksa');

        var modal = $(this);
        modal.find('#modalTanggalImunisasi').text(tanggalImunisasi);
        modal.find('#modalJenisImunisasi').text(jenisImunisasi);
        modal.find('#modalMerekImunisasi').text(merekImunisasi);
        modal.find('#modalNamaPemeriksa').text(namaPemeriksa);
    });

    // Event listener untuk edit imunisasi
    $('.edit-imunisasi').on('click', function(event) {
        event.preventDefault();
        var imunisasiId = $(this).data('id');
        var imunisasiType = $(this).data('jenis');
        var tanggal = $(this).data('tanggal');
        var merek = $(this).data('merek');
        var namaPemeriksa = $(this).data('pemeriksa');

        $('#editImunisasiForm').attr('action', '{{ url("imunisasi") }}/' + imunisasiId);
        $('#edit_imunisasi_type').val(imunisasiType);
        $('#edit_tanggal_imunisasi').val(tanggal);
        $('#edit_merek').val(merek);
        $('#edit_nama_pemeriksa').val(namaPemeriksa);
        $('#editImunisasiModal').modal('show');
    });
</script>

</head>

