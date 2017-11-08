package com.zingbyte.batmanfoodorder.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.zingbyte.batmanfoodorder.R;

import java.util.HashMap;

import static java.security.AccessController.getContext;

public class SingleProductDetail extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    TextView textCartItemCount,quantitytxt;
    int mCartItemCount = 10;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    ImageView fav_img,addcart_img,removecart_img;
    int quantityvalue = 0;
    int flag_fav = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single_product_detail);


        addcart_img = (ImageView)findViewById(R.id.addcart);
        removecart_img = (ImageView)findViewById(R.id.removecart);
        quantitytxt = (TextView)findViewById(R.id.quantity);

        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        fav_img = (ImageView)findViewById(R.id.fav);

        Hash_file_maps.put("Android CupCake", "https://image.ibb.co/bLNtyG/image1.png");
        Hash_file_maps.put("Android Donut", "https://image.ibb.co/gbmxQw/image2.png");
        Hash_file_maps.put("Android Eclair", "https://image.ibb.co/cHm8Xb/image3.png");
        Hash_file_maps.put("Android Froyo", "https://image.ibb.co/hJT0dG/image4.png");
        Hash_file_maps.put("Android GingerBread", "https://image.ibb.co/cHm8Xb/image3.png");

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(SingleProductDetail.this);
            textSliderView
                    //.description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
           // textSliderView.getBundle().putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
      //  sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);

        addcart_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCartItemCount = mCartItemCount + 1;
                quantitytxt.setText(""+mCartItemCount);
               // Toast.makeText(SingleProductDetail.this, ""+mCartItemCount, Toast.LENGTH_SHORT).show();
                setupBadge();
            }
        });

        removecart_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCartItemCount = mCartItemCount - 1;
              //  Toast.makeText(SingleProductDetail.this, ""+mCartItemCount, Toast.LENGTH_SHORT).show();
                quantitytxt.setText(""+mCartItemCount);
                setupBadge();
            }
        });

        fav_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag_fav==0){

                    fav_img.setImageDrawable(ActivityCompat.getDrawable(view.getContext(),R.drawable.favfill));
                    flag_fav = 1;
                }
                else
                {
                    fav_img.setImageDrawable(ActivityCompat.getDrawable(view.getContext(),R.drawable.heart));
                    flag_fav = 0;
                }



            }
        });

    }



    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_product, menu);


        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.cart:
                //add the function to perform here
                return(true);

        }
        return(super.onOptionsItemSelected(item));
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0)
            {
                if (textCartItemCount.getVisibility() != View.GONE)
                {
                    textCartItemCount.setVisibility(View.GONE);
                }
            }
            else
            {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE)
                {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
}

}

