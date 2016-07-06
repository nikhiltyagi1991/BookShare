package com.nikhil.bookshare;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nikhil on 30/5/16.
 */
public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_book_layout, parent, false);
        }
        ImageView bookImage = (ImageView) convertView.findViewById(R.id.bookImage);
        TextView bookName = (TextView) convertView.findViewById(R.id.bookName);
        TextView bookExpectedPrice = (TextView) convertView.findViewById(R.id.bookExpectedPrice);
        TextView bookMRP = (TextView) convertView.findViewById(R.id.bookMRP);

        /*
        if(!getItem(position).getImageUrl().isEmpty())
            bookImage.setImageURI(new UR);
            */

        String expectedPrice,mrp ="Rs "+String.valueOf(book.getMaxPrice());
        if(book.getExpectedPrice()==0)
            expectedPrice = "Free!!";
        else
            expectedPrice = "Rs "+String.valueOf(book.getExpectedPrice());


        bookName.setText(book.getName());
        bookExpectedPrice.setText(expectedPrice);
        bookMRP.setText(mrp);
        bookMRP.setPaintFlags(bookMRP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return convertView;
    }
}
