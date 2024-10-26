<!-- title riwayat kesehatan -->
<div class="container-title-status-imunisasi">
   <div class="row align-items-center">
      <div class="col-sm-6">
         <h3>Status Imunisasi</h3>
      </div>
   </div>
</div>

<!-- Modal for Adding Immunization Date -->
<div class="modal fade" id="imunisasiModal" tabindex="-1" aria-labelledby="imunisasiModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="{{ route('data-anak.updateImunisasi', $anakRecords->id) }}" method="POST">
            @csrf
            @method('PUT')
            <input type="hidden" name="imunisasi_column" id="imunisasi_column">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="imunisasiModalLabel">Tambah Imunisasi</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="imunisasi_type">Jenis Imunisasi</label>
                        <input type="text" id="imunisasi_type" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        <label for="tanggal_imunisasi">Tanggal Imunisasi</label>
                        <input type="date" name="tanggal_imunisasi" id="tanggal_imunisasi" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Submit</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                </div>
            </div>
        </form>
    </div>
</div>


<div class="container">
    <!-- Hepatitis B -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>Hepatitis B</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->tanggal_imunisasi_hepatitis_b ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->tanggal_imunisasi_hepatitis_b ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_hepatitis_b)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_hepatitis_b }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_hepatitis_b) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Hepatitis B" data-column="hepatitis_b">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- BCG -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>BCG</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->tanggal_imunisasi_bcg ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->tanggal_imunisasi_bcg ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_bcg)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_bcg }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_bcg) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="BCG" data-column="bcg">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- Polio Tetes 1 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>Polio Tetes 1</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->tanggal_imunisasi_polio_tetes_1 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->tanggal_imunisasi_polio_tetes_1 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_polio_tetes_1)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_polio_tetes_1 }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_polio_tetes_1) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Polio Tetes 1" data-column="polio_tetes_1">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- DPT-HB-Hib 1 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>DPT-HB-Hib 1</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->tanggal_imunisasi_dpt_hb_hib_1 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->tanggal_imunisasi_dpt_hb_hib_1 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_dpt_hb_hib_1)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_dpt_hb_hib_1 }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_dpt_hb_hib_1) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="DPT-HB-Hib 1" data-column="dpt_hb_hib_1">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- Polio Tetes 2 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>Polio Tetes 2</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->polio_tetes_2 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->polio_tetes_2 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_polio_tetes_2)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_polio_tetes_2 }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_polio_tetes_2) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Polio Tetes 2" data-column="polio_tetes_2">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- Rota Virus 1 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>Rota Virus 1</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->rota_virus_1 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->rota_virus_1 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_rota_virus_1)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_rota_virus_1 }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_rota_virus_1) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Rota Virus 1" data-column="rota_virus_1">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- PCV 1 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>PCV 1</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->pcv_1 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->pcv_1 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_pcv_1)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_pcv_1 }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_pcv_1) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="PCV 1" data-column="pcv_1">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- DPT-HB-HIB 2 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>DPT-HB-HIB 2</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->dpt_hb_hib_2 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->dpt_hb_hib_2 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_dpt_hb_hib_2)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_dpt_hb_hib_2 }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_dpt_hb_hib_2) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="DPT-HB-HIB 2" data-column="dpt_hb_hib_2">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- Polio Tetes 3 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>Polio Tetes 3</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->polio_tetes_3 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->polio_tetes_3 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_polio_tetes_3)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_polio_tetes_3 }}</span>
                    @endif
                </div>

                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_polio_tetes_3) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Polio Tetes 3" data-column="polio_tetes_3">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- Rota Virus 2 -->
    <div class="card-list-imunisasi mb-3">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-sm-5">
                    <h4>Rota Virus 2</h4>
                </div>
                <div class="col-sm-2">
                    <span class="status-badge {{ $anakRecords->rota_virus_2 ? 'sudah' : 'belum' }}">
                        Status: {{ $anakRecords->rota_virus_2 ? 'Sudah' : 'Belum' }}
                    </span>
                </div>
                <div class="col-sm">
                    @if($anakRecords->tanggal_imunisasi_rota_virus_2)
                        <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_rota_virus_2 }}</span>
                    @endif
                </div>
                <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_rota_virus_2) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Rota Virus 2" data-column="rota_virus_2">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
            </div>
        </div>
    </div>

    <!-- PCV 2 -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>PCV 2</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->pcv_2 ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->pcv_2 ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_pcv_2)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_pcv_2 }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_pcv_2) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="PCV 2" data-column="pcv_2">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- DPT-HB-HIB 3 -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>DPT-HB-HIB 3</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->dpt_hb_hib_3 ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->dpt_hb_hib_3 ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_dpt_hb_hib_3)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_dpt_hb_hib_3 }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_dpt_hb_hib_3) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="DPT-HB-HIB 3" data-column="dpt_hb_hib_3">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- Polio Tetes 4 -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>Polio Tetes 4</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->polio_tetes_4 ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->polio_tetes_4 ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_polio_tetes_4)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_polio_tetes_4 }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_polio_tetes_4) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Polio Tetes 4" data-column="polio_tetes_4">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- Polio Suntik 1 -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>Polio Suntik 1</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->polio_suntik_1 ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->polio_suntik_1 ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_polio_suntik_1)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_polio_suntik_1 }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_polio_suntik_1) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Polio Suntik 1" data-column="polio_suntik_1">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- Rota Virus 3 -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>Rota Virus 3</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->rota_virus_3 ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->rota_virus_3 ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_rota_virus_3)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_rota_virus_3 }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_rota_virus_3) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Rota Virus 3" data-column="rota_virus_3">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- Campak Rubella -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>Campak Rubella</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->campak_rubella ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->campak_rubella ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_campak_rubella)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_campak_rubella }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_campak_rubella) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Campak Rubella" data-column="campak_rubella">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- Polio Suntik 2 -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>Polio Suntik 2</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->polio_suntik_2 ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->polio_suntik_2 ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_polio_suntik_2)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_polio_suntik_2 }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_polio_suntik_2) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Polio Suntik 2" data-column="polio_suntik_2">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- Japanese Encephalitis -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>Japanese Encephalitis</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->japanese_encephalitis ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->japanese_encephalitis ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_japanese_encephalitis)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_japanese_encephalitis }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_japanese_encephalitis) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Japanese Encephalitis" data-column="japanese_encephalitis">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- PCV 3 -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>PCV 3</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->pcv_3 ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->pcv_3 ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_pcv_3)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_pcv_3 }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_pcv_3) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="PCV 3" data-column="pcv_3">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- DPT-HB-HIB Lanjutan -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>DPT-HB-HIB Lanjutan</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->dpt_hb_hib_lanjutan ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->dpt_hb_hib_lanjutan ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_dpt_hb_hib_lanjutan)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_dpt_hb_hib_lanjutan }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_dpt_hb_hib_lanjutan) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="DPT-HB-HIB Lanjutan" data-column="dpt_hb_hib_lanjutan">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>

   <!-- Campak Rubella Lanjutan -->
   <div class="card-list-imunisasi mb-3">
      <div class="container">
         <div class="row align-items-center">
               <div class="col-sm-5">
                  <h4>Campak Rubella Lanjutan</h4>
               </div>
               <div class="col-sm-2">
                  <span class="status-badge {{ $anakRecords->campak_rubella_lanjutan ? 'sudah' : 'belum' }}">
                     Status: {{ $anakRecords->campak_rubella_lanjutan ? 'Sudah' : 'Belum' }}
                  </span>
               </div>
               <div class="col-sm">
                  @if($anakRecords->tanggal_imunisasi_campak_rubella_lanjutan)
                     <span class="status-badge">Tanggal: {{ $anakRecords->tanggal_imunisasi_campak_rubella_lanjutan }}</span>
                  @endif
               </div>

               <div class="col-sm">
                  @if(!$anakRecords->tanggal_imunisasi_campak_rubella_lanjutan) <!-- Check if the status is not 'Sudah' -->
                     <button class="btn btn-primary" data-toggle="modal" data-target="#imunisasiModal" 
                              data-imunisasi="Campak Rubella Lanjutan" data-column="campak_rubella_lanjutan">
                           Tambah Imunisasi
                     </button>
                  @endif
               </div>
         </div>
      </div>
   </div>
