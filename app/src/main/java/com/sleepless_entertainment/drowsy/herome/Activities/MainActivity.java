package com.sleepless_entertainment.drowsy.herome.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionValues;

import com.sleepless_entertainment.drowsy.herome.Fragments.MainFragment;
import com.sleepless_entertainment.drowsy.herome.Fragments.PickPowerFragment;
import com.sleepless_entertainment.drowsy.herome.R;

public class MainActivity extends Activity implements MainFragment.MainFragmentInteractionListener, PickPowerFragment.PickPowerInteractionListener {

//    TODO: Try creating the line layout around MainFragment programmatically
//    TODO: Implement data persistence for MainFragment buttons
//    TODO: Implement fragment transition animations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new MainFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//            transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }

    public void loadPickPowerFragment() {
        PickPowerFragment pickPowerFragment = PickPowerFragment.newInstance("","");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

//        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.replace(R.id.fragment_container, pickPowerFragment).addToBackStack(null);


        fragmentTransaction.commit();
    }

    @Override
    public void onMainFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPickPowerFragmentInteraction(Uri uri) {

    }
}
