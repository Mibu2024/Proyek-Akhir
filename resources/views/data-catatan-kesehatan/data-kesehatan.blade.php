@extends('layouts.app')

@section('content')

<!-- Modal -->
<div class="modal fade" id="healthRecordModal" tabindex="-1" aria-labelledby="healthRecordModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="healthRecordModalLabel">Health Record Details</h5>
      </div>
      <div class="modal-body">
        <div class="container">
          <div class="row">
            <!-- First Column -->
            <div class="col-md-6">
              <p><strong>Tanggal:</strong> <span id="modalTanggal"></span></p>
              <p><strong>Nama Ibu:</strong> <span id="modalNamaIbu"></span></p>
              <p><strong>Keluhan:</strong> <span id="modalKeluhan"></span></p>
              <p><strong>Tekanan Darah:</strong> <span id="modalTekananDarah"></span></p>
              <p><strong>Berat Badan:</strong> <span id="modalBeratBadan"></span></p>
              <p><strong>Umur Kehamilan:</strong> <span id="modalUmurKehamilan"></span></p>
              <p><strong>Tinggi Fundus:</strong> <span id="modalTinggiFundus"></span></p>
              <p><strong>Letak Janin:</strong> <span id="modalLetakJanin"></span></p>
              <p><strong>Denyut Jantung Janin:</strong> <span id="modalDenyutJanin"></span></p>
            </div>
            <!-- Second Column -->
            <div class="col-md-6">
              <p><strong>Hasil Lab:</strong> <span id="modalHasilLab"></span></p>
              <p><strong>Tindakan:</strong> <span id="modalTindakan"></span></p>
              <p><strong>Kaki Bengkak:</strong> <span id="modalKakiBengkak"></span></p>
              <p><strong>Nasihat:</strong> <span id="modalNasihat"></span></p>
              <p><strong>Nama Pemeriksa:</strong> <span id="modalNamaPemeriksa"></span></p>
              <p><strong>Tinggi Badan:</strong> <span id="modalTinggiBadan"></span></p>
              <p><strong>Lingkar Perut:</strong> <span id="modalLingkarPerut"></span></p>
              <p><strong>Lingkar Lengan Atas:</strong> <span id="modalLingkarLengan"></span></p>
              <p>
                <strong>Foto USG:</strong>
                <a href="" id="modalFotoUSG" target="_blank">
                  <i class="fa fa-eye ml-1"></i>
                </a>
              </p>
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

<!-- modal script -->
<script>
    function setHealthData(record) {
    document.getElementById('modalTanggal').textContent = record.tanggal;
    document.getElementById('modalNamaIbu').textContent = record.nama_ibu;
    document.getElementById('modalKeluhan').textContent = record.keluhan;
    document.getElementById('modalTekananDarah').textContent = record.tekanan_darah;
    document.getElementById('modalBeratBadan').textContent = record.berat_badan;
    document.getElementById('modalUmurKehamilan').textContent = record.umur_kehamilan;
    document.getElementById('modalTinggiFundus').textContent = record.tinggi_fundus;
    document.getElementById('modalLetakJanin').textContent = record.letak_janin;
    document.getElementById('modalDenyutJanin').textContent = record.denyut_jantung_janin;
    document.getElementById('modalHasilLab').textContent = record.hasil_lab;
    document.getElementById('modalTindakan').textContent = record.tindakan;
    document.getElementById('modalKakiBengkak').textContent = record.kaki_bengkak;
    document.getElementById('modalNasihat').textContent = record.nasihat;
    document.getElementById('modalNamaPemeriksa').textContent = record.nama_pemeriksa;
    document.getElementById('modalTinggiBadan').textContent = record.tinggi_badan;
    document.getElementById('modalLingkarPerut').textContent = record.lingkar_perut;
    document.getElementById('modalLingkarLengan').textContent = record.lingkar_lengan_atas;
    if (record.foto_usg) {
        document.getElementById('modalFotoUSG').href = `/data-kesehatan/view-foto-usg/${record.id}`;
    } else {
        document.getElementById('modalFotoUSG').style.display = 'none'; // Hide if no image
    }
}
</script>

<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
    <div class="container">
        <div class="card card-custom">
            <div class="card-body">
                @include('data-catatan-kesehatan.components.search_and_actions')
                
                <div class="table-responsive">
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th><b>No</b></th>
                                <th><b>Tanggal Periksa</b></th>
                                <th><b>Nama Ibu</b></th>
                                <th><b>Nama Pemeriksa</b></th>
                                <th><b>Keluhan</b></th>
                                <th><b>#</b></th>
                                <th><b>Aksi</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            @forelse ($data_kesehatans as $index => $dk)
                                <tr>
                                    <td>{{ $index + 1 + ($data_kesehatans->currentPage() - 1) * $data_kesehatans->perPage() }}</td>
                                    <td>{{ $dk->tanggal }}</td>
                                    <td>{{ $dk->nama_ibu }}</td>
                                    <td>{{ $dk->nama_pemeriksa }}</td>
                                    <td>{{ $dk->keluhan }}</td>
                                    <td>
                                        <button 
                                        data-toggle="modal" 
                                        data-target="#healthRecordModal" 
                                        type="button" 
                                        style="background-color: #E7FFEA; color: #45A350; outline: none; box-shadow: none; border: 1px solid transparent;"
                                        class="btn btn-m btn-primary view-detail-btn"
                                        onclick="setHealthData({{ json_encode($dk) }})"
                                        >
                                            View
                                        </button>
                                    </td>
                                    <td>
                                        @include('data-catatan-kesehatan.components.action_buttons', ['dk' => $dk])
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
                
                @include('data-catatan-kesehatan.components.pagination')
            </div>
        </div>
    </div>
</div>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush