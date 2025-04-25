package com.fmt.konichi.Model

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database

class AuthModel : ViewModel() {

    private val auth = Firebase.auth
    private val database = Firebase.database

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    onResult(true, "Berhasil login")
                }else{
                    onResult(false, it.exception?.localizedMessage)
                }
            }
    }


    fun signUp(email : String, name: String, password : String, confirmPassword : String, onResult: (Boolean,String?) -> Unit ){
        if (password != confirmPassword){
            onResult(false, "Konfirmasi password salah")
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{

                if (it.isSuccessful){

                    val uid = it.result?.user?.uid
                    val users = User(uid,name,email,password)
                    if (uid != null) {
                        database.getReference("User").child(uid).setValue(users)
                            .addOnCompleteListener{task1 ->
                                if (task1.isSuccessful){
                                    onResult(true, "Akun berhasil dibuat")
                                }else{
                                    onResult(false, "Akun gagal dibuat")
                                }
                            }
                    }
                }else{
                    onResult(false,it.exception?.localizedMessage)
                }
            }

    }




}