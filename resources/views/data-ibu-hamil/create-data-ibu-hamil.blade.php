@extends('layouts.app')

@section('content')
    <!--begin::Body-->

    <body id="kt_body"
        class="header-fixed header-mobile-fixed subheader-enabled subheader-fixed aside-enabled aside-fixed page-loading">
        <!--begin::Main-->
        <div class="d-flex flex-column flex-root">
            <!--begin::Page-->
                <!--begin::Wrapper-->
                <div class="d-flex flex-column flex-row-fluid wrapper" id="kt_wrapper">
                    <!--begin::Content-->
                    <div class="content d-flex flex-column flex-column-fluid" id="kt_content">
                            <!-- Main content -->
                                <div class="container-fluid">
                                    <div class="card">
                                        <div class="card-body">
                                            <a href="{{ route('home') }}">
                                                <i class="flaticon2-back icon-xm text-success"> Kembali</i>
                                            </a>
                                            <h3 class="text-dark font-weight-bold mt-5 "><b>Tambah Data Baru</b></h3>

                                            <form action="{{ route('data-ibu-hamil.store') }}" method="post">
                                                @csrf

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Nama Ibu Hamil</strong></label>
                                                    <input type="text" name="nama_ibu" id="nama_ibu" class="form-control @error('nama_ibu') is-invalid @enderror"
                                                        placeholder="Masukkan Nama Ibu Hamil" value="{{ old('nama_ibu') }}">
                                                    @error('nama_ibu')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Umur Ibu (<span class="text-success">Tahun</span>)</strong></label>
                                                    <input type="text" name="umur_ibu" id="umur_ibu" class="form-control @error('umur_ibu') is-invalid @enderror"
                                                        placeholder="Masukkan Umur Ibu Hamil (Hanya Angka Saja)" value="{{ old('umur_ibu') }}">
                                                    @error('umur_ibu')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Alamat</strong></label>
                                                    <input type="text" name="alamat" id="alamat" class="form-control @error('alamat') is-invalid @enderror"
                                                        placeholder="Masukkan Alamat" value="{{ old('alamat') }}">
                                                    @error('alamat')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>    

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Email</strong></label>
                                                    <input type="email" name="email" id="email" class="form-control @error('email') is-invalid @enderror"
                                                        placeholder="Masukkan Email" value="{{ old('email') }}">
                                                    @error('email')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>NIK</strong></label>
                                                    <input type="text" name="nik" id="nik" class="form-control @error('nik') is-invalid @enderror"
                                                        placeholder="Masukkan NIK / Nomor Induk Kependudukan (Hanya Angka Saja)" value="{{ old('nik') }}">
                                                    @error('nik')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Nomor Telepon</strong></label>
                                                    <input type="text" name="no_telepon" id="no_telepon" class="form-control @error('no_telepon') is-invalid @enderror"
                                                        placeholder="Masukkan Nomor Telepon (Hanya Angka Saja)" value="{{ old('no_telepon') }}">
                                                    @error('no_telepon')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Kehamilan Ke</strong></label>
                                                    <input type="text" name="kehamilan_ke" id="kehamilan_ke" class="form-control @error('kehamilan_ke') is-invalid @enderror"
                                                        placeholder="Masukkan Kehamilan Ke Berapa (Hanya Angka Saja)" value="{{ old('kehamilan_ke') }}">
                                                    @error('kehamilan_ke')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Nama Suami</strong></label>
                                                    <input type="text" name="nama_suami" id="nama_suami" class="form-control @error('nama_suami') is-invalid @enderror"
                                                        placeholder="Masukkan Nama Suami" value="{{ old('nama_suami') }}">
                                                    @error('nama_suami')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Umur Suami (<span class="text-success">Tahun</span>)</strong></label>
                                                    <input type="text" name="umur_suami" id="umur_suami" class="form-control @error('umur_suami') is-invalid @enderror"
                                                        placeholder="Masukkan Umur Suami (Hanya Angka Saja)" value="{{ old('umur_suami') }}">
                                                    @error('umur_suami')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>No JKN Faskes Tk 1 </strong></label>
                                                    <input type="text" name="no_jkn_faskes_tk_1" id="no_jkn_faskes_tk_1" class="form-control @error('no_jkn_faskes_tk_1') is-invalid @enderror"
                                                        placeholder="Masukkan No JKN Faskes Tk 1" value="{{ old('no_jkn_faskes_tk_1') }}">
                                                    @error('no_jkn_faskes_tk_1')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>No JKN Rujukan </strong></label>
                                                    <input type="text" name="no_jkn_rujukan" id="no_jkn_rujukan" class="form-control @error('no_jkn_rujukan') is-invalid @enderror"
                                                        placeholder="Masukkan No JKN Rujukan" value="{{ old('no_jkn_rujukan') }}">
                                                    @error('no_jkn_rujukan')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Golongan Darah </strong></label>
                                                    <input type="text" name="gol_darah" id="gol_darah" class="form-control @error('gol_darah') is-invalid @enderror"
                                                        placeholder="Masukkan Golongan Darah" value="{{ old('gol_darah') }}">
                                                    @error('gol_darah')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Pekerjaan </strong></label>
                                                    <input type="text" name="pekerjaan" id="pekerjaan" class="form-control @error('pekerjaan') is-invalid @enderror"
                                                        placeholder="Masukkan Pekerjaan" value="{{ old('pekerjaan') }}">
                                                    @error('pekerjaan')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>

                                                <div class="form-group mt-5">
                                                    <label for=""><strong>Password </strong></label>
                                                    <input type="password" name="password" id="password" class="form-control @error('password') is-invalid @enderror"
                                                        placeholder="Password" value="{{ old('password') }}">
                                                    @error('umur_suami')
                                                        <span class="invalid-feedback" role="alert">
                                                            <strong>{{ $message }}</strong>
                                                        </span>
                                                    @enderror
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="user_id"><strong>Pilih Puskesmas</strong></label>
                                                    <select name="user_id" id="user_id" class="form-control @error('user_id') is-invalid @enderror">
                                                        <option value="">Pilih Puskesmas</option>
                                                        @foreach($users as $user)
                                                            <option value="{{ $user->id }}">{{ $user->name }}</option>
                                                        @endforeach
                                                    </select>
                                                    @error('user_id')
                                                        <div class="invalid-feedback">{{ $message }}</div>
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
        @include('sweetalert::alert')
    </body>
    <!--end::Body-->
@endsection
