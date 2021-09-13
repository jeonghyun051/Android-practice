package com.cos.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUserList, rvItemList;
    private UserAdapter userAdapter;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<User> users = new ArrayList<>();
        users.add(new User(1,"story"));
        users.add(new User(2,"story"));
        users.add(new User(3,"story"));
        users.add(new User(4,"story"));
        users.add(new User(5,"story"));
        users.add(new User(6,"story"));
        users.add(new User(7,"story"));
        users.add(new User(8,"story"));

        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rvUserList = findViewById(R.id.rv_user_list);
        rvUserList.setLayoutManager(manager);
        userAdapter = new UserAdapter(users);

        rvUserList.setAdapter(userAdapter);

        List<Item> items = new ArrayList<>();
        items.add(new Item(1,"numbiker.nikkhi"));
        items.add(new Item(2,"keria"));
        items.add(new Item(3,"rich"));
        items.add(new Item(4,"story"));
        items.add(new Item(5,"story"));
        items.add(new Item(6,"story"));
        items.add(new Item(7,"story"));
        items.add(new Item(8,"story"));
        LinearLayoutManager manager2 = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvItemList = findViewById(R.id.rv_item_list);
        rvItemList.setLayoutManager(manager2);
        itemAdapter = new ItemAdapter(items);

        rvItemList.setAdapter(itemAdapter);
    }
}
