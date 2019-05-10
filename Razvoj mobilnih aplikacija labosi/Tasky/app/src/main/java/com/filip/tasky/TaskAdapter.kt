package com.filip.tasky

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.items_repository.view.*

class TaskAdapter(val taskListener: TaskInterface): RecyclerView.Adapter<TaskHolder>() {

    private val tasks = mutableListOf<Tasks>()

    fun setData(newTasks: List<Tasks>){
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val taskView = LayoutInflater.from(parent.context).inflate(R.layout.items_repository, parent, false)
        val taskHolder = TaskHolder(taskView)
        return taskHolder
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val tasks1 = tasks[position]
        holder.bind(tasks1, taskListener)
    }
}

class TaskHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(tasks1: Tasks, taskListener: TaskInterface) {
        itemView.yourTasks.text = tasks1.task
        itemView.yourCategory.text = tasks1.category

        if(tasks1.priority == "High"){
            itemView.priorityImage.setImageResource(R.drawable.red)
        }
        else if(tasks1.priority == "Medium") {
            itemView.priorityImage.setImageResource(R.drawable.yellow)
        }
        else{
            itemView.priorityImage.setImageResource(R.drawable.green)
        }
        itemView.setOnLongClickListener { taskListener.remove(tasks1.id); true }
    }
}
