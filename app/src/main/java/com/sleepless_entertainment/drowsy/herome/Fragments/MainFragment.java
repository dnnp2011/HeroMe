package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sleepless_entertainment.drowsy.herome.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.herome.R;

public class MainFragment extends Fragment implements View.OnClickListener {

    private String Origin;

    private Button accidentBtn, geneticBtn, bornBtn, chooseBtn;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

//        Check for saved info
        Bundle bundle = this.getArguments();
        String tempOrigin = bundle.getString(MainActivity.ORIGIN_KEY, null);

//        Fetch button references
        accidentBtn = view.findViewById(R.id.accidentOption);
        geneticBtn = view.findViewById(R.id.mutationOption);
        bornBtn = view.findViewById(R.id.bornOption);
        chooseBtn = view.findViewById(R.id.choosePowersBtn);

//        Assign listeners
        accidentBtn.setOnClickListener(this);
        geneticBtn.setOnClickListener(this);
        bornBtn.setOnClickListener(this);

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickNextScreen();
            }
        });

//        Disable the advance button
        chooseBtn.setEnabled(false);
        chooseBtn.getBackground().mutate().setAlpha(128);

        setCheckAlpha(0);
        setButtonAlpha(170);

        if (tempOrigin != null) {
//            Find this button, and call onClick on it
            View savedButton = MainActivity.getMatchingButton(tempOrigin, (ConstraintLayout) view.findViewById(R.id.mainFragmentObj));
            onClick(savedButton);
        }

        return view;
    }
    @Override
    public void onClick(View view) {
        chooseBtn.setEnabled(true);
        chooseBtn.getBackground().setAlpha(255);

        setCheckAlpha(0);
        setButtonAlpha(170);

        view.setBackgroundResource(R.drawable.hero_button_selected);
        view.getBackground().setAlpha(255);

        Button button = (Button) view;
        button.getCompoundDrawablesRelative()[2].mutate().setAlpha(255);

        Origin = button.getText().toString();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).saveToBundle(MainActivity.ORIGIN_KEY, Origin, this);
    }

    private void setCheckAlpha(int value) {
        geneticBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        accidentBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        bornBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
    }

    private void setButtonAlpha(int value) {
        accidentBtn.setBackgroundResource(R.drawable.hero_button);
        geneticBtn.setBackgroundResource(R.drawable.hero_button);
        bornBtn.setBackgroundResource(R.drawable.hero_button);

        accidentBtn.getBackground().mutate().setAlpha(value);
        geneticBtn.getBackground().mutate().setAlpha(value);
        bornBtn.getBackground().mutate().setAlpha(value);
    }

    private void onClickNextScreen() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.loadNextFragment(new PickPowerFragment());
    }

    public interface DataListener {
        void saveToBundle(String key, String data, @Nullable Fragment fragment);
    }
}
