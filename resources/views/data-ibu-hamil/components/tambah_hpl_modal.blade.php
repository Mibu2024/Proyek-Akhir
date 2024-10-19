<div class="modal fade" id="tambahHplModal" tabindex="-1" role="dialog" aria-labelledby="tambahHplModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form id="tambahHplForm" method="POST" action="{{ route('data-ibu-hamil.upload-hpl') }}">
                @csrf
                <div class="modal-header">
                    <h5 class="modal-title" id="tambahHplModalLabel">Tambah Tanggal HPL</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="id" id="ibuHamilId" value="">
                    <div class="form-group">
                        <label for="tanggalHpl">Tanggal HPL</label>
                        <input type="date" class="form-control" id="tanggalHpl" name="tanggal_hpl" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>