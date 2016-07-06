package network;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.nikhil.bookshare.Book;
import com.nikhil.bookshare.BooksAdapter;
import com.nikhil.bookshare.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nikhil on 19/6/16.
 */
public class GoogleBookAsyncTask extends AsyncTask<Void,Void,String> {
    private Map<String,Object> params;
    private ListView listView;
    private View hintView;

    public GoogleBookAsyncTask(String intitle,ListView listView,View hintView){
        params = new LinkedHashMap<>();
        params.put("q","intitle:"+intitle);
        this.listView = listView;
        this.hintView = hintView;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            HttpRequestHandler booksApiRequest = new HttpRequestHandler("https://www.googleapis.com/books/v1/volumes");
            return booksApiRequest.doRequest("GET",this.params);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if(s==null)
            return;
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray items = jsonObject.getJSONArray("items");
            int itemsLength = (items.length()>10)?10:items.length();
            List<Book> books = new ArrayList<>();
            for (int i=0;i<itemsLength;i++){
                JSONObject volumeInfo = items.getJSONObject(i).getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                String author ="";

                String publisher="";
                String imageURL="";

                if(volumeInfo.has("publisher"))
                    publisher= volumeInfo.getString("publisher");

                if(volumeInfo.has("imageLinks"))
                    imageURL= volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail");

                if(volumeInfo.has("authors"))
                    author = volumeInfo.getJSONArray("authors").getString(0);
                else
                    Log.d("here",title+": authors not found");

                books.add(new Book(title,author,publisher,imageURL,0,0));
            }
            listView.setAdapter(new BooksAdapter(listView.getContext(), R.layout.list_book_layout,books));
            hintView.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
