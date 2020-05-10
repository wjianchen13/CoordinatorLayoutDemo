package com.cold.coordinatorlayoutdemo.mix;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cold.coordinatorlayoutdemo.R;

import java.util.ArrayList;
import java.util.List;


public class ScrollFragment extends Fragment {

    private Context mContext;

    public ScrollFragment() {

    }

    public static ScrollFragment newInstance() {
        ScrollFragment fragment = new ScrollFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scroll, container, false);
//        RecyclerView recyclerView = view.findViewById(R.id.list);
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(i + 1 + "");
//        }
//        TypeAdapter adapter = new TypeAdapter(mContext, list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//        layoutManager.setOrientation(recyclerView.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
        return view;
    }

}
