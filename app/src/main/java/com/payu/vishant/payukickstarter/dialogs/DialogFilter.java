package com.payu.vishant.payukickstarter.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.payu.vishant.payukickstarter.R;
import com.payu.vishant.payukickstarter.models.KickStarter;

import java.util.ArrayList;

/**
 * Created by Vishant on 8/14/2017.
 */

public class DialogFilter extends DialogFragment implements CompoundButton.OnCheckedChangeListener {

    static ArrayList<KickStarter> kickStarterArrayList;
    static ArrayList<Integer> number_of_backers;
    ArrayList<Integer> checkBoxIdList;
    private static LinearLayout ll_dialog_filter;

    public static DialogFilter newInstance(ArrayList<KickStarter> kickStarters) {
        DialogFilter fragment = new DialogFilter();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        kickStarterArrayList = kickStarters;
        number_of_backers = new ArrayList<>();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.dialog_filter, null);
        ll_dialog_filter = root_view.findViewById(R.id.ll_dialog_filter);

        for(KickStarter kickStarter : kickStarterArrayList){
            if(!number_of_backers.contains(kickStarter.getNum_backers())){
                number_of_backers.add(kickStarter.getNum_backers());
            }
        }

        for(int no_backers : number_of_backers){
            CheckBox ch = new CheckBox(getContext());
            ch.setText(String.valueOf(no_backers));
            ch.setChecked(true);
            ch.setOnCheckedChangeListener(this);
            ll_dialog_filter.addView(ch);
        }

        return root_view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}


