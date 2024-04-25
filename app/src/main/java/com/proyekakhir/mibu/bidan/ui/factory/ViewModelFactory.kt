package com.proyekakhir.mibu.bidan.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.bidan.ui.auth.viewmodel.BidanLoginViewModel
import com.proyekakhir.mibu.bidan.ui.auth.viewmodel.BidanSignUpViewModel
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.BidanArtikelViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.BidanHomeViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.BidanSettingsViewModel

class ViewModelFactory(private val repository: FirebaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(BidanLoginViewModel::class.java) -> {
                BidanLoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(BidanSignUpViewModel::class.java) -> {
                BidanSignUpViewModel(repository) as T
            }

            modelClass.isAssignableFrom(BidanHomeViewModel::class.java) -> {
                BidanHomeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(BidanArtikelViewModel::class.java) -> {
                BidanArtikelViewModel(repository) as T
            }

            modelClass.isAssignableFrom(BidanSettingsViewModel::class.java) -> {
                BidanSettingsViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}