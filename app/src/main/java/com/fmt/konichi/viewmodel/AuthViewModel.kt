package com.fmt.konichi.viewmodel

import androidx.lifecycle.ViewModel
import com.fmt.konichi.Model.User
import com.fmt.konichi.usecase.AuthResult
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val auth = Firebase.auth
    private val database = Firebase.database

    private val _authState = MutableStateFlow<AuthResult>(AuthResult.Idle)
    val authState: StateFlow<AuthResult> = _authState

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun login(email: String, password: String) {
        _authState.value = AuthResult.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthResult.Success("Berhasil login")
                } else {
                    _authState.value = AuthResult.Error(task.exception?.localizedMessage ?: "Login gagal")
                }
            }
    }

    fun signUp(email: String, name: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            _authState.value = AuthResult.Error("Konfirmasi password salah")
            return
        }

        _authState.value = AuthResult.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid
                    val user = User(uid, name, email, password)

                    if (uid != null) {
                        database.getReference("User").child(uid).setValue(user)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    _authState.value = AuthResult.Success("Akun berhasil dibuat")
                                } else {
                                    _authState.value = AuthResult.Error("Gagal menyimpan data pengguna")
                                }
                            }
                    }
                } else {
                    _authState.value = AuthResult.Error(task.exception?.localizedMessage ?: "Signup gagal")
                }
            }
    }

    fun ShowUserName() {
        val uid = auth.currentUser?.uid
        if (uid != null) {
            database.getReference("User").child(uid)
                .get()
                .addOnSuccessListener { snapshot ->
                    val user = snapshot.getValue(User::class.java)
                    _currentUser.value = user
                }
                .addOnFailureListener {
                    _currentUser.value = null
                }
        }
    }

    fun logOut() {
        auth.signOut()
        _authState.value = AuthResult.Idle
        _currentUser.value = null
    }
}