package com.cold.coordinatorlayoutdemo.test2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cold.coordinatorlayoutdemo.R;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class TestActivity2 extends AppCompatActivity{

    private List<String> datas;
    private RecyclerView recyclerView;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        recyclerView = (RecyclerView) findViewById(R.id.recview);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initData();
        CustomAdapter adapter = new CustomAdapter(this,datas);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add(""+ i);
        }
    }

}
