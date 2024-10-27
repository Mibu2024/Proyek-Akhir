@extends('layouts.app')

@section('content')

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .logout-container {
            position: relative;
            max-width: 100%;
            height: relative;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 10px;
            padding: 10px;
            background-color: white;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
        }

        .profile-container {
            position: relative;
            max-width: 100%;
            height: relative;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 10px;
            padding: 10px;
            background-color: white;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
        }

        .logout-text {
            text-align: center;
            color: black;
        }

        .logout-text h1 {
            font-size: 2rem;
            font-weight: bold;
        }

        .btn-danger {
            width: 90%;
            border-radius: 10px;
            margin-bottom: 10px;
        }

        
    </style>
</head>
<body>
    <div class="content d-flex flex-column flex-column-fluid" id="kt_content">
        <div class="container">
        <div class="title">
                        <h3>Profile Saya</h3>
                    </div>

                    <div class="row">
                        <div class="col-md-4">
                            <!-- Logout Section -->
                            <div class="logout-container text-center">
                                <!-- Profile Image -->
                                <div class="profile-image-container mb-3">
                                    <img src="assets/media/logos/logomibu.png" alt="Profile Image" class="profile-image rounded-circle" style="width: 100px; height: 100px; object-fit: cover;">
                                </div>
                                
                                <!-- Header Text -->
                                <div class="logout-text">
                                    <strong>{{ $user->name }}</strong>
                                </div>
                                
                                <!-- Logout Button -->
                                <div class="logout-button mt-3">
                                    <a href="{{ route('logout') }}" onclick="event.preventDefault(); document.getElementById('logout-form').submit();" class="btn btn-danger">
                                        Logout
                                    </a>
                                    <form id="logout-form" action="{{ route('logout') }}" method="POST" style="display: none;">
                                        @csrf
                                    </form>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-8">
                            <!-- logout Section -->
                            <div class="profile-container">
                                <div class="alamat">
                                    <strong>Alamat</strong>
                                    <p>{{ $user->alamat }}</p>
                                </div>

                                <div class="telepon">
                                    <strong>No Telepon</strong>
                                    <p>{{ $user->no_telepon }}</p>
                                </div>

                                <div class="alamat">
                                    <strong>Email</strong>
                                    <p>{{ $user->email }}</p>
                                </div>

                                <div class="kode-puskesmas">
                                    <strong>Kode Layanan Fasilitas Kesehatan</strong>
                                    <p>{{ $user->kode_yankes }}</p>
                                </div>
                            </div>
                        </div>
                    </div>
        </div>
    </div>
</body>
</html>

@endsection

@push('scripts')
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
@endpush