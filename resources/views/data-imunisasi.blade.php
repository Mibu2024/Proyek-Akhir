@extends('layouts.app')

@section('content')
    <!--begin::Body-->

    <body id="kt_body"
        class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed page-loading">
                    <!--begin::Content-->
                    <div class="content d-flex flex-column flex-column-fluid" id="kt_content">
                        <!--begin::Subheader-->
                        <div class="subheader py-2 py-lg-4 subheader-solid" id="kt_subheader">
                            <div
                                class="container-fluid d-flex align-items-center justify-content-between flex-wrap flex-sm-nowrap">
                                <!--begin::Info-->
                                <div class="d-flex align-items-center flex-wrap mr-2">
                                    <!--begin::Page Title-->
                                    <h5 class="text-dark font-weight-bold mt-2 mb-2 mr-5">Data Imunisasi</h5>
                                    <!--end::Page Title-->
                                </div>
                                <!--end::Info-->
                                <!--begin::Toolbar-->
                                <div class="d-flex align-items-center">
                                    <!--begin::Breadcrumb-->
                                    <ul
                                        class="breadcrumb breadcrumb-transparent breadcrumb-dot font-weight-bold p-0 my-2 font-size-sm">
                                        <li class="breadcrumb-item text-muted">
                                            <a href="" class="text-muted">Data imunisasi</a>
                                        </li>
                                    </ul>
                                    <!--end::Breadcrumb-->
                                </div>
                                <!--end::Toolbar-->
                            </div>
                        </div>
                        <!--end::Subheader-->
                            <!--begin::Container-->
                            <div class="container">
                                <div class="card card-custom">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-4">
                                                <form action="{{ route('data-imunisasi.index') }}" method="GET">
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
                                                <a href="{{ route('data-imunisasi.create') }}" type="button" class="btn btn-success">
                                                    <i class="flaticon2-add-1"></i><strong>Data Baru</strong>
                                                </a>
                                                <a href="{{ route('data-imunisasi.download') }}" type="button" class="btn btn-primary ml-2">
                                                    <i class="flaticon2-download"></i><strong>Download Data</strong>
                                                </a>
                                            </div>
                                            
                                        </div>

                                        <div class="row table-responsive">
                                            <table class="table text-center">
                                                <thead>
                                                    <tr>
                                                        <th>No</th>
                                                        <th>Tanggal Periksa</th>
                                                        <th>Nama Anak</th>
                                                        <th>Hepatitis B</th>
                                                        <th>BCG</th>
                                                        <th>Polio Tetes 1</th>
                                                        <th>DPT-HB-Hib 1</th>
                                                        <th>Polio Tetes 2</th>
                                                        <th>Rota Virus 1</th>
                                                        <th>PCV 1</th>
                                                        <th>DPT-HB-Hib 2</th>
                                                        <th>Polio Tetes 3</th>
                                                        <th>Rota Virus 2</th>
                                                        <th>PCV 2</th>
                                                        <th>DPT-HB-Hib 3</th>
                                                        <th>Polio Tetes 4</th>
                                                        <th>Polio Suntik 1</th>
                                                        <th>Rota Virus 3</th>
                                                        <th>Campak Rubella</th>
                                                        <th>Polio Suntik 2</th>
                                                        <th>Japanese Encephalitis</th>
                                                        <th>PCV 3</th>
                                                        <th>DPT-HB-Hib Lanjutan</th>
                                                        <th>Campak Rubella Lanjutan</th>
                                                        <th>Nama Pemeriksa</th>
                                                        <th>Aksi</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    @php
                                                         $num = ($data_imunisasis->currentPage() - 1) * $data_imunisasis->perPage() + 1;
                                                    @endphp
                                                    @forelse ($data_imunisasis as $di)
                                                        <tr>
                                                            <td>{{ $num++ }}</td>
                                                            <td>{{ $di->tanggal }}</td>
                                                            <td>{{ $di->nama_anak }}</td>
                                                            <td>
                                                                @if ($di->hepatitis_b == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                <br>
                                                                <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_hepatitis_b }}</span>
                                                            </td>
                                                            <td>
                                                                @if ($di->bcg == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_bcg)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_bcg }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->polio_tetes_1 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_polio_tetes_1)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_polio_tetes_1 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->dpt_hb_hib_1 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_dpt_hb_hib_1)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_dpt_hb_hib_1 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->polio_tetes_2 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_polio_tetes_2)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_polio_tetes_2 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->rota_virus_1 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_rota_virus_1)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_rota_virus_1 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->pcv_1 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_pcv_1)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_pcv_1 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->dpt_hb_hib_2 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_dpt_hb_hib_2)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_dpt_hb_hib_2 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->polio_tetes_3 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_polio_tetes_3)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_polio_tetes_3 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->rota_virus_2 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_rota_virus_2)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_rota_virus_2 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->pcv_2 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_pcv_2)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_pcv_2 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->dpt_hb_hib_3 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_dpt_hb_hib_3)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_dpt_hb_hib_3 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->polio_tetes_4 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_polio_tetes_4)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_polio_tetes_4 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->polio_suntik_1 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_polio_suntik_1)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_polio_suntik_1 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->rota_virus_3 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_rota_virus_3)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_rota_virus_3 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->campak_rubella == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_campak_rubella)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_campak_rubella }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->polio_suntik_2 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_polio_suntik_2)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_polio_suntik_2 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->japanese_encephalitis == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_japanese_encephalitis)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_japanese_encephalitis }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->pcv_3 == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_pcv_3)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_pcv_3 }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                           <td>
                                                                @if ($di->dpt_hb_hib_lanjutan == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_dpt_hb_hib_lanjutan)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_dpt_hb_hib_lanjutan }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>
                                                                @if ($di->campak_rubella_lanjutan == 'Sudah')
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="green" class="bi bi-check-circle-fill" viewBox="0 0 16 16">
                                                                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0m-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
                                                                        </svg>
                                                                    </span>
                                                                @else
                                                                    <span>
                                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="red" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
                                                                          <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"/>
                                                                        </svg>
                                                                    </span>
                                                                @endif
                                                                @if ($di->tanggal_imunisasi_campak_rubella_lanjutan)
                                                                    <br>
                                                                    <span style="font-size: xx-small;">Di Imunisasi tanggal: </span>
                                                                    <span style="font-weight: bold; font-size: x-small;">{{ $di->tanggal_imunisasi_campak_rubella_lanjutan }}</span>
                                                                @endif
                                                            </td>
                                                            
                                                            <td>{{ $di->nama_pemeriksa }}</td>
                                                            <td>
                                                                <!-- Action buttons -->
                                                                <a href="{{ route('data-imunisasi.edit', $di->id) }}"><i class="flaticon2-edit mr-3"></i></a>
                                                                <a data-toggle="modal" data-target="#deleteModal-{{ $di->id }}"><i class="flaticon2-trash mr-3"></i></a>
                                                                
                                                                <!-- Delete Modal -->
                                                                <div class="modal fade" id="deleteModal-{{ $di->id }}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
                                                                aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="deleteModalLabel">Delete Data Anak</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                Yakin Ingin Menghapus Data Ini?
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                <form id="deleteForm-{{ $di->id }}" action="{{ route('data-imunisasi.delete', $di->id) }}" method="POST">
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
                                                        <td colspan="20" style="text-align: center;">Tidak Ada Data Ditemukan</td>
                                                    </tr>
                                                    @endforelse
                                                </tbody>
                                            </table>

                                            <div class="d-flex justify-content-between align-items-center flex-wrap">
                                                <div class="d-flex align-items-center py-3">
                                                    <div class="d-flex align-items-center">
                                                        <span class="text-muted mr-2">Show</span>
                                                    </div>

                                                    <form method="GET" action="data-imunisasi.index">
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
                                                    {{ $data_imunisasis->links() }}
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
                        "{{ route('data-imunisasi.index') }}?search={{ request('search') }}&per_page=" + $(this)
                        .val();
                });
            });
        </script>
    </body>
    <!--end::Body-->
@endsection
