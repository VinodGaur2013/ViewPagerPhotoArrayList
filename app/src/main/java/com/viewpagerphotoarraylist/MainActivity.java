package com.viewpagerphotoarraylist;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> images;

    private BitmapFactory.Options options;
    private ViewPager viewPager;
    private View btnNext, btnPrev;
    private Pager_Adapter adapter;
   // private LinearLayout thumbnailsContainer;
    private final static int[] resourceIDs = new int[]{R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four,R.drawable.five, R.drawable.one, R.drawable.two,
           R.drawable.three, R.drawable.four,R.drawable.five};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images = new ArrayList<>();

        //find view by id
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //thumbnailsContainer = (LinearLayout) findViewById(R.id.container);
        btnNext = findViewById(R.id.next);
        btnPrev = findViewById(R.id.prev);

        btnPrev.setOnClickListener(onClickListener(0));
        btnNext.setOnClickListener(onClickListener(1));

        setImagesData();

        // init viewpager adapter and attach
        adapter = new Pager_Adapter(getSupportFragmentManager(), images);
        viewPager.setAdapter(adapter);

        inflateThumbnails();
    }

    private View.OnClickListener onClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    //next page
                    if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                } else {
                    //previous page
                    if (viewPager.getCurrentItem() > 0) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    }
                }
            }
        };
    }

    private void setImagesData() {
        for (int i = 0; i < resourceIDs.length; i++) {
            images.add(resourceIDs[i]);
        }
    }

    private void inflateThumbnails() {
        for (int i = 0; i < images.size(); i++) {
            View imageLayout = getLayoutInflater().inflate(R.layout.item_image, null);
            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.img_thumb);
            imageView.setOnClickListener(onChagePageClickListener(i));
            options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            options.inDither = false;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images.get(i), options );
            imageView.setImageBitmap(bitmap);
            //set to image view
            imageView.setImageBitmap(bitmap);
            //add imageview
            //thumbnailsContainer.addView(imageLayout);
        }
    }

    private View.OnClickListener onChagePageClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(i);
            }
        };
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawer_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_delete:
                deleteImage();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void deleteImage() {

        int position = viewPager.getCurrentItem();
        if (position>=0) {
            images.remove(position);
            Toast.makeText(this, "deleted..." + position, Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            viewPager.setAdapter(null);
            viewPager.setAdapter(adapter);
        }else{
            Toast.makeText(this, "no image found to delete...", Toast.LENGTH_SHORT).show();
        }
      /*  int currnt =viewPager.getCurrentItem();
        viewPager.refreshDrawableState();
        viewPager.setCurrentItem(currnt);*/
    }


}

