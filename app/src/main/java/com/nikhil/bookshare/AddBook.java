package com.nikhil.bookshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import network.GoogleBookAsyncTask;

public class AddBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_book);

        ImageButton searchBook = (ImageButton)findViewById(R.id.searchBook);
        searchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText searchText = (EditText)findViewById(R.id.searchText);
                String titleSearch = searchText.getText().toString();
                if(titleSearch.isEmpty())
                    return;
                ListView booksList = (ListView)findViewById(R.id.booksList);
                GoogleBookAsyncTask googleBookAsyncTask = new GoogleBookAsyncTask(titleSearch,booksList,findViewById(R.id.hintText));
                googleBookAsyncTask.execute();
            }
        });


    }
}
