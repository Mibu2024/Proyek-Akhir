<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sidebar Example</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
        }

        /* Sidebar styles */
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed;
            left: 0;
            top: 0;
            background-color: #fff;
            border-right: 1px solid #eee;
            display: flex;
            flex-direction: column;
            padding-top: 20px;
            z-index: 1000;
            transition: transform 0.3s ease; /* Smooth transition */
        }

        .sidebar img {
            border-radius: 50%;
            display: block;
            margin: 0 auto 10px;
        }

        .row {
            
        }

        .sidebar .nav-link {
            display: grid;
            grid-template-columns: 30px 1fr;
            align-items: center;
            color: #6c757d;
            font-weight: 500;
            padding-top: 20px;
            font-size: 14px;
        }

        .sidebar .nav-link:hover {
            background-color: #f8f9fa;
            border-left: 3px solid #007bff;
            color: #007bff;
        }

        .sidebar .nav-link.active {
            background-color: #eaf4ff;
            border-left: 3px solid #007bff;
            color: #007bff;
        }

        .content {
            margin-left: 250px;
            padding: 20px;
            flex: 1;
        }

        /* Toggle button styles */
        .toggle-btn {
            position: fixed;
            left: 10px;
            top: 10px;
            z-index: 1100;
            display: none; /* Hidden on large screens */
            font-size: 24px;
            cursor: pointer;
        }

        .img-fluid {
            width: 50px;
            height: 50px;
        }

        /* Responsive styles */
        @media (max-width: 768px) {
            .sidebar {
                transform: translateX(-100%); /* Hide sidebar off-screen */
            }

            .sidebar.active {
                transform: translateX(0); /* Show sidebar */
            }

            .content {
                margin-left: 0; /* Reset margin */
            }

            .toggle-btn {
                display: block; /* Show the toggle button */
            }
        }

        .sidebar-divider {
            margin: 0;
            border-top: 1px solid rgba(0,0,0,.1);
        }

        .profile-details {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: calc(100% - 55px); /* Ensures text doesn't overflow sidebar */
        }

        .profile-details h6 {
            font-size: 14px;
        }

        .profile-details small {
            font-size: 12px;
        }

        .profile-info {
            color: #6c757d;
            font-weight: 500;
            font-size: 14px;
            padding-top: 20px;
            display: grid;
            grid-template-columns: 40px 1fr;
            align-items: center;
            transition: background-color 0.3s ease;
        }

        .profile-info:hover {
            background-color: #f8f9fa;
            border-left: 3px solid #007bff;
            color: #007bff;
        }

        .profile-info h6,
        .profile-info small {
            margin: 0;
            padding: 0;
        }

    </style>
</head>
<body>

<!-- Toggle button for small screens -->
<div class="toggle-btn">
    <i class="fas fa-bars"></i>
</div>

<div class="sidebar d-flex flex-column min-vh-100">
    <img src="assets/media/logos/logomibu.png" alt="User Avatar" class="img-fluid">
    
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link {{ Request::is('home') ? 'active' : '' }}" href="{{ route('home') }}">
                <i class="fas fa-folder"></i> <span>Data Ibu Hamil</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link {{ Request::is('data-kesehatan*') ? 'active' : '' }}" href="{{ route('data-kesehatan.index') }}">
                <i class="fas fa-building"></i> <span>Catatan Kesehatan</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link {{ Request::is('data-nifas*') ? 'active' : '' }}" href="{{ route('data-nifas.index') }}">
                <i class="fas fa-file-alt"></i> <span>Catatan Nifas</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link {{ Request::is('data-layanan-kb*') ? 'active' : '' }}" href="{{ route('data-layanan-kb.index') }}">
                <i class="fas fa-file-alt"></i> <span>Catatan Layanan KB</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link {{ Request::is('data-anak*') ? 'active' : '' }}" href="{{ route('data-anak.index') }}">
                <i class="fas fa-child"></i> <span>Catatan Anak</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link {{ Request::is('data-artikel*') ? 'active' : '' }}" href="{{ route('data-artikel.index') }}">
                <i class="fas fa-newspaper"></i> <span>Artikel</span>
            </a>
        </li>
    </ul>

    <ul class="nav flex-column mt-auto mb-7"> 
        <li class="nav-item">
            <a class="nav-link {{ Request::is('profile*') ? 'active' : '' }}" 
               href="{{ route('profile.index', $user->id) }}">
                <i class="fas fa-user"></i> <span>Profile</span>
            </a>
        </li>
    </ul>

    <hr class="sidebar-divider my-0">

    <!-- Profile Info Section -->
    <div class="profile-info p-3">
        <div class="profile-image-container d-flex justify-content-center align-items-center">
            <img 
                src="assets/media/logos/logoMibu.png" 
                alt="Profile Picture" 
                class="rounded-circle profile-image"
                style="width: 40px; height: 40px; margin: auto;"
            >
        </div>
        <div class="profile-details d-flex flex-column justify-content-center ml-3">
            <h6 class="mb-0">{{ auth()->user()->name }}</h6>
            <small class="text-muted">{{ auth()->user()->kode_yankes }}</small>
        </div>
    </div>


</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- JavaScript for sidebar toggle -->
<script>
    document.querySelector('.toggle-btn').addEventListener('click', function() {
        document.querySelector('.sidebar').classList.toggle('active');
    });
</script>
</body>
</html>
