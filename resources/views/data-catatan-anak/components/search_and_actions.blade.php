<head>
    <style>
        .input-group .form-control {
            border-radius: 20px;
            border-color: #ddd;
        }

        .btn-sort {
            border-radius: 20px;
            background-color: #EBF8FF;
            color: #4DBEFF;
            height: 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 200px;
            text-align: left;
            font-weight: bold;
            border: 2px ##4DBEFF;
        }

        .btn-outline-primary {
            align-items: center;
            padding: 10px;
            border-radius: 10px;
        }

        .btn-primary {
            align-items: center;
            padding: 10px;
            border-radius: 10px;
        }

        .btn-sort::after {
            margin-left: 10px;
        }

        .btn-outline-secondary {
            border: none;
        }

        .form-control {
            background-color: #F9F9FB;
            height: 50px;
            font-weight: bold;
        }

        .form-control::placeholder {
            color: #B2BAC6;
        }
    </style>
</head>


<div class="row mb-4">
    <div class="col-md-12 text-right d-flex justify-content-between align-items-center">
        <h2 class="mb-0">Data Catatan Anak</h2>
        <div>
            <a href="{{ route('data-anak.download') }}" class="btn btn-outline-primary ml-2">
                <i class="flaticon2-download"></i> Download
            </a>
            <a href="{{ route('data-anak.create') }}" class="btn btn-primary ml-2">
                <i class="flaticon2-add-1"></i> Tambah Data
            </a>
        </div>
    </div>
</div>

<hr />

<div class="row mb-4">
    <div class="col-md-4">
        <form action="{{ route('data-anak.index') }}" method="GET">
            <div class="input-group">
                <!-- Search Field -->
                <input type="text" name="search" value="{{ request('search') }}" class="form-control" placeholder="Cari Catatan Anak" aria-label="Cari Catatan Anak">
            </div>
        </form>
    </div>

    <!-- Sort By Dropdown Button -->
    <div class="col-md-4">
        <div class="btn-group">
        <button type="button" class="btn btn-sort dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span>Paling Baru</span>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Paling Baru</a>
            <a class="dropdown-item" href="#">Paling Lama</a>
            <a class="dropdown-item" href="#">A-Z</a>
            <a class="dropdown-item" href="#">Z-A</a>
        </div>
        </div>
    </div>
</div>


