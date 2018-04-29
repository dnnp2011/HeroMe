package com.sleepless_entertainment.drowsy.herome.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sleepless_entertainment.drowsy.herome.Activities.MainActivity;
import com.sleepless_entertainment.drowsy.herome.R;

public class BackstoryFragment extends Fragment {

    private Button primaryPowerBtn, secondaryPowerBtn;
    private TextView heroNameView, heroBackstoryView;
    private ImageView heroCrestView;
    private String Power, Origin;

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

//        Fetch References
        Button startOverBtn = view.findViewById(R.id.nextFragmentBtn);
        primaryPowerBtn = view.findViewById(R.id.primaryPowerView);
        secondaryPowerBtn = view.findViewById(R.id.secondaryPowerView);
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
        unwrapBundle();
        setHeroInfo(determineHero(Power, Origin));

        return view;
    }

    private void unwrapBundle() {
        Bundle bundle = this.getArguments();
        Power = bundle.getString(MainActivity.POWER_KEY, null);
        Origin = bundle.getString(MainActivity.ORIGIN_KEY, null);
    }

    private Hero determineHero(String power, String origin) {
        if (power.equals(MainActivity.HeroPower.TURTLE_POWER.toString())) {
            return (makeNinjaTurtle());
        }
        else if (power.equals(MainActivity.HeroPower.LIGHTNING.toString())) {
            return (makeThor());
        }
        else if (power.equals(MainActivity.HeroPower.FLIGHT.toString())) {
            return (makeSuperman());
        }
        else if (power.equals(MainActivity.HeroPower.WEB_SLINGING.toString())) {
            return (makeSpiderman());
        }
        else if (power.equals(MainActivity.HeroPower.LASER_VISION.toString())) {
            return (makeCyclops());
        }
        else {
            return (makeHulk());
        }
    }

    private void setHeroInfo(Hero hero) {
        Drawable checkMark = null;
        heroNameView.setText(hero.Name);
        heroBackstoryView.setText(hero.Backstory);
        primaryPowerBtn.setText(hero.PrimaryPower);
        checkMark = primaryPowerBtn.getCompoundDrawablesRelative()[2];
        checkMark.setBounds(0,0,checkMark.getIntrinsicWidth(), checkMark.getIntrinsicHeight());
        primaryPowerBtn.setCompoundDrawablesRelative(setDrawableBounds(hero.PrimaryPowerIcon), null, checkMark, null);
        primaryPowerBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(0);
        secondaryPowerBtn.setText(hero.SecondaryPower);
        secondaryPowerBtn.setCompoundDrawablesRelative(setDrawableBounds(hero.SecondaryPowerIcon), null, checkMark, null);
        secondaryPowerBtn.getCompoundDrawablesRelative()[2].mutate().setAlpha(0);
        heroCrestView.setImageDrawable(hero.Crest);
    }

    private Drawable setDrawableBounds(Drawable drawable) {
        drawable.mutate().setBounds(0,0,(int)(drawable.getIntrinsicWidth()*0.09), (int)(drawable.getIntrinsicHeight()*0.09));
        return drawable;
    }

    private class Hero {
        String Name;
        String Backstory;
        String PrimaryPower;
        String SecondaryPower;
        Drawable Crest;
        Drawable PrimaryPowerIcon;
        Drawable SecondaryPowerIcon;

        public Hero(String name, String backstory, String primaryPower, String secondaryPower,
                    Drawable crest, Drawable primaryPowerIcon, Drawable secondaryPowerIcon) {
            this.Name = name;
            this.Backstory = backstory;
            this.PrimaryPower = primaryPower;
            this.SecondaryPower = secondaryPower;
            this.Crest = crest;
            this.PrimaryPowerIcon = primaryPowerIcon;
            this.SecondaryPowerIcon = secondaryPowerIcon;
        }
    }

    private Hero makeNinjaTurtle() {
        return new Hero(
            "Raphael",
                getString(R.string.ninja_turtle_story),
                "Martial Arts",
                "Regeneration",
                getResources().getDrawable(R.drawable.turtle_power3x, null),
                getResources().getDrawable(R.drawable.nunchuks, null),
                getResources().getDrawable(R.drawable.turtleshell, null)

        );
    }
    private Hero makeThor() {
        return new Hero(
                "Thor",
                getString(R.string.thor_story),
                "Lightning",
                "Thor's Hammer",
                getResources().getDrawable(R.drawable.thors_hammer3x, null),
                getResources().getDrawable(R.drawable.lightning, null),
                getResources().getDrawable(R.drawable.hammer, null)

        );
    }
    private Hero makeSuperman() {
        return new Hero(
                "Superman",
                getString(R.string.superman_story),
                "Flight",
                "Super Strength",
                getResources().getDrawable(R.drawable.super_man_crest3x, null),
                getResources().getDrawable(R.drawable.bird, null),
                getResources().getDrawable(R.drawable.superman_crest, null)

        );
    }
    private Hero makeSpiderman() {
        return new Hero(
                "Spider-Man",
                getString(R.string.spiderman_story),
                "Web Slinging",
                "Endurance",
                getResources().getDrawable(R.drawable.spiderweb3x, null),
                getResources().getDrawable(R.drawable.spider_web, null),
                getResources().getDrawable(R.drawable.spider, null)

        );
    }
    private Hero makeCyclops() {
        return new Hero(
                "Cyclops",
                getString(R.string.cyclops_story),
                "Laser Vision",
                "Teamwork",
                getResources().getDrawable(R.drawable.laservision3x, null),
                getResources().getDrawable(R.drawable.laser_visor, null),
                getResources().getDrawable(R.drawable.xmen, null)

        );
    }
    private Hero makeHulk() {
        return new Hero(
                "The Hulk",
                getString(R.string.hulk_story),
                "Super Strength",
                "Invincible",
                getResources().getDrawable(R.drawable.superstrength3x, null),
                getResources().getDrawable(R.drawable.hulk_fist, null),
                getResources().getDrawable(R.drawable.radioactive, null)

        );
    }

}
