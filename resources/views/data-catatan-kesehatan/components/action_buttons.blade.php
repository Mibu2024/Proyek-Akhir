<a href="{{ route('data-kesehatan.edit', $dk->id) }}" class="btn btn-sm btn-icon btn-light btn-hover-primary mr-2" title="Edit">
    <i class="flaticon2-edit"></i>
</a>
<a href="#" class="btn btn-sm btn-icon btn-light btn-hover-danger" title="Delete" data-toggle="modal" data-target="#deleteModal-{{ $dk->id }}">
    <i class="flaticon2-trash"></i>
</a>

<!-- Delete Modal -->
<div class="modal fade" id="deleteModal-{{ $dk->id }}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Delete Data Kesehatan</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Yakin Ingin Menghapus Data Ini?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <form id="deleteForm-{{ $dk->id }}" action="{{ route('data-kesehatan.delete', $dk->id) }}" method="POST">
                    @csrf
                    @method('DELETE')
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>