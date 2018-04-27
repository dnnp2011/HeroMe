package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;

import com.sleepless_entertainment.drowsy.herome.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.herome.R;

public class BackstoryFragment extends Fragment {

    private Button primaryPowerBtn, secondaryPowerBtn, startOverBtn;
    private View heroNameView, heroCrestView, heroBackstoryView;

    private BackstoryFragmentInteractionListener mListener;

    public BackstoryFragment() {
        // Required empty public constructor
    }

    public static BackstoryFragment newInstance() {
        return new BackstoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backstory, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        SharedPreferences preferences = mainActivity.sharedPreferences;
        MainActivity.HeroOrigin origin = MainActivity.HeroOrigin.valueOf(preferences.getString("HeroOrigin", "DEFAULT"));
        MainActivity.HeroPower power = MainActivity.HeroPower.valueOf(preferences.getString("HeroPower", "DEFAULT"));

        System.out.println("Hero Origin: " + String.valueOf(origin) + " Hero Power: " + String.valueOf(power));

//        Fetch References
        primaryPowerBtn = view.findViewById(R.id.primaryPowerView);
        secondaryPowerBtn = view.findViewById(R.id.secondaryPowerView);
        startOverBtn = view.findViewById(R.id.nextFragmentBtn);
        heroNameView = view.findViewById(R.id.heroNameView);
        heroCrestView = view.findViewById(R.id.heroCrestView);
        heroBackstoryView = view.findViewById(R.id.backstoryView);

//        Assign Listeners
        startOverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Load first fragment
                ((MainActivity) getActivity()).relaunchMainActivity(getContext());
            }
        });

//        Activate / Deactivate

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BackstoryFragmentInteractionListener) {
            mListener = (BackstoryFragmentInteractionListener) context;
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

    @Override
    public void onResume() {
        super.onResume();
//        Set Hero Info Here also
    }

    public interface BackstoryFragmentInteractionListener {
        // TODO: Update argument type and name
        void onBackstoryFragmentInteraction(Uri uri);
    }

    private void setHeroInfo(MainActivity.HeroOrigin origin, MainActivity.HeroPower power) {
//        Determine Hero Type and fill in info
    }
}