</div>


<head>
    <style>
        .card-list-imunisasi {
            position: relative;
            max-width: 100%;
            border-radius: 8px;
            overflow: hidden;
            margin-top: 20px;
            padding: 20px;
            box-shadow: 0 0px 8px rgba(0, 0, 0, 0.2);
            background-color: #f8f9fa;
        }

        .card-list-imunisasi .row {
            align-items: center;
        }

        .card-list-imunisasi p {
            margin-bottom: 0;
            color: #6c757d;
        }

        .card-list-imunisasi h4 {
            margin-bottom: 0;
            font-weight: bold;
            color: #495057;
        }

        .status-badge {
            display: inline-block;
            padding: 12px;
            border-radius: 8px;
            font-size: 13px;
            opacity: 0.9;
            font-weight: bold;
            text-align: center;
            background-color: #FDECC9;
            color: black;
        }

        @media (max-width: 768px) {
            .card-list-kesehatan .col-sm {
                margin-bottom: 10px;
            }
        }

        .btn-create-data-imunisasi {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #EBF8FF;
            border: 1px solid #4DBEFF;
            color: #4DBEFF;
        }

        .btn-create-data-imunisasi:hover {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #4DBEFF;
            border: 1px solid #4DBEFF;
            color: white;
        }

        .btn-create-data-imunisasi:hover .flaticon2-add-1 {
            align-items: center;
            border-radius: 8px;
            height: 50px;
            background-color: #4DBEFF;
            border: 1px solid #4DBEFF;
            color: white;
        }

        .flaticon2-add-1 {
            color: #4DBEFF;
        }

        .container-title-status-imunisasi {
            margin-top: 30px;
        }

        .btn-sort {
            border-radius: 8px;
            border: 1px solid #DDE1EB;
            color: #A0A8B5;
            height: 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 150px;
            text-align: left;
            font-weight: bold;
        }

        .btn-sort::after {
            margin-left: 10px;
        }

        .card-list-imunisasi, .container, .row {
            overflow: visible !important;
        }

        .status-badge.sudah {
            background-color: #4CAF50;
            color: white;
         }

         .status-badge.belum {
            background-color: #F44336;  
            color: white;
         }

    </style>

   <script>
      $('#imunisasiModal').on('show.bs.modal', function (event) {
         var button = $(event.relatedTarget);
         var imunisasiType = button.data('imunisasi');
         var imunisasiColumn = button.data('column');

         var modal = $(this);
         modal.find('#imunisasi_type').val(imunisasiType);
         modal.find('#imunisasi_name').val(imunisasiType); // Update this line
         modal.find('#imunisasi_column').val(imunisasiColumn);
      });
   </script>

</head>

