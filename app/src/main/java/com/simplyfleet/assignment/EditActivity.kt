package com.simplyfleet.assignment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.simplyfleet.assignment.network.RetrofitBuilder
import com.simplyfleet.assignment.network.ViewModelFactory
import com.simplyfleet.assignment.roomdb.AppDatabase
import com.simplyfleet.assignment.roomdb.User
import com.simplyfleet.assignment.roomdb.UserDao
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Random


class EditActivity : AppCompatActivity() {
    private lateinit var userName: TextInputEditText
    private lateinit var userMobileno: TextInputEditText
    private lateinit var userEmail: TextInputEditText
    private lateinit var user_address: TextInputEditText
    private lateinit var user_address1: TextInputEditText



    private lateinit var btn_submit: Button
    private lateinit var viewModel: ListViewModel
    private lateinit var dao1: UserDao
    var uid:Int=0
    var loadUserDetails: List<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        setupViewModel()

        userName =findViewById(R.id.userName)
        userMobileno=findViewById(R.id.userPhoneno)
        userEmail=findViewById(R.id.Useremail)
        user_address=findViewById(R.id.user_address)
        user_address1=findViewById(R.id.user_address1)
        btn_submit=findViewById(R.id.btn_submit)
        dao1 = AppDatabase.getInstance(this)!!.userDao()


        if( getIntent().getExtras() != null)
        {
            uid = intent.getStringExtra("uid")!!.toInt()
            loadUserDetails= dao1.getUserbyid(uid.toInt())
            userName.setText(loadUserDetails?.get(0)?.name.toString())
            userMobileno.setText(loadUserDetails?.get(0)?.mobile.toString())
            userEmail.setText(loadUserDetails?.get(0)?.email.toString())
            user_address.setText(loadUserDetails?.get(0)?.address.toString())
            user_address1.setText(loadUserDetails?.get(0)?.userAddress1.toString())
        }

        checkValidation()
        submitButton()

    }



    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(RetrofitBuilder.apiService)
        )[ListViewModel::class.java]
    }

    private  fun   addData(){

        val name=userName.text.toString()
        val userMobileno=userMobileno.text.toString()
        val userEmail=userEmail.text.toString()
        val userAddress=user_address.text.toString()
        val userAddress1=user_address1.text.toString()



        val random2 = Random().nextInt(10) + 1
        val questionEntity = User(random2, name,userMobileno,userEmail,userAddress,userAddress1,getCurrentDate1().toString())
        val ytre= dao1.insertAll(questionEntity)
        finish()
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    private  fun   updateData(){

        val name=userName.text.toString()
        val userMobileno=userMobileno.text.toString()
        val userEmail=userEmail.text.toString()
        val userAddress=user_address.text.toString()
        val userAddress1=user_address1.text.toString()

//   Update user data
        dao1.UpdateUserDetails(name,userMobileno,userEmail,userAddress,userAddress1,uid.toInt())

        finish()
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate1(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDateTime.now().format(formatter).toString()
    }
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun checkValidation(){
        userName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) userName.setError(null)
            }
        })

        userMobileno.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) userMobileno.setError(null)
            }
        })
        userEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) userEmail.setError(null)
            }
        })
    }

    fun submitButton(){
        btn_submit.setOnClickListener {

                            if (userName.getText().toString().trim().equals("")) {
                            userName.setError("Enter Name");
                            }
                            if (userMobileno.getText().toString().trim().equals("")) {
                            userMobileno.setError("Enter Mobile no");
                            }
                            if (userEmail.getText().toString().trim().equals("")) {
                            userEmail.setError("Enter Email");
                            }

                            if (user_address.getText().toString().trim().equals("")) {
                            user_address.setError("Enter Addresline1");
                            }
                            if (user_address1.getText().toString().trim().equals("")) {
                            user_address1.setError("Enter Addressline2");
                            }

                             if (isValidEmail(userEmail.getText().toString())) {

                                 if(!TextUtils.isEmpty(userName.getText().toString()) && !TextUtils.isEmpty(userMobileno.getText().toString())  && !TextUtils.isEmpty(userEmail.getText().toString()) && !TextUtils.isEmpty(user_address.getText().toString()) && !TextUtils.isEmpty(user_address1.getText().toString()) ) {
                                     if(uid>0){
                                         //Update user data
                                         updateData()
                                     }else{
                                         //Add new User
                                         addData()
                                     }


                                 }

                            } else{
                                userEmail.setError("Enter Valid email");
                            }

        }
    }
}