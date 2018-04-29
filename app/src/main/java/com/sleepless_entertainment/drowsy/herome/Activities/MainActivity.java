package com.sleepless_entertainment.drowsy.herome.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.sleepless_entertainment.drowsy.herome.Fragments.MainFragment;
import com.sleepless_entertainment.drowsy.herome.R;

public class MainActivity extends FragmentActivity implements MainFragment.DataListener {

//    TODO: Try creating the line layout around MainFragment programmatically
//    TODO: Add more Backstories
//    TODO: Fix Icon sizes
//    TODO: Reset saved values on "start over"

    private SharedPreferences preferences;
    private Bundle dataBundle;

    public static final String ORIGIN_KEY = "hero_origin";
    public static final String POWER_KEY = "hero_power";
    public static Context context;

    //region Data Communcation
    @Override
    public void saveToBundle(String key, String data, @Nullable Fragment fragment) {
        getDataBundle().putString(key, data);
        if (fragment != null) {
            fragment.setArguments(getDataBundle());
        }
    }

    private Bundle getDataBundle() {
        if (dataBundle == null)
        {
            dataBundle = new Bundle();
            dataBundle.putString(ORIGIN_KEY, getPreferences().getString(ORIGIN_KEY, null));
            dataBundle.putString(POWER_KEY, getPreferences().getString(POWER_KEY, null));
        }
        return dataBundle;
    }

    private SharedPreferences getPreferences() {
        if (preferences == null) {
            preferences = getSharedPreferences(getString(R.string.shared_pref_file_name), MODE_PRIVATE);
        }
        return preferences;
    }

    public static View getMatchingButton(String comparer, ConstraintLayout mainLayout) {
        for(int i = 0; i < mainLayout.getChildCount(); i++) {
            View child = mainLayout.getChildAt(i);
            if (child.getClass().equals(Button.class) && ((Button) child).getText().toString().equals(comparer)) {
                return child;
            }
        }
        return null;
    }
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.mainFragmentObj);

        if (fragment == null) {
            Fragment targetFragment = new MainFragment();
            targetFragment.setArguments(getDataBundle());
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_top);
            transaction.add(R.id.fragment_container, targetFragment);
            transaction.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ORIGIN_KEY, getDataBundle().getString(ORIGIN_KEY, null));
        editor.putString(POWER_KEY, getDataBundle().getString(POWER_KEY, null));
        editor.apply();
    }

    public void loadNextFragment(Fragment targetFragment) {
        targetFragment.setArguments(getDataBundle());
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


    public enum HeroOrigin {
        DEFAULT, CAME_BY_ACCIDENT, GENETIC_MUTATION, BORN_WITH_THEM;

        @Override
        public String toString() {
            switch (this) {
                case DEFAULT:
                    return "Default";
                case CAME_BY_ACCIDENT:
                    return context.getString(R.string.came_by_accident);
                case GENETIC_MUTATION:
                    return context.getString(R.string.genetic_mutation);
                case BORN_WITH_THEM:
                    return context.getString(R.string.born_with_them);
                default:
                    return "Error";
            }
        }
    }

    public enum HeroPower {
        DEFAULT, TURTLE_POWER, LIGHTNING, FLIGHT, WEB_SLINGING, LASER_VISION, SUPER_STRENGTH;

        @Override
        public String toString() {
            switch (this) {
                case DEFAULT:
                    return "Default";
                case TURTLE_POWER:
                    return context.getString(R.string.turtle_power);
                case LIGHTNING:
                    return context.getString(R.string.lightning);
                case FLIGHT:
                    return context.getString(R.string.flight);
                case WEB_SLINGING:
                    return context.getString(R.string.web_slinging);
                case LASER_VISION:
                    return context.getString(R.string.laser_vision);
                case SUPER_STRENGTH:
                    return context.getString(R.string.super_strength);
                default:
                    return "Error";
            }
        }
    }
}