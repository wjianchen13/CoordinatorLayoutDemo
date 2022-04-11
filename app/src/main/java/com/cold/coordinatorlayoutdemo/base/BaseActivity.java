package com.cold.coordinatorlayoutdemo.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cold.coordinatorlayoutdemo.R;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(i + 1 + "");
        }
        TypeAdapter adapter = new TypeAdapter(this, list, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

//        TextView title = findViewById(R.id.title);
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) title.getLayoutParams();
//        params.setBehavior(new SampleTitleBehavior());
    }
}
