package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sleepless_entertainment.drowsy.herome.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.herome.R;

public class PickPowerFragment extends Fragment implements View.OnClickListener {

    public MainActivity.HeroPower Power;

    private Button turtleBtn, lightningBtn, flightBtn, webBtn, laserBtn, strengthBtn, showBackstoryBtn;
    private SharedPreferences preferences;

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
        preferences = ((MainActivity) getActivity()).sharedPreferences;

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

        Power = MainActivity.findMatchingPower(button.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Power = MainActivity.HeroPower.valueOf(preferences.getString("HeroPower", "DEFAULT"));
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("HeroPower", String.valueOf(Power));
        editor.apply();
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
        turtleBtn.getBackground().mutate().setAlpha(value);
        lightningBtn.getBackground().mutate().setAlpha(value);
        flightBtn.getBackground().mutate().setAlpha(value);
        webBtn.getBackground().mutate().setAlpha(value);
        laserBtn.getBackground().mutate().setAlpha(value);
        strengthBtn.getBackground().mutate().setAlpha(value);

        turtleBtn.setBackgroundResource(R.drawable.hero_button);
        lightningBtn.setBackgroundResource(R.drawable.hero_button);
        flightBtn.setBackgroundResource(R.drawable.hero_button);
        webBtn.setBackgroundResource(R.drawable.hero_button);
        laserBtn.setBackgroundResource(R.drawable.hero_button);
        strengthBtn.setBackgroundResource(R.drawable.hero_button);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PickPowerInteractionListener) {
            mListener = (PickPowerInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BackstoryFragmentInteractionListener");
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
