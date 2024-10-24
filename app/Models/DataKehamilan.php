<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DataKehamilan extends Model
{
    use HasFactory;

    protected $table = 'data_kehamilans';
    
    protected $fillable =  [
        'id_kehamilan',
        'id_ibu',
        'tanggal_kehamilan',
        'tanggal_hpl',
        'kehamilan_ke'
    ];

    public function ibuHamil()
    {
        return $this->belongsTo(DataIbuHamil::class, 'id_ibu');
    }
}
