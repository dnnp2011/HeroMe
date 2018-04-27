package com.sleepless_entertainment.drowsy.herome.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sleepless_entertainment.drowsy.herome.Fragments.BackstoryFragment;
import com.sleepless_entertainment.drowsy.herome.Fragments.MainFragment;
import com.sleepless_entertainment.drowsy.herome.Fragments.PickPowerFragment;
import com.sleepless_entertainment.drowsy.herome.R;

public class MainActivity extends FragmentActivity implements MainFragment.MainFragmentInteractionListener, PickPowerFragment.PickPowerInteractionListener, BackstoryFragment.BackstoryFragmentInteractionListener {

//    TODO: Try creating the line layout around MainFragment programmatically
//    TODO: Implement data persistence for MainFragment buttons
//     TODO: Make loadNextFragment a generic Fragment Loader
//    TODO: Implement data hand off between fragments

    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.mainFragmentObj);

        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_file_name), MODE_PRIVATE);

        if (fragment == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);
            transaction.add(R.id.fragment_container, new MainFragment());
            transaction.commit();
        }
    }



    public void loadNextFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, targetFragment);
        getFragmentManager().executePendingTransactions();
        transaction.commit();
    }

    public void relaunchMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onMainFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPickPowerFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackstoryFragmentInteraction(Uri uri) {

    }

    public static HeroOrigin findMatchingOrigin(String value) {
        HeroOrigin returnVal = HeroOrigin.DEFAULT;
        value = value.replace(" ", "_").trim().toUpperCase();
        for (HeroOrigin origin : HeroOrigin.values()) {
            if (String.valueOf(origin).equals(value))
                returnVal = origin;
        }
        return returnVal;
    }

    public static HeroPower findMatchingPower(String value) {
        HeroPower returnVal = HeroPower.DEFAULT;
        value = value.replace(" ", "_").trim().toUpperCase();
        for (HeroPower power : HeroPower.values()) {
            if (String.valueOf(power).equals(value))
                returnVal = power;
        }
        return returnVal;
    }

    public enum HeroOrigin {
        DEFAULT, CAME_BY_ACCIDENT, GENETIC_MUTATION, BORN_WITH_THEM
    }

    public enum HeroPower {
        DEFAULT, TURTLE_POWER, LIGHTNING, FLIGHT, WEB_SLINGING, LASER_VISION, SUPER_STRENGTH
    }
}