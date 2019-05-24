package com.xheghun.esopos;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.google.android.material.button.MaterialButton;
import com.xheghun.esopos._interface.NavigationHost;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuyerFragment extends Fragment {
    @BindView(R.id.form_1)
    View form_1;
    @BindView(R.id.form_2)
    View form_2;
    @BindView(R.id.form_3)
    View form_3;
    @BindView(R.id.next_btn)
    MaterialButton next_btn;
    @BindView(R.id.prev_btn)
    MaterialButton prev_btn;



    @BindView(R.id.esopos_buyer_frag_root)
    ViewGroup root;

    public BuyerFragment() {
        // Required empty public constructor
    }

    boolean form1_isVisible;
    boolean form2_isVisible;
    boolean form3_isVisible;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_buyer, container, false);
        ButterKnife.bind(this,view);

        Slide slide = new Slide(Gravity.START);
        slide.setDuration(500);
        slide.setInterpolator(new AccelerateDecelerateInterpolator());


        next_btn.setOnClickListener(v -> {
            form1_isVisible = true;
            if (form1_isVisible) {
                TransitionManager.beginDelayedTransition(root,slide);
                form_1.setVisibility(View.INVISIBLE);


                form_2.setVisibility(View.VISIBLE);

                form_1.setVisibility(View.GONE);


                prev_btn.setVisibility(View.VISIBLE);

                form2_isVisible = true;
                form1_isVisible = false;
                form3_isVisible = false;

            }else if (form2_isVisible) {
                TransitionManager.beginDelayedTransition(root,slide);
                form_2.setVisibility(View.INVISIBLE);
                form_3.setVisibility(View.VISIBLE);
                form_2.setVisibility(View.GONE);
                next_btn.setText("Submit");
                form3_isVisible = true;
                form2_isVisible = false;
                form1_isVisible = false;
            }
        });
        prev_btn.setOnClickListener(v ->{
            if (form2_isVisible) {
                TransitionManager.beginDelayedTransition(root,slide);
                form_2.setVisibility(View.INVISIBLE);

                form_1.setVisibility(View.VISIBLE);


                form_2.setVisibility(View.GONE);
                prev_btn.setVisibility(View.INVISIBLE);

                form1_isVisible = true;
                form2_isVisible = false;
                form3_isVisible = false;

            } else if (form3_isVisible) {
                TransitionManager.beginDelayedTransition(root,slide);
                form_3.setVisibility(View.INVISIBLE);
                form_2.setVisibility(View.VISIBLE);
                form_3.setVisibility(View.GONE);
                next_btn.setText("submit");
                form2_isVisible = true;
                form3_isVisible = false;
                form1_isVisible = false;
            }
        });

        return view;
    }
    @OnClick(R.id.seller)
    public void gotoSeller() {
        ((NavigationHost)getActivity()).navigateTo(new SellerFragment(), true);
    }
}
