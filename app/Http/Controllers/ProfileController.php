<?php

namespace App\Http\Controllers;
use App\Models\User;

use Illuminate\Http\Request;

class ProfileController extends Controller
{
    public function detailUser($id)
    {
        // Fetch the record based on the ID from the 'DataIbuHamil' model
        $user = User::find($id);

        // Check if the record exists
        if (!$user) {
            return redirect()->route('data-ibu-hamil.index')->with('error', 'Data not found.');
        }

        // Pass the data to the view
        return view('profile/profile', compact( 'user'));
    }
}
