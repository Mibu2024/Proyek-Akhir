package com.proyekakhir.mibu.user.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.auth.viewmodel.LoginViewModel
import com.proyekakhir.mibu.user.auth.viewmodel.SignUpViewModel
import com.proyekakhir.mibu.user.di.Injection
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.anak.CatatanAnakViewModel
import com.proyekakhir.mibu.user.ui.home.HomeViewModel
import com.proyekakhir.mibu.user.ui.kehamilan.CatatanKehamilanViewModel

class ViewModelFactory(private val repository: UserRepository, private val userPreference: UserPreference) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository, userPreference) as T
            }

            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }

            modelClass.isAssignableFrom(CatatanKehamilanViewModel::class.java) -> {
                CatatanKehamilanViewModel(repository) as T
            }

            modelClass.isAssignableFrom(CatatanAnakViewModel::class.java) -> {
                CatatanAnakViewModel(repository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    val userPreference = UserPreference.getInstance(context.dataStore)
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context), userPreference)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}
