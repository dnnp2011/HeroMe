package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sleepless_entertainment.drowsy.herome.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.herome.R;

public class PickPowerFragment extends Fragment implements View.OnClickListener {

    private Button turtleBtn, lightningBtn, flightBtn, webBtn, laserBtn, strengthBtn, showBackstoryBtn;

    private String Power;

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

//        Check for saved info
        Bundle bundle = this.getArguments();
        String tempPower = bundle.getString(MainActivity.POWER_KEY, null);

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
                ((MainActivity) getActivity()).loadNextFragment(BackstoryFragment.newInstance());
            }
        });

//        Disable advance button, assign checkmark drawables, make checks invisible and mutated
        showBackstoryBtn.setEnabled(false);
        showBackstoryBtn.getBackground().mutate().setAlpha(128);

        setCheckAlpha(0);
        setButtonAlpha(170);

        if (tempPower != null) {
//            Find this button, and call onClick on it
            View savedButton = MainActivity.getMatchingButton(tempPower, (ConstraintLayout) view.findViewById(R.id.pickPowerFrame));
            onClick(savedButton);
        }

        return view;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Override
    public void onClick(View view) {
//        Activate advance button and make visible
        showBackstoryBtn.setEnabled(true);
        showBackstoryBtn.getBackground().setAlpha(255);

//        Make all checks invisible
        setCheckAlpha(0);
        setButtonAlpha(170);

//        Make selected check visible
        Button button = (Button) view;
        button.getCompoundDrawablesRelative()[2].mutate().setAlpha(255);
        view.getBackground().mutate().setAlpha(255);
        view.setBackgroundResource(R.drawable.hero_button_selected);

        Power = button.getText().toString();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).saveToBundle(MainActivity.POWER_KEY, Power, this);
    }

    private void setCheckAlpha(int value) {
        turtleBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        lightningBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        flightBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        webBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        laserBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        strengthBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
    }

    private void setButtonAlpha(int value) {
        turtleBtn.setBackgroundResource(R.drawable.hero_button);
        lightningBtn.setBackgroundResource(R.drawable.hero_button);
        flightBtn.setBackgroundResource(R.drawable.hero_button);
        webBtn.setBackgroundResource(R.drawable.hero_button);
        laserBtn.setBackgroundResource(R.drawable.hero_button);
        strengthBtn.setBackgroundResource(R.drawable.hero_button);

        turtleBtn.getBackground().mutate().setAlpha(value);
        lightningBtn.getBackground().mutate().setAlpha(value);
        flightBtn.getBackground().mutate().setAlpha(value);
        webBtn.getBackground().mutate().setAlpha(value);
        laserBtn.getBackground().mutate().setAlpha(value);
        strengthBtn.getBackground().mutate().setAlpha(value);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
