package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sleepless_entertainment.drowsy.herome.R;

public class PickPowerFragment extends Fragment implements View.OnClickListener {


    private Button turtleBtn, lightningBtn, flightBtn, webBtn, laserBtn, strengthBtn, showBackstoryBtn;
    private Drawable turtleCheck, lightningCheck, flightCheck, webCheck, laserCheck, strengthCheck;


    private PickPowerInteractionListener mListener;

    public PickPowerFragment() {
        // Required empty public constructor
    }

    public static PickPowerFragment newInstance() {
        return new PickPowerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_power, container, false);

//        Fetch button references
        turtleBtn = view.findViewById(R.id.turtleOption);
        lightningBtn = view.findViewById(R.id.lightningOption);
        flightBtn = view.findViewById(R.id.flightOption);
        webBtn = view.findViewById(R.id.webSlingingOption);
        laserBtn = view.findViewById(R.id.laserVisionOption);
        strengthBtn = view.findViewById(R.id.superStrengthOption);
        showBackstoryBtn = view.findViewById(R.id.showBackstoryButton);

//        Assign button listeners
        turtleBtn.setOnClickListener(this);
        lightningBtn.setOnClickListener(this);
        flightBtn.setOnClickListener(this);
        webBtn.setOnClickListener(this);
        laserBtn.setOnClickListener(this);
        strengthBtn.setOnClickListener(this);
        showBackstoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Load next fragment

            }
        });

//        Disable advance button, assign checkmark drawables, make checks invisible and mutated
        showBackstoryBtn.setEnabled(false);
        showBackstoryBtn.getBackground().mutate().setAlpha(128);

        turtleCheck = turtleBtn.getCompoundDrawablesRelative()[2];
        lightningCheck = turtleBtn.getCompoundDrawablesRelative()[2];
        flightCheck = turtleBtn.getCompoundDrawablesRelative()[2];
        webCheck = turtleBtn.getCompoundDrawablesRelative()[2];
        laserCheck = turtleBtn.getCompoundDrawablesRelative()[2];
        strengthCheck = turtleBtn.getCompoundDrawablesRelative()[2];

//        Set Alpha
        setCheckAlpha(0);
        setButtonAlpha(170);

        return view;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onClick(View view) {
//        Make all checks invisible
        setCheckAlpha(0);
        setButtonAlpha(170);

//        Make selected check visible
        Button button = (Button) view;
        button.getBackground().setAlpha(255);
        button.getCompoundDrawables()[2].mutate().setAlpha(255);


//        Activate advance button and make visible
        showBackstoryBtn.setEnabled(true);
        showBackstoryBtn.getBackground().setAlpha(255);

    }

    private void setButtonAlpha(int value) {
        turtleBtn.getBackground().mutate().setAlpha(value);
        lightningBtn.getBackground().mutate().setAlpha(value);
        flightBtn.getBackground().mutate().setAlpha(value);
        webBtn.getBackground().mutate().setAlpha(value);
        laserBtn.getBackground().mutate().setAlpha(value);
        strengthBtn.getBackground().mutate().setAlpha(value);
    }

    private void setCheckAlpha(int value) {
        turtleCheck.mutate().setAlpha(value);
        lightningCheck.mutate().setAlpha(value);
        flightCheck.mutate().setAlpha(value);
        webCheck.mutate().setAlpha(value);
        laserCheck.mutate().setAlpha(value);
        strengthCheck.mutate().setAlpha(value);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PickPowerInteractionListener) {
            mListener = (PickPowerInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface PickPowerInteractionListener {
        void onPickPowerFragmentInteraction(Uri uri);
    }
}
