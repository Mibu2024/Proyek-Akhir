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
            display: flex; /* Enables flexbox layout */
        }
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed; /* Ensures the sidebar is fixed */
            left: 0; /* Aligns the sidebar to the left */
            top: 0; /* Aligns the sidebar to the top */
            background-color: #fff;
            border-right: 1px solid #eee;
            display: flex;
            flex-direction: column; /* Arrange children vertically */
            padding-top: 20px;
            z-index: 1000; /* Ensures it is above other content */
        }
        .sidebar img {
            border-radius: 50%;
            display: block;
            margin: 0 auto 10px;
        }
        .sidebar .nav-link {
            display: grid;
            grid-template-columns: 30px 1fr; /* 30px for icon, remaining for text */
            align-items: center; /* Vertically centers both columns */
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
        .sidebar .user-profile {
            padding: 15px;
            margin-top: auto; /* Pushes the user profile to the bottom */
            width: 100%;
            border-top: 1px solid #eee;
        }
        .sidebar .user-profile img {
            width: 40px;
            height: 40px;
        }
        .sidebar .user-profile p {
            margin: 0;
            font-size: 14px;
            color: #6c757d;
        }
        .sidebar .img-fluid {
            width: 60px;
            height: 60px;
        }
        .content {
            margin-left: 250px; /* Create space for the sidebar */
            padding: 20px; /* Add padding to the content */
            flex: 1; /* Allows the content to take the remaining space */
        }
    </style>
</head>
<body>

<div class="sidebar">
    <img src="assets/media/logos/logomibu.png" alt="User Avatar" class="img-fluid">
    
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="#">
                <i class="fas fa-folder"></i> <span>Data Ibu Hamil</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-building"></i> <span>Catatan Kesehatan</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-file-alt"></i> <span>Catatan Nifas</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-clock"></i> <span>Catatan Imunisasi</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-child"></i> <span>Catatan Anak</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-newspaper"></i> <span>Artikel</span>
            </a>
        </li>
    </ul>

    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-cog"></i> <span>Settings</span>
            </a>
        </li>
    </ul>

    <div class="user-profile d-flex align-items-center">
        <img src="https://via.placeholder.com/40" alt="User Profile">
        <div class="ml-3">
            <p class="mb-0">Louise Thompson</p>
            <small>Enterprise plan</small>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
