package com.example.loginapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginapp.Activities.Adapter.WordsAdapter;
import com.example.loginapp.Activities.Model.Lesson;
import com.example.loginapp.Activities.Model.Words;
import com.example.loginapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordsActivity extends AppCompatActivity {
    @BindView(R.id.toolbarWords)
    Toolbar toolbarWords;
    @BindView(R.id.rv_words_list)
    RecyclerView rv_words_list;
    public String lesson_name;
    private DatabaseReference wordsRef,lessonRef;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private ImageButton img1,img2,img3,img4;
    private ImageView imageView1;
    private RecyclerView list;

    private MediaPlayer mp;

    private ArrayList<Words> wordsList = new ArrayList<>();
    private WordsAdapter mAdapter;
    private String farmId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        ButterKnife.bind(this);
        setUI();
        getExtrasIntent();
    }

    private void setUI() {
        toolbarWords.setTitle(lesson_name);
        toolbarWords.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarWords);
        list.setHasFixedSize(true);
        list.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new WordsAdapter(wordsList, this);
        list.setAdapter(mAdapter);
        loadWords();
    }

    private void loadWords() {
        BaseApp.db
                .collection("lesson")
                .whereEqualTo("lesson_name", lesson_name)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    try {
                        wordsList.clear();
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String pinyin = documentSnapshot.getData().get("chinese_words").toString();
                            String english = documentSnapshot.getData().get("english_words").toString();
                            String img = documentSnapshot.getData().get("words_image").toString();
                            String audio = documentSnapshot.getData().get("words_voice").toString();
                            Words word = new Words(pinyin, english, img, audio);
                            wordsList.add(word);
                            mAdapter.notifyDataSetChanged();
                        }
                    } catch (Exception ex) {}
                });
    }

    private void getExtrasIntent(){
        lesson_name = getIntent().getStringExtra("lesson_name");
    }
}
