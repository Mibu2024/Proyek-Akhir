<div class="d-flex justify-content-between align-items-center flex-wrap">
    <div class="d-flex align-items-center py-3">
        <span class="text-muted mr-2">Show</span>
        <form method="GET" action="{{ route('home') }}">
            <select id="entries" class="form-control form-control-sm font-weight-bold mr-4 border-0 bg-light" style="width: 75px;" name="per_page" onchange="this.form.submit()">
                @foreach([5, 10, 20, 30] as $perPage)
                    <option value="{{ $perPage }}" {{ request('per_page') == $perPage ? 'selected' : '' }}>{{ $perPage }}</option>
                @endforeach
            </select>
        </form>
    </div>
    <div id="paginationLinks">
        {{ $data_ibu_hamils->links() }}
    </div>
</div>