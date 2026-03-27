package com.cold.coordinatorlayoutdemo.test4;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cold.coordinatorlayoutdemo.R;


public class TestSecondFragment4 extends BaseFragment {

    private Context mContext;

    public TestSecondFragment4() {

    }

    public static TestSecondFragment4 newInstance() {
        TestSecondFragment4 fragment = new TestSecondFragment4();
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
        View view = inflater.inflate(R.layout.fragment_test_second4, container, false);
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
