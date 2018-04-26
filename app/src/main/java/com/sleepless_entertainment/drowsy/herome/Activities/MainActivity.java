package com.sleepless_entertainment.drowsy.herome.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sleepless_entertainment.drowsy.herome.Fragments.MainFragment;
import com.sleepless_entertainment.drowsy.herome.Fragments.PickPowerFragment;
import com.sleepless_entertainment.drowsy.herome.R;

public class MainActivity extends FragmentActivity implements MainFragment.MainFragmentInteractionListener, PickPowerFragment.PickPowerInteractionListener {

//    TODO: Try creating the line layout around MainFragment programmatically
//    TODO: Implement data persistence for MainFragment buttons
//    TODO: Implement fragment transition animations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.mainFragmentObj);

        if (fragment == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);
            transaction.add(R.id.fragment_container, new MainFragment());
            transaction.commit();
        }
    }

    public void loadPickPowerFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//        TODO: PickPowerFragment doesn't enter from the left as it should

//        HACK: New is Entering LEFT; REVERSE: New is Entering RIGHT
//        TODO: Switch Enter right with enter left
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, PickPowerFragment.newInstance());
        getFragmentManager().executePendingTransactions();
        transaction.commit();
    }

    @Override
    public void onMainFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPickPowerFragmentInteraction(Uri uri) {

    }
}
