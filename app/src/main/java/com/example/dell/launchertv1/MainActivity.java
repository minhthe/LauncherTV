package com.example.dell.launchertv1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Switch;

public class MainActivity extends Activity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    String arrName[]={"Truyền Hình","Phim", "Âm Nhạc","Youtube"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getImages());
        gridView.setAdapter(gridAdapter);
//this iss for test
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);

                switch (item.getTitle()) {
                    case "Phim":
                    {
                        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                        startActivity(intent);
                        break;

                    }
                    case "Âm Nhạc":
                    {
                        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "Truyền Hình":
                    {
                        Intent intent = new Intent(MainActivity.this, TVActivity.class);
                        startActivity(intent);
                        break;
                    }

                    default:
                    {
                        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                        if (launchIntent != null) {
                            startActivity(launchIntent);//null pointer check in case package name was not found
                        }
                        break;

                    }
                }
                //Create intent
               // Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                //intent.putExtra("title", item.getTitle());
                //intent.putExtra("image", item.getImage());

                //Start details activity
                //startActivity(intent);
              //  Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
               // if (launchIntent != null) {
                //    startActivity(launchIntent);//null pointer check in case package name was not found
               // }
            }
        });
    }

    /**
     * Prepare some dummy data for gridview
     */
    private ArrayList<ImageItem> getImages() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, arrName[i]));
        }

        return imageItems;
    }
}