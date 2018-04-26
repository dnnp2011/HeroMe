package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    private Drawable accidentCheck;
    private Drawable geneticCheck;
    private Drawable bornCheck;
    private View selectedOption;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button accidentBtn, geneticBtn, bornBtn, chooseBtn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
                ((MainActivity) getActivity()).loadPickPowerFragment();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMainFragmentInteraction(uri);
        }
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

//    @Override
//    public void onClick(View view) {
//        chooseBtn.setEnabled(true);
//        chooseBtn.getBackground().setAlpha(255);
//
//        Button button = (Button) view;
//
//        switch (view.getId()) {
//            case R.id.accidentOption:
//                accidentBtn.getCompoundDrawables()[2].setAlpha(255);
//                geneticBtn.getCompoundDrawables()[2].setAlpha(0);
//                bornBtn.getCompoundDrawables()[2].setAlpha(0);
//                break;
//            case R.id.mutationOption:
//                accidentBtn.getCompoundDrawables()[2].setAlpha(0);
//                geneticBtn.getCompoundDrawables()[2].setAlpha(255);
//                bornBtn.getCompoundDrawables()[2].setAlpha(0);
//                break;
//            case R.id.bornOption:
//                accidentBtn.getCompoundDrawables()[2].setAlpha(0);
//                geneticBtn.getCompoundDrawables()[2].setAlpha(0);
//                bornBtn.getCompoundDrawables()[2].setAlpha(255);
//                break;
//            default:
//                System.out.println("No ID matched in switch statement");
//                break;
//        }
//
////        if (button.getCompoundDrawables()[2].getAlpha() == 0)
////            button.getCompoundDrawables()[2].setAlpha(255);
////        else
////            button.getCompoundDrawables()[2].setAlpha(0);
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface MainFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMainFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View view) {
            chooseBtn.setEnabled(true);
            chooseBtn.getBackground().setAlpha(255);

            selectedOption = view;

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
}
