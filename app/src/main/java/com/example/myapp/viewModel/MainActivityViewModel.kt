package com.example.myapp.viewModel

import com.example.myapp.data.User
import com.example.myapp.model.repository.FirebaseRepository
import com.example.myapp.model.repository.FirebaseRepositoryImpl

class MainActivityViewModel {

    private val firebaseRepository: FirebaseRepository = FirebaseRepositoryImpl()

    fun updateUserData(firebaseUser: User, uid: String) {
        firebaseRepository.updateUserData(firebaseUser, uid)
    }

}