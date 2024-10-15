@extends('layouts.app')

@section('title', 'Home')

@section('content')
    <!--begin::Body-->

    <body id="kt_body"
        class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed page-loading">
                    <!--begin::Content-->
                    <div class="content d-flex flex-column flex-column-fluid" id="kt_content">
                            <!--begin::Container-->
                            <div class="container">
                                <div class="card card-custom">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-4">
                                                <form action="{{ route('data-kesehatan.index') }}" method="GET">
                                                    <div class="form-group">
                                                        <div class="input-icon input-icon-right">
                                                            <input type="text" name="search" value="{{ request('search') }}"
                                                                class="form-control" placeholder="Search..." />
                                                            <span><i class="flaticon2-search-1 icon-md"></i></span>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-3"></div>
                                            <div class="col-5 text-right">
                                                <a href="{{ route('data-artikel.create') }}" type="button" class="btn btn-success"><i
                                                        class="flaticon2-add-1"></i><strong>Data Baru</strong>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="row table-responsive">
                                            <table class="table text-center">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Tanggal Upload</th>
                                                        <th>Judul</th>
                                                        <th>Isi Artikel</th>
                                                        <th>Author</th>
                                                        <th>Foto Artikel</th>
                                                        <th>Aksi</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    @php
                                                         $num = ($data_artikels->currentPage() - 1) * $data_artikels->perPage() + 1;
                                                    @endphp
                                                    @forelse ($data_artikels as $dk)
                                                        <tr>
                                                            <td>{{ $num++ }}</td>
                                                            <td>{{ $dk->tanggal }}</td>
                                                            <td>{{ $dk->judul }}</td>
                                                            <td>{{ $dk->isi }}</td>
                                                            <td>{{ $dk->author }}</td>
                                                            <td>
                                                                <!-- Foto Artikel Column with Eye Icon -->
                                                                @if($dk->foto)
                                                                    <a href="{{ route('data-artikel.view-foto-artikel', $dk->id) }}" target="blank"><i class="flaticon-eye"></i></a>
                                                                @else
                                                                    <span>No Image</span>
                                                                @endif
                                                            </td>
                                                            <td>
                                                                <!-- Action buttons -->
                                                                <a href="{{ route('data-artikel.edit', $dk->id) }}"><i class="flaticon2-edit mr-3"></i></a>
                                                                <a data-toggle="modal" data-target="#deleteModal-{{ $dk->id }}"><i class="flaticon2-trash mr-3"></i></a>
                                                                
                                                                <!-- Delete Modal -->
                                                                <div class="modal fade" id="deleteModal-{{ $dk->id }}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
                                                                aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="deleteModalLabel">Delete Data Artikel</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                Yakin Ingin Menghapus Data Ini?
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                <form id="deleteForm-{{ $dk->id }}" action="{{ route('data-artikel.delete', $dk->id) }}" method="POST">
                                                                                    @csrf
                                                                                    @method('DELETE')
                                                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                                                </form>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    @empty
                                                    <tr>
                                                        <td colspan="15" style="text-align: center;">Tidak Ada Data Ditemukan</td>
                                                    </tr>
                                                    @endforelse
                                                </tbody>
                                            </table>

                                            <div class="d-flex justify-content-between align-items-center flex-wrap">
                                                <div class="d-flex align-items-center py-3">
                                                    <div class="d-flex align-items-center">
                                                        <span class="text-muted mr-2">Show</span>
                                                    </div>

                                                    <form method="GET" action="data-artikel.index">
                                                        <select id="entries"
                                                            class="form-control form-control-sm font-weight-bold mr-4 border-0 bg-light"
                                                            style="width: 75px;" name="per_page" onchange="this.form.submit()">
                                                            <option value="5" {{ request('per_page') == 5 ? 'selected' : '' }}>5</option>
                                                            <option value="10" {{ request('per_page') == 10 ? 'selected' : '' }}>10</option>
                                                            <option value="20" {{ request('per_page') == 20 ? 'selected' : '' }}>20</option>
                                                            <option value="30" {{ request('per_page') == 30 ? 'selected' : '' }}>30</option>
                                                            <!-- Tambahkan lebih banyak opsi jika diperlukan -->
                                                        </select>
                                                    </form>
                                                </div>

                                                <div id="paginationLinks">
                                                    {{ $data_artikels->links() }}
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--end::Container-->
                    </div>
                    <!--end::Content-->
                </div>
                <!--end::Wrapper-->
            </div>
            <!--end::Page-->
        </div>
        <!--end::Main-->
        <!-- begin::User Panel-->
        <div id="kt_quick_user" class="offcanvas offcanvas-right p-10">
            <!--begin::Header-->
            <div class="offcanvas-header d-flex align-items-center justify-content-between pb-5">
                <h3 class="font-weight-bold m-0">User Profile
                </h3>
                <a href="#" class="btn btn-xs btn-icon btn-light btn-hover-primary" id="kt_quick_user_close">
                    <i class="ki ki-close icon-xs text-muted"></i>
                </a>
            </div>
            <!--end::Header-->
            <!--begin::Content-->
            <div class="offcanvas-content pr-5 mr-n5">
                <!--begin::Header-->
                <div class="card card-custom gutter-b bg-success shadow-sm mt-5 p-5 py-5">
                    <div class="d-flex align-items-center mt-5">
                        <div class="symbol symbol-100">
                            {{-- <div class="symbol-label" style="background-image:url({{ asset('assets/media/users/blank.png') }})">
                        </div>
                        <i class="symbol-badge bg-success"></i> --}}
                        </div>
                        <div class="d-flex flex-column">
                            <a href="javascript:void(0)" class="font-weight-bold font-size-h5 text-light">{{ Auth::user()->name }}</a>
                            <div class="navi">
                                <a href="javascript:void(0)" class="navi-item">
                                    <span class="navi-link p-0 pb-2">
                                        <span class="navi-icon mr-1">
                                            <span class="svg-icon svg-icon-lg svg-icon-light">
                                                <!--begin::Svg Icon | path:assets/media/svg/icons/Communication/Mail-notification.svg-->
                                                <svg xmlns="http://www.w3.org/2000/svg"
                                                    xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px"
                                                    viewBox="0 0 24 24" version="1.1">
                                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                                        <rect x="0" y="0" width="24" height="24" />
                                                        <path
                                                            d="M21,12.0829584 C20.6747915,12.0283988 20.3407122,12 20,12 C16.6862915,12 14,14.6862915 14,18 C14,18.3407122 14.0283988,18.6747915 14.0829584,19 L5,19 C3.8954305,19 3,18.1045695 3,17 L3,8 C3,6.8954305 3.8954305,6 5,6 L19,6 C20.1045695,6 21,6.8954305 21,8 L21,12.0829584 Z M18.1444251,7.83964668 L12,11.1481833 L5.85557487,7.83964668 C5.4908718,7.6432681 5.03602525,7.77972206 4.83964668,8.14442513 C4.6432681,8.5091282 4.77972206,8.96397475 5.14442513,9.16035332 L11.6444251,12.6603533 C11.8664074,12.7798822 12.1335926,12.7798822 12.3555749,12.6603533 L18.8555749,9.16035332 C19.2202779,8.96397475 19.3567319,8.5091282 19.1603533,8.14442513 C18.9639747,7.77972206 18.5091282,7.6432681 18.1444251,7.83964668 Z"
                                                            fill="#000000" />
                                                        <circle fill="#000000" opacity="0.3" cx="19.5" cy="17.5"
                                                            r="2.5" />
                                                    </g>
                                                </svg>
                                                <!--end::Svg Icon-->
                                            </span>
                                        </span>

                                        <span class="navi-text text-light text-hover-success">{{ Auth::user()->email }}</span>
                                    </span>
                                </a>
                                <a href="{{ route('logout') }}"
                                    onclick="event.preventDefault(); document.getElementById('logout-form').submit();"
                                    class="btn btn-sm btn-danger font-weight-bolder py-2 px-5 mt-2">Sign
                                    Out</a>
                                <form id="logout-form" method="POST" action="{{ route('logout') }}">
                                    @csrf
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--end::Header-->
            </div>
            <!--end::Content-->
        </div>
        <!-- end::User Panel-->

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $(document).on('change', '#entries', function() {
                window.location =
                    "{{ route('data-artikel.index') }}?search={{ request('search') }}&per_page=" + $(this)
                    .val();
            });
        });
    </script>
    
    </body>
    <!--end::Body-->
@endsection
