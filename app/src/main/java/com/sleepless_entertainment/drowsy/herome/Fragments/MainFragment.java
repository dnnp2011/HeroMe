package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.*;

import com.sleepless_entertainment.drowsy.herome.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.herome.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private Drawable accidentCheck, geneticCheck, bornCheck;
    private Button accidentBtn, geneticBtn, bornBtn, chooseBtn;

    private MainFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

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

        accidentCheck = accidentBtn.getCompoundDrawablesRelative()[2];
        geneticCheck = geneticBtn.getCompoundDrawablesRelative()[2];
        bornCheck = bornBtn.getCompoundDrawablesRelative()[2];

        accidentCheck.mutate().setAlpha(0);
        geneticCheck.mutate().setAlpha(0);
        bornCheck.mutate().setAlpha(0);

        accidentBtn.getBackground().mutate().setAlpha(170);
        geneticBtn.getBackground().mutate().setAlpha(170);
        bornBtn.getBackground().mutate().setAlpha(170);

        return view;
    }
    @Override
    public void onClick(View view) {
        chooseBtn.setEnabled(true);
        chooseBtn.getBackground().setAlpha(255);

        switch (view.getId()) {
            case R.id.accidentOption:
                System.out.println("Clicked Accident Option");
                accidentCheck.setAlpha(255);
                geneticCheck.setAlpha(0);
                bornCheck.setAlpha(0);
                accidentBtn.getBackground().setAlpha(255);
                geneticBtn.getBackground().setAlpha(128);
                bornBtn.getBackground().setAlpha(128);
                break;
            case R.id.mutationOption:
                System.out.println("Clicked Mutation Option");
                geneticCheck.setAlpha(255);
                accidentCheck.setAlpha(0);
                bornCheck.setAlpha(0);
                accidentBtn.getBackground().setAlpha(128);
                geneticBtn.getBackground().setAlpha(255);
                bornBtn.getBackground().setAlpha(128);
                break;
            case R.id.bornOption:
                System.out.println("Clicked Born Option");
                bornCheck.setAlpha(255);
                accidentCheck.setAlpha(0);
                geneticCheck.setAlpha(0);
                accidentBtn.getBackground().setAlpha(128);
                geneticBtn.getBackground().setAlpha(128);
                bornBtn.getBackground().setAlpha(255);
                break;
            default:
                System.out.println("No ID matched in switch statement");
                break;
        }
    }

    private void onClickNextScreen() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.loadPickPowerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainFragmentInteractionListener) {
            mListener = (MainFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface MainFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMainFragmentInteraction(Uri uri);
    }
}
