package com.nikhil.bookshare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 */
public class AvailableBooks extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_books,null);
        updateAvailableBooks((ListView) view.findViewById(R.id.booksList));
        return view;
    }

    private void updateAvailableBooks(ListView availableBooks){
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Introduction to Algorithms","Someone",250.75,100));
        books.add(new Book("Operating Systems","Rahul Tyagi",275.50,200));
        books.add(new Book("JQuery Novice to Ninja","Nikhil tyagi",150.75,50));
        books.add(new Book("Angularjs Resouce Book","XYZ",250.75,0));
        books.add(new Book("Switch: Changing when change is hard","DEF",170.05,40));
        books.add(new Book("MEAN framework: MongoDB Express Angular Node","IJK",230.50,70));
        availableBooks.setAdapter(new BooksAdapter(this.getContext(),R.layout.list_book_layout,books));
    }
}
