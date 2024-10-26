@extends('layouts.app')

@section('content')

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

<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
    <div class="container">
        <div class="card card-custom">
            <div class="card-body">
                @include('data-catatan-nifas.components.search_and_actions')
                
                <div class="table-responsive">
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th><b>No</b></th>
                                <th><b>Tanggal Periksa</b></th>
                                <th><b>Nama Ibu</b></th>
                                <th><b>Masalah</b></th>
                                <th><b>Tindakan</b></th>
                                <th><b>#</b></th>
                                <th><b>Aksi</b></th>
                            </tr>
                        </thead>
                        <tbody>
                            @forelse ($data_nifas as $index => $dn)
                                <tr>
                                    <td>{{ $index + 1 + ($data_nifas->currentPage() - 1) * $data_nifas->perPage() }}</td>
                                    <td>{{ $dn->tanggal }}</td>
                                    <td>{{ $dn->nama_ibu }}</td>
                                    <td>{{ $dn->masalah }}</td>
                                    <td>{{ $dn->tindakan }}</td>
                                    <td><button data-toggle="modal"  data-target="#nifasRecordModal"  onclick="setNifasData({{ json_encode($dn) }})" class="btn btn-m btn-primary view-detail-btn" style="background-color: #E7FFEA; color: #45A350; outline: none; box-shadow: none; border: 1px solid transparent;" data-id="{{ $dn->id }}" data-toggle="modal"><b>Lihat Detail</b></button>
                                    </td>
                                    <td>
                                        @include('data-catatan-nifas.components.action_buttons', ['dn' => $dn])
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
                
                @include('data-catatan-nifas.components.pagination')
            </div>
        </div>
    </div>
</div>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush