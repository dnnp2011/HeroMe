package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

    public MainActivity.HeroOrigin Origin;

    private Button accidentBtn, geneticBtn, bornBtn, chooseBtn;
    private SharedPreferences preferences;

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
        preferences = ((MainActivity) getActivity()).sharedPreferences;
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

        Origin = MainActivity.findMatchingOrigin(button.getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
        Origin = MainActivity.HeroOrigin.valueOf(preferences.getString("HeroOrigin", "DEFAULT"));
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("HeroOrigin", String.valueOf(Origin));
        editor.apply();
    }

    private void setCheckAlpha(int value) {
        geneticBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        accidentBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
        bornBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(value);
    }

    private void setButtonAlpha(int value) {
        accidentBtn.getBackground().mutate().setAlpha(value);
        geneticBtn.getBackground().mutate().setAlpha(value);
        bornBtn.getBackground().mutate().setAlpha(value);

        accidentBtn.setBackgroundResource(R.drawable.hero_button);
        geneticBtn.setBackgroundResource(R.drawable.hero_button);
        bornBtn.setBackgroundResource(R.drawable.hero_button);
    }

    private void onClickNextScreen() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.loadNextFragment(new PickPowerFragment());
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
