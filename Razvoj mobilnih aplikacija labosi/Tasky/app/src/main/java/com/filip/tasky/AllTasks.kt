package com.filip.tasky

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.all_tasks.*
import kotlinx.android.synthetic.main.all_tasks.view.*
import kotlinx.android.synthetic.main.new_task.*

open class AllTasks: Fragment() {
    private val taskAdapter by lazy { TaskAdapter(taskListener) }

    val taskListener = object: TaskInterface {
        override fun remove(id: Int) {

            TaskDatabase.taskDao.delete(id)
            display()
        }
    }

    companion object {
        fun newInstance(): Fragment{
            return AllTasks()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.all_tasks, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onResume()
        setUpUi()
        display()
    }

    private fun setUpUi() {
        seeTasks.layoutManager = LinearLayoutManager(MyApplication.ApplicationContext)
        seeTasks.adapter = taskAdapter
        seeTasks.itemAnimator = DefaultItemAnimator()
        seeTasks.addItemDecoration(DividerItemDecoration(MyApplication.ApplicationContext,RecyclerView.VERTICAL))
    }

    private fun display() {
        taskAdapter.setData(TaskDatabase.taskDao.getAll())
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if(isVisibleToUser){
            display()
        }
    }
}