package com.example.a14_recyclerviewdragandswipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.a14_recyclerviewdragandswipe.adapter.CustomAdapter;
import com.example.a14_recyclerviewdragandswipe.helper.SimpleItemTouchHelperCallback;
import com.example.a14_recyclerviewdragandswipe.model.Member;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

        List<Member> members = prepareMemberList();
        refreshAdapter(members);
    }
    private void initview(){

        context = this;
        recyclerView = findViewById(R.id.recycleViewMain);
        recyclerView.setLayoutManager(new GridLayoutManager(context,1));
    }
    private void refreshAdapter(List<Member> members) {
        CustomAdapter adapter = new CustomAdapter(members, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }
    private List<Member> prepareMemberList(){
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            members.add(new Member("Sherzod " + i,"Jurabekov " + i));
        }
        return members;
    }
}