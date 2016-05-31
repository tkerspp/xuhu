package com.example.xumengxi.uw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by xumengxi on 2016/5/25.
 */
public class SecondActivity extends AppCompatActivity {
    private ChatMessage chatMessage;
    private Firebase myFirebaseRef;
    private Firebase FirebaseRef;
    private ListView mListView;
    private Button mButtonSend;
    private EditText mEditTextMessage;
    private ChatMessageAdapter mAdapter;

    String rString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        myFirebaseRef.setAndroidContext(this);
        FirebaseRef.setAndroidContext(this);
        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (Button) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);

        rString = getIntent().getExtras().getString("extra");

        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);
        myFirebaseRef = new Firebase("https://blazing-fire-5657.firebaseio.com/" + rString);
        FirebaseRef = new Firebase("https://blazing-fire-5657.firebaseio.com/");
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                sendMessage(message);
                mEditTextMessage.setText("");
                myFirebaseRef.setValue(message);

            }
        });
        if (rString.equals("xuhu")) {
            Response("liaomengsi");
            Response("xuming");
        }else if (rString.equals("liaomengsi")) {
           Response("xuhu");
        }else if (rString.equals("xuming")){
            Response("xuhu");
        }
    }
    private void sendMessage(String message) {
        chatMessage = new ChatMessage(message, true, false, rString);
        mAdapter.add(chatMessage);
    }

    private void mimicOtherMessage(String message) {
        ChatMessage Message = new ChatMessage(message, false, false);
        mAdapter.add(Message);
    }

    private void Response(String str){
        FirebaseRef.child(str).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                mimicOtherMessage(text);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
