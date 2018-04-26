package com.sleepless_entertainment.drowsy.herome.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;

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
            manager.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }

    public void loadPickPowerFragment() {
        PickPowerFragment pickPowerFragment = PickPowerFragment.newInstance("","");
        this.getFragmentManager().beginTransaction().replace(R.id.fragment_container, pickPowerFragment).addToBackStack(null).commit();
    }

    @Override
    public void onMainFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPickPowerFragmentInteraction(Uri uri) {

    }
}
