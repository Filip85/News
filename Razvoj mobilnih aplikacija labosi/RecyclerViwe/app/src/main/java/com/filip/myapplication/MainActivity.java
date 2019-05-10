package com.filip.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private int[] image = {R.drawable.download, R.drawable.stevejobsheadshot2010crop2, R.drawable.swh1web};

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listRecyclerView = (RecyclerView)findViewById(R.id.recylerViweID);
        layoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(image);
        listRecyclerView.setAdapter(adapter);
    }
}
