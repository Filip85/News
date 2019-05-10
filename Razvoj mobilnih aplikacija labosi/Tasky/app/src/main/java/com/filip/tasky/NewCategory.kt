package com.filip.tasky

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.new_category.*
import kotlinx.android.synthetic.main.new_category.view.*
import java.util.*

class NewCategory: Fragment() {
    companion object {
        fun newInstance(): NewCategory {
            return NewCategory()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        val view = inflater.inflate(R.layout.new_category, container, false);

        view.CategoryButton.setOnClickListener { addCategory() }
        return view;
    }

    private fun addCategory() {
        val categories = Category(0, AddCategory.text.toString())
        TaskDatabase.taskDao.insertC(categories)
        Toast.makeText(MyApplication.ApplicationContext, "Category added", Toast.LENGTH_LONG).show()
    }
}