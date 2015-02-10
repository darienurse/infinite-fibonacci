package com.FibonacciGenerator;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ArrayAdapter<Long> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final ListView listview = (ListView) findViewById(R.id.listView);
        /*
        * Initialize the fibonacci generator. Initial values are generated based on the screen size. More values
        * are generated as the user scrolls down.
        */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;
        final FibonacciGenerator fibGen = new FibonacciGenerator(screenHeight);

        adapter = new ArrayAdapter<Long>(this, android.R.layout.simple_list_item_1, fibGen.getCache()){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                if(position>=fibGen.getOverflowIndex()) {
                    /* Sets the text color of items in the list at and below the overflow index to red.*/
                    text.setTextColor(Color.RED);
                }
                else{
                    text.setTextColor(Color.WHITE);
                }
                return view;
            }
        };
        listview.setAdapter(adapter);
        listview.setOnScrollListener(new InfiniteScrollListener(5) {
            @Override
            public void loadMore(int page, int totalItemsCount) {
                fibGen.getFibonacci(totalItemsCount);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
