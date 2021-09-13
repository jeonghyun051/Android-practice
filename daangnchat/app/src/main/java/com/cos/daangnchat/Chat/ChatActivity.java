package com.cos.daangnchat.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cos.daangnchat.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rvChatList;
    private ChatAdapter chatAdapter;
    private String nickname = "name2";
    private TextView etText;
    private Button btnSend;
    private DatabaseReference myRef;
    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Write a message to the database 데베에 메시지 보내기
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        etText = findViewById(R.id.et_msg);
        btnSend = findViewById(R.id.btn_chat);
        btnSend.setOnClickListener(v -> {
            String msg = etText.getText().toString(); // msg
            if (msg != null){
                ChatData chat = new ChatData();
                chat.setNickname(nickname);
                chat.setMsg(msg);
                myRef.push().setValue(chat);
                //myRef.setValue("Hello, World!123");
            }
        });

        List<ChatData> chats = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(new ChatActivity(),RecyclerView.VERTICAL,false);
        rvChatList = findViewById(R.id.rv_chat_list);
        rvChatList.setLayoutManager(manager);
        chatAdapter = new ChatAdapter(chats, nickname);

        rvChatList.setAdapter(chatAdapter);


        /*ChatData chat = new ChatData();
        chat.setNickname(name);
        chat.setMsg("hi");
        myRef.setValue(chat);
        //myRef.setValue("Hello, World!123");*/

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG, "onChildAdded: 스냅샷 " + snapshot);
                ChatData chat = snapshot.getValue(ChatData.class);
                chatAdapter.addItem(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}