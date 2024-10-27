@extends('layouts.app')

@section('content')

<!-- Modal -->
<div class="modal fade" id="kbRecordModal" tabindex="-1" aria-labelledby="healthRecordModalLabel" aria-hidden="true">
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
              <p><strong>Tanggal Praktik:</strong> <span id="modalTanggalKb"></span></p>
              <p><strong>Nama Ibu:</strong> <span id="modalNamaIbuKb"></span></p>
              <p><strong>Tekanan Darah:</strong> <span id="modalTekananDarahKb"></span></p>
              <p><strong>Berat Badan:</strong> <span id="modalBeratBadanKb"></span></p>
            </div>
            <!-- Second Column -->
            <div class="col-md-6">
              <p><strong>Jenis KB:</strong> <span id="modalJenisKb"></span></p>
              <p><strong>Tanggal Kembali:</strong> <span id="modalTanggalKembaliKb"></span></p>
              <p><strong>Keluhan:</strong> <span id="modalKeluhanKb"></span></p>
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
    document.getElementById('modalTanggalKb').textContent = record.tanggal_praktik;
    document.getElementById('modalNamaIbuKb').textContent = record.nama_ibu;
    document.getElementById('modalKeluhanKb').textContent = record.keluhan;
    document.getElementById('modalTekananDarahKb').textContent = record.tekanan_darah;
    document.getElementById('modalBeratBadanKb').textContent = record.berat_badan;
    document.getElementById('modalJenisKb').textContent = record.jenis_kb;
    document.getElementById('modalTanggalKembaliKb').textContent = record.tanggal_kembali;
}
</script>

<div class="content d-flex flex-column flex-column-fluid" id="kt_content">
    <div class="container">
        <div class="card card-custom">
            <div class="card-body">
                @include('data-layanan-kb.components.search_and_actions')
                
                <div class="table-responsive">
                    <table class="table text-center">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Nama Ibu</th>
                                <th>Tanggal Kunjungan</th>
                                <th>Tekanan Darah (mmHg)</th>
                                <th>Berat Badan (Kg)</th>
                                <th>Jenis KB</th>
                                <th>Tanggal Kembali</th>
                                <th>Keluhan</th>
                            </tr>
                        </thead>
                        <tbody>
                            @forelse ($data_layanan_kbs as $index => $dlk)
                                <tr>
                                    <td>{{ $index + 1 + ($data_layanan_kbs->currentPage() - 1) * $data_layanan_kbs->perPage() }}</td>
                                    <td>{{ $dlk->nama_ibu }}</td>
                                    <td>{{ $dlk->tanggal_praktik }}</td>
                                    <td>{{ $dlk->tekanan_darah }}</td>
                                    <td>{{ $dlk->berat_badan }}</td>
                                    <td>{{ $dlk->jenis_kb }}</td>
                                    <td>{{ $dlk->tanggal_kembali }}</td>
                                    <td>{{ $dlk->keluhan }}</td>
                                    <td>
                                        <button 
                                            class="btn btn-m btn-primary view-detail-btn" 
                                            data-toggle="modal" 
                                            data-target="#kbRecordModal" 
                                            style="background-color: #E7FFEA; color: #45A350; outline: none; box-shadow: none; border: 1px solid transparent;" 
                                            data-id="{{ $dlk->id }}" 
                                            onclick="setHealthData({{ json_encode($dlk) }})"
                                            data-toggle="modal">
                                            <b>Lihat Detail</b>
                                        </button>
                                    </td>
                                    <td>
                                        @include('data-layanan-kb.components.action_buttons', ['dlk' => $dlk])
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
                
                @include('data-layanan-kb.components.pagination')
            </div>
        </div>
    </div>
</div>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush