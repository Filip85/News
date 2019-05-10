package com.filip.tasky

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.all_tasks.*
import kotlinx.android.synthetic.main.new_task.*
import kotlinx.android.synthetic.main.new_task.view.*
import java.util.*
import kotlin.collections.ArrayList

class NewTask: Fragment(){

    companion object {
        fun newInstance(): NewTask {
            return NewTask()
        }
        const val YES = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        val view = inflater.inflate(R.layout.new_task, container, false);

        view.TaskButton.setOnClickListener{ addTask() }
        return view;
    }

    private fun addTask() {
        val index = Random().nextInt(100)
        val task = Tasks(0, AddTask.text.toString(), spinner1.selectedItem.toString(), spinner2.selectedItem.toString())
        TaskDatabase.taskDao.insert(task)
        val flag = TaskDatabase.taskDao.getAll()
        Toast.makeText(MyApplication.ApplicationContext, "Task added", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSpinner()
    }

    private fun setUpSpinner() {
        val adapter = ArrayAdapter(MyApplication.ApplicationContext,
                android.R.layout.simple_spinner_item,
                TaskDatabase.taskDao.getAllCategory())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter

        ArrayAdapter.createFromResource(MyApplication.ApplicationContext, R.array.priority, android.R.layout.simple_spinner_item)
                .also { pradapter -> pradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.adapter = pradapter
                }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser){
            setUpSpinner()
        }
    }
}
