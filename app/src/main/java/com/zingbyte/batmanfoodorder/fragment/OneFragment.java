package com.zingbyte.batmanfoodorder.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.zingbyte.batmanfoodorder.R;
import com.zingbyte.batmanfoodorder.activity.SingleProductDetail;


import java.util.HashMap;

public class OneFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    View view;
    SliderLayout sliderLayout;
    HashMap<String,String> Hash_file_maps ;
    OneFragment mContext = this;
    public OneFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_one, container, false);

        Hash_file_maps = new HashMap<String, String>();
        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);

        Hash_file_maps.put("Android CupCake", "https://image.ibb.co/bLNtyG/image1.png");
        Hash_file_maps.put("Android Donut", "https://image.ibb.co/gbmxQw/image2.png");
        Hash_file_maps.put("Android Eclair", "https://image.ibb.co/cHm8Xb/image3.png");
        Hash_file_maps.put("Android Froyo", "https://image.ibb.co/hJT0dG/image4.png");
        Hash_file_maps.put("Android GingerBread", "https://image.ibb.co/cHm8Xb/image3.png");

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity());
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
        return view;
    }


    @Override
    public void onStop() {

        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

}
