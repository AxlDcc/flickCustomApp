package com.example.udeoflickr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.udeoflickr.gallery_list.GalleryListActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    //FIREBASE
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private var mAuth: FirebaseAuth?= null
    //LoginLogic
    private var labelAuth: Boolean? = null
    private var tv_title : TextView? = null
    private var tv_opcion : TextView? = null
    //Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        mAuth = FirebaseAuth.getInstance()
        initUi()
    }
    fun initUi(){
        tv_title = findViewById(R.id.tv_login_title)
        tv_opcion = findViewById(R.id.tv_opcion_ingreso)
    }

    fun buttonLoginEvent(view: View) {
        val email = tv_userName.text.toString()
        val password = tv_Password.text.toString()
        loginToFirebase(email,password)
    }
    fun loginToFirebase(email:String,password: String){
        if (labelAuth !== null){
            mAuth!!.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Succesful Login",Toast.LENGTH_LONG).show()
                        LoadUser()
                    }else{
                        Toast.makeText(applicationContext,"Error al ingresar",Toast.LENGTH_LONG).show()
                    }
                }
        }else{
            mAuth!!.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext,"Cuenta Creada con exito",Toast.LENGTH_LONG).show()
                        LoadUser()
                    }else{
                        Toast.makeText(applicationContext,"Error al crear la cuenta",Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    override fun onStart() {
        super.onStart()
        LoadUser()
    }

    fun LoadUser(){
        var currentUser = mAuth!!.currentUser
        if (currentUser!= null){
            val intent = Intent(this,GalleryListActivity::class.java)
            intent.putExtra("email",currentUser.email)
            intent.putExtra("Uid",currentUser.uid)
            startActivity(intent)
        }
    }

    fun loginEvent(view: View) {
        if (tv_opcion?.text == "Ya tienes Cuenta? Ingresa"){
            labelAuth = true
            tv_title?.text = "Inicia Sesión"
            tv_opcion?.text = "No tienes cuenta? Registrate"
        }else{
            labelAuth = false
            tv_title!!.text = "Crear Cuenta"
            tv_opcion?.text = "Ya tienes Cuenta? Ingresa"
        }
    }
}
