@extends('layouts.app')

@section('content')
    <!--begin::Body-->

    <body id="kt_body"
        class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed page-loading">
        <!--begin::Main-->
        <!--begin::Header Mobile-->
        <div id="kt_header_mobile" class="header-mobile align-items-center header-mobile-fixed">
            <!--begin::Logo-->
            <a href="index.html">
                <img alt="Logo" class="w-45px" src="assets/media/logos/Logo_Mibu.png" />
            </a>
            <!--end::Logo-->
            <!--begin::Toolbar-->
            <div class="d-flex align-items-center">
                <!--begin::Aside Mobile Toggle-->
                <button class="btn p-0 burger-icon burger-icon-left" id="kt_aside_mobile_toggle">
                    <span></span>
                </button>
                <!--end::Aside Mobile Toggle-->
                <!--begin::Header Menu Mobile Toggle-->
                <button class="btn p-0 burger-icon ml-4" id="kt_header_mobile_toggle">
                    <span></span>
                </button>
                <!--end::Header Menu Mobile Toggle-->
                <!--begin::Topbar Mobile Toggle-->
                <button class="btn btn-hover-text-primary p-0 ml-2" id="kt_header_mobile_topbar_toggle">
                    <span class="svg-icon svg-icon-xl">
                        <!--begin::Svg Icon | path:assets/media/svg/icons/General/User.svg-->
                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px"
                            height="24px" viewBox="0 0 24 24" version="1.1">
                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                <polygon points="0 0 24 0 24 24 0 24" />
                                <path
                                    d="M12,11 C9.790861,11 8,9.209139 8,7 C8,4.790861 9.790861,3 12,3 C14.209139,3 16,4.790861 16,7 C16,9.209139 14.209139,11 12,11 Z"
                                    fill="#000000" fill-rule="nonzero" opacity="0.3" />
                                <path
                                    d="M3.00065168,20.1992055 C3.38825852,15.4265159 7.26191235,13 11.9833413,13 C16.7712164,13 20.7048837,15.2931929 20.9979143,20.2 C21.0095879,20.3954741 20.9979143,21 20.2466999,21 C16.541124,21 11.0347247,21 3.72750223,21 C3.47671215,21 2.97953825,20.45918 3.00065168,20.1992055 Z"
                                    fill="#000000" fill-rule="nonzero" />
                            </g>
                        </svg>
                        <!--end::Svg Icon-->
                    </span>
                </button>
                <!--end::Topbar Mobile Toggle-->
            </div>
            <!--end::Toolbar-->
        </div>
        <!--end::Header Mobile-->
        <div class="d-flex flex-column flex-root">
            <!--begin::Page-->
            <div class="d-flex flex-row flex-column-fluid page">
                <!--begin::Aside-->
                <div class="aside aside-left aside-fixed d-flex flex-column flex-row-auto" id="kt_aside">
                    <!--begin::Brand-->
                    <div class="brand flex-column-auto" id="kt_brand">
                        <!--begin::Logo-->
                        <a href="{{ route('home') }}" class="brand-logo">
                            <img alt="Logo" class="w-65px" src="assets/media/logos/logowithbg.png" />
                        </a>
                        <!--end::Logo-->
                    </div>
                    <!--end::Brand-->
                    <!--begin::Aside Menu-->
                    <div class="aside-menu-wrapper flex-column-fluid" id="kt_aside_menu_wrapper">
                        <!--begin::Menu Container-->
                        <div id="kt_aside_menu" class="aside-menu my-4" data-menu-vertical="1" data-menu-scroll="1"
                            data-menu-dropdown-timeout="500">
                            <!--begin::Menu Nav-->
                            <ul class="menu-nav">
                                <li class="menu-item menu-item-active" aria-haspopup="true">
                                    <a href="{{ route('home') }}" class="menu-link">
                                        <i class="menu-icon flaticon2-user"></i>
                                        <span class="menu-text">Data Ibu Hamil</span>
                                    </a>
                                </li>
                                <li class="menu-item menu-item" aria-haspopup="true">
                                    <a href="{{ route('data-kesehatan.index') }}" class="menu-link">
                                        <i
                                            class="menu-icon 
                                            fas fa-notes-medical"></i>
                                        <span class="menu-text">Data Kesehatan</span>
                                    </a>
                                </li>
                                <li class="menu-item menu-item" aria-haspopup="true">
                                    <a href="{{ route('data-layanan-kb.index') }}" class="menu-link" style="display: flex; align-items: center; justify-content: center; text-align: center;">
                                        <i class="menu-icon fas fa-notes-medical"></i>
                                        <span class="menu-text">Data Layanan KB</span>
                                    </a>
                                </li>
                                <li class="menu-item menu-item" aria-haspopup="true">
                                    <a href="{{ route('data-nifas.index') }}" class="menu-link">
                                        <i class="menu-icon 
                                        fas fa-hospital-user"></i>
                                        <span class="menu-text">Data Nifas</span>
                                    </a>
                                </li>
                                <li class="menu-item menu-item" aria-haspopup="true">
                                    <a href="{{ route('data-anak.index') }}" class="menu-link">
                                        <i class="menu-icon fas fa-child"></i>
                                        <span class="menu-text">Data Anak</span>
                                    </a>
                                </li>
                                <li class="menu-item menu-item" aria-haspopup="true">
                                    <a href="{{ route('data-imunisasi.index') }}" class="menu-link">
                                        <i class="menu-icon flaticon2-hospital"></i>
                                        <span class="menu-text">Data Imunisasi</span>
                                    </a>
                                </li>
                                <li class="menu-item menu-item" aria-haspopup="true">
                                    <a href="{{ route('data-artikel.index') }}" class="menu-link">
                                        <i class="menu-icon fas fa-newspaper"></i>
                                        <span class="menu-text">Artikel</span>
                                    </a>
                                </li>
                            </ul>
                            <!--end::Menu Nav-->
                        </div>
                        <!--end::Menu Container-->
                    </div>
                    <!--end::Aside Menu-->
                </div>
                <!--end::Aside-->
                <!--begin::Wrapper-->
                <div class="d-flex flex-column flex-row-fluid wrapper" id="kt_wrapper">
                    <!--begin::Header-->
                    <div id="kt_header" class="header header-fixed">
                        <!--begin::Container-->
                        <div class="container-fluid d-flex align-items-stretch justify-content-between">
                            <!--begin::Header Menu Wrapper-->
                            <div class="header-menu-wrapper header-menu-wrapper-left" id="kt_header_menu_wrapper">
                                <!--begin::Header Menu-->
                                <div id="kt_header_menu"
                                    class="header-menu header-menu-mobile header-menu-layout-default">
                                    <!--begin::Header Nav-->
                                    <ul class="menu-nav">
                                        <li class="menu-item menu-item-active" aria-haspopup="true">
                                            <!--begin::Daterange-->
                                            <div class="btn btn-sm btn-light font-weight-bold mr-2">
                                                <span class="text-muted font-size-base font-weight-bold mr-2">Hari
                                                    Ini</span>
                                                <span id="current-date"
                                                    class="font-size-base font-weight-bolder" style="color: #0BB783;"></span>
                                            </div>
                                            <div class="btn btn-sm btn-light font-weight-bold mr-2">
                                                <span class="text-muted font-size-base font-weight-bold mr-2">Jam</span>
                                                <span id="current-time"
                                                    class="font-size-base font-weight-bolder" style="color: #0BB783;" ></span>
                                            </div>
                                            <!--end::Daterange-->
                                        </li>
                                    </ul>
                                    <!--end::Header Nav-->
                                </div>
                                <!--end::Header Menu-->
                            </div>
                            <!--end::Header Menu Wrapper-->
                            <!--begin::Topbar-->
                            <div class="topbar">
                                <!--begin::User-->
                                <div class="topbar-item">
                                    <div class="btn btn-icon w-auto btn-clean d-flex align-items-center btn-lg px-2"
                                        id="kt_quick_user_toggle">
                                        <span
                                            class="text-muted font-weight-bold font-size-base d-none d-md-inline mr-1">Hi,</span>
                                        <span
                                            class="text-dark-50 font-weight-bolder font-size-base d-none d-md-inline mr-3">{{ Auth::user()->name }}</span>
                                        <span class="symbol symbol-35 symbol-success">
                                            <span class="symbol-label font-size-h5 font-weight-bold">{{ substr(Auth::user()->name, 0, 1) }}</span>
                                        </span>
                                    </div>
                                </div>
                                <!--end::User-->
                            </div>
                            <!--end::Topbar-->
                        </div>
                        <!--end::Container-->
                    </div>
                    <!--end::Header-->
                    <!--begin::Content-->
                    <div class="content d-flex flex-column flex-column-fluid" id="kt_content">
                        <!--begin::Subheader-->
                        <div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
                            <div
                                class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
                                <!--begin::Info-->
                                <div class="d-flex align-items-center flex-wrap mr-2">
                                    <!--begin::Page Title-->
                                    <h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Data Ibu Hamil</h5>
                                    <!--end::Page Title-->
                                </div>
                                <!--end::Info-->
                                <!--begin::Toolbar-->
                                <div class="d-flex align-items-center">
                                    <!--begin::Breadcrumb-->
                                    <ul
                                        class="breadcrumb breadcrumb-transparent breadcrumb-dot font-weight-bold p-0 my-2 font-size-sm">
                                        <li class="breadcrumb-item text-muted">
                                            <a href="{{ route('home') }}" class="text-muted">Data Ibu Hamil</a>
                                        </li>
                                        <li class="breadcrumb-item text-muted">
                                            <a href="javascript:void(0)" class="text-muted">Edit Data</a>
                                        </li>
                                    </ul>
                                    <!--end::Breadcrumb-->
                                </div>
                                <!--end::Toolbar-->
                            </div>
                        </div>
                        <!--end::Subheader-->
                            <!-- Main content -->
                                <div class="container-fluid">
                                    <div class="card">
                                        <div class="card-body">
                                            <a href="{{ route('home') }}">
                                                <i class="flaticon2-back icon-xm text-success"> Kembali</i>
                                            </a>
                                            <h3 class="text-dark font-weight-bold mt-5 "><b>Edit Data Ibu Hamil</b></h3>

                                            <form action="{{ route('data-ibu-hamil.update', $data_ibu_hamils->id) }}" method="POST">
                                                @csrf
                                                @method('PUT')
                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Nama Ibu Hamil</strong></label>
                                                    <input type="text" name="nama_ibu" id="nama_ibu" class="form-control @error('nama_ibu') is-invalid @enderror" value="{{ $data_ibu_hamils->nama_ibu }}"
                                                        placeholder="Masukkan Nama Ibu Hamil">
                                                    @error('nama_ibu')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Umur Ibu (<span class="text-success">Tahun</span>)</strong></label>
                                                    <input type="text" name="umur_ibu" id="umur_ibu" class="form-control @error('umur_ibu') is-invalid @enderror" value="{{ $data_ibu_hamils->umur_ibu }}"
                                                        placeholder="Masukkan Umur Ibu Hamil (Hanya Angka Saja)">
                                                    @error('umur_ibu')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Alamat</strong></label>
                                                    <input type="text" name="alamat" id="alamat" class="form-control @error('alamat') is-invalid @enderror" value="{{ $data_ibu_hamils->alamat }}"
                                                        placeholder="Masukkan Alamat">
                                                    @error('alamat')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>    

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Email</strong></label>
                                                    <input type="email" name="email" id="email" class="form-control @error('email') is-invalid @enderror" value="{{ $data_ibu_hamils->email }}"
                                                        placeholder="Masukkan Email">
                                                    @error('email')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>NIK</strong></label>
                                                    <input type="text" name="nik" id="nik" class="form-control @error('nik') is-invalid @enderror" value="{{ $data_ibu_hamils->nik }}"
                                                        placeholder="Masukkan NIK / Nomor Induk Kependudukan (Hanya Angka Saja)">
                                                    @error('nik')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Nomor Telepon</strong></label>
                                                    <input type="text" name="no_telepon" id="no_telepon" class="form-control @error('no_telepon') is-invalid @enderror" value="{{ $data_ibu_hamils->no_telepon }}"
                                                        placeholder="Masukkan Nomor Telepon (Hanya Angka Saja)">
                                                    @error('no_telepon')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Kehamilan Ke</strong></label>
                                                    <input type="text" name="kehamilan_ke" id="kehamilan_ke" class="form-control @error('kehamilan_ke') is-invalid @enderror" value="{{ $data_ibu_hamils->kehamilan_ke }}"
                                                        placeholder="Masukkan Kehamilan Ke Berapa (Hanya Angka Saja)">
                                                    @error('kehamilan_ke')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Nama Suami</strong></label>
                                                    <input type="text" name="nama_suami" id="nama_suami" class="form-control @error('nama_suami') is-invalid @enderror" value="{{ $data_ibu_hamils->nama_suami }}"
                                                        placeholder="Masukkan Nama Suami">
                                                    @error('nama_suami')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Umur Suami (<span class="text-success">Tahun</span>)</strong></label>
                                                    <input type="text" name="umur_suami" id="umur_suami" class="form-control @error('umur_suami') is-invalid @enderror" value="{{ $data_ibu_hamils->umur_suami }}"
                                                        placeholder="Masukkan Umur Suami (Hanya Angka Saja)">
                                                    @error('umur_suami')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>No JKN Faskes Tk 1 </strong></label>
                                                    <input type="text" name="no_jkn_faskes_tk_1" id="no_jkn_faskes_tk_1" class="form-control @error('no_jkn_faskes_tk_1') is-invalid @enderror" value="{{ $data_ibu_hamils->no_jkn_faskes_tk_1 }}"
                                                        placeholder="Masukkan No JKN Faskes Tk 1" value="{{ old('no_jkn_faskes_tk_1') }}">
                                                    @error('no_jkn_faskes_tk_1')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>No JKN Rujukan </strong></label>
                                                    <input type="text" name="no_jkn_rujukan" id="no_jkn_rujukan" class="form-control @error('no_jkn_rujukan') is-invalid @enderror" value="{{ $data_ibu_hamils->no_jkn_rujukan }}"
                                                        placeholder="Masukkan No JKN Rujukan" value="{{ old('no_jkn_rujukan') }}">
                                                    @error('no_jkn_rujukan')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Golongan Darah </strong></label>
                                                    <input type="text" name="gol_darah" id="gol_darah" class="form-control @error('gol_darah') is-invalid @enderror" value="{{ $data_ibu_hamils->gol_darah}}"
                                                        placeholder="Masukkan Golongan Darah" value="{{ old('gol_darah') }}">
                                                    @error('gol_darah')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Pekerjaan </strong></label>
                                                    <input type="text" name="pekerjaan" id="pekerjaan" class="form-control @error('pekerjaan') is-invalid @enderror" value="{{ $data_ibu_hamils->pekerjaan }}"
                                                        placeholder="Masukkan Pekerjaan" value="{{ old('pekerjaan') }}">
                                                    @error('pekerjaan')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>
                                                
                                                <div class="form-group mt-5">
                                                    <label for="tanggal_hpl"><strong>Tanggal HPL (Optional)</strong></label>
                                                    <input type="date" name="tanggal_hpl" id="tanggal_hpl" class="form-control @error('tanggal_hpl') is-invalid @enderror" value="{{ $data_ibu_hamils->tanggal_hpl }}"
                                                        placeholder="Pilih HPL (Optional)">
                                                    @error('tanggal_hpl')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="text-right">
                                                    <a href="{{ route('home') }}" class="btn btn-outline-danger mr-2"
                                                        role="button">Batal</a>
                                                    <button type="submit" class="btn btn-success">Simpan</button>
                                                </div>
                                            </form>

                                        </div>
                                    </div>

                                </div><!-- /.container-fluid -->
                            <!--end::content-->
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
        @include('sweetalert::alert')
    </body>
    <!--end::Body-->
@endsection
