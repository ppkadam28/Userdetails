package com.simplyfleet.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nityam.ui.home_activity.ui.questionnaire.adapter.ViewAllQuestionAdapter
import com.simplyfleet.assignment.network.RetrofitBuilder
import com.simplyfleet.assignment.network.ViewModelFactory
import com.simplyfleet.assignment.roomdb.AppDatabase
import com.simplyfleet.assignment.roomdb.User
import com.simplyfleet.assignment.roomdb.UserDao
import java.util.Random

class ListActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private lateinit var dao1: UserDao
    private lateinit var floatAdd: FloatingActionButton
    private lateinit var recyler_user: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        floatAdd=findViewById(R.id.btn_add_user)
        recyler_user=findViewById(R.id.recycler_users_set)

        setupViewModel()


        dao1 = AppDatabase.getInstance(this)!!.userDao()

        val float_adduser: FloatingActionButton = findViewById(R.id.btn_add_user) as FloatingActionButton
        float_adduser.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }


        recyler_user.layoutManager = LinearLayoutManager(this)
        val questionlistadapter = ViewAllQuestionAdapter(this, loadquestion())
        recyler_user.adapter = questionlistadapter
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(RetrofitBuilder.apiService)
        )[ListViewModel::class.java]
    }

    fun loadquestion(): List<User>? {
        return dao1.getAll()
    }

}