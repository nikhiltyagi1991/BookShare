package com.nikhil.bookshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class Profile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,null);
        updateUserBooks((ListView)view.findViewById(R.id.userBooks));
        addNewBook((ListView) view.findViewById(R.id.userBooks), inflater);
        return view;
    }

    private void updateUserBooks(ListView availableBooks){
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Introduction to Algorithms", "Someone", 250.75, 100));
        books.add(new Book("Operating Systems", "Rahul Tyagi", 275.50, 200));
        books.add(new Book("JQuery Novice to Ninja", "Nikhil tyagi", 150.75, 50));
        availableBooks.setAdapter(new BooksAdapter(this.getContext(), R.layout.list_book_layout, books));
    }

    private void addNewBook(ListView availableBooks,LayoutInflater inflater){
        //View addBookView = inflater.inflate(R.layout.layout_add_book,null);
        Button addBook = new Button(inflater.getContext());
        addBook.setText("Share a Book");
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addBookIntent = new Intent(v.getContext(),AddBook.class);
                startActivity(addBookIntent);
            }
        });
        availableBooks.addFooterView(addBook);
    }

}
