package com.nityam.ui.home_activity.ui.questionnaire.adapter


import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.simplyfleet.assignment.EditActivity
import com.simplyfleet.assignment.ListActivity

import com.simplyfleet.assignment.R
import com.simplyfleet.assignment.roomdb.AppDatabase
import com.simplyfleet.assignment.roomdb.User
import com.simplyfleet.assignment.roomdb.UserDao

class ViewAllQuestionAdapter(
    private val context: Context?,
    private val mList: List<User>?,

) :
    RecyclerView.Adapter<ViewAllQuestionAdapter.AllQueAnsViewHolder>() {
    private lateinit var dao1: UserDao

    //private lateinit var questionsAnswerDao: QuestionsAnswerDao

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllQueAnsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.all_user_list_adapter, parent, false)

        dao1 = context?.let { AppDatabase.getInstance(it) }!!.userDao()
        //questionsAnswerDao = AppDatabase.getInstance(parent.context)!!.questionsAnswerDao
        return AllQueAnsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AllQueAnsViewHolder, position: Int) {
        val ItemsViewModel = mList?.get(position)
        holder.fname.text = ItemsViewModel?.name
        //holder.subquestion.text = ItemsViewModel?.question
        //holder.subquestion.visibility = View.GONE
        //holder.lastname.text = ItemsViewModel?.lastName

        holder.edit.setTag(ItemsViewModel?.uid)
        holder.edit.setOnClickListener {

           // context?.startActivity(Intent(context, EditActivity::class.java))
            val uid=holder.edit.getTag().toString()
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra("uid", uid)
            context?.startActivity(intent)
        }
        holder.delete.setTag(ItemsViewModel?.uid)
        holder.delete.setOnClickListener {
            val id=holder.delete.getTag().toString()

            val deletedata=   dao1.deleteByUserId(id.toInt())
            context?.startActivity(Intent(context, ListActivity::class.java))
        //Log.d("hhhh","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+deletedata)

        }




    }

    class AllQueAnsViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val fname: TextView = itemview.findViewById(R.id.fname)

        //val subquestion: TextView = itemview.findViewById(R.id.subquestion)
        val edit: ImageView = itemview.findViewById(R.id.Imgedit)
        val delete: ImageView = itemview.findViewById(R.id.imgDelete)



    }

    interface onButtonClick {
        fun onEditClick(questionss: User?)
        fun onDeleteClick(questionss: User?)
    }
}