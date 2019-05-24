package com.xheghun.esopos.fragments;


import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.animation.AnimationUtils;
import com.xheghun.esopos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */


public class OfflineHelpFragment extends Fragment {
    @BindView(R.id.selling_offline)
    TextView selling_offline;
    @BindView(R.id.selling_offline_content)
    ViewGroup selling_offline_content;

    @BindView(R.id.offline_config)
    TextView offline_config;
    @BindView(R.id.offline_config_content)
    ViewGroup offline_config_content;

    @BindView(R.id.offline_sell)
    TextView offline_sell;
    @BindView(R.id.offline_sell_content)
    ViewGroup offline_sell_content;

    @BindView(R.id.offline_server)
    TextView offline_server;

    @BindView(R.id.offline_server_content)
    ViewGroup offline_server_content;

    private Drawable drawable_minus;
    private Slide slide;

    LinearLayout root;

    public OfflineHelpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.offline_hlp));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offline_help, container, false);
        ButterKnife.bind(this, view);

        slide = new Slide(Gravity.TOP);
        slide.setDuration(200);
        slide.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);

        drawable_minus = getResources().getDrawable(R.drawable.ic_collapse_minus);

        selling_offline.setOnClickListener(v -> {
            root = view.findViewById(R.id.selling_offline_content);
            selling_offline.setCompoundDrawables(drawable_minus,null
            ,null,null);
            animateVisibility(View.VISIBLE,View.GONE,View.GONE,View.GONE,root);
        });
        offline_config.setOnClickListener(v -> {
            root = view.findViewById(R.id.offline_config_content);
            offline_config.setCompoundDrawables(drawable_minus,null,null,null);
            animateVisibility(View.GONE,View.VISIBLE,View.GONE,View.GONE,root);
        });
        offline_sell.setOnClickListener(v -> {
            root = view.findViewById(R.id.offline_sell_content);
            offline_sell.setCompoundDrawables(drawable_minus,null,null,null);
            animateVisibility(View.GONE,View.GONE,View.VISIBLE,View.GONE,root);
        });
        offline_server.setOnClickListener(v -> {
            root = view.findViewById(R.id.offline_server_content);
            offline_server.setCompoundDrawables(drawable_minus,null,null,null);
            animateVisibility(View.GONE,View.GONE,View.GONE,View.VISIBLE,root);
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateVisibility(int selling_offline_visible, int offline_config_visible,
                                   int sell_offline_visible, int offline_server_visible,ViewGroup root) {
        TransitionManager.beginDelayedTransition(root, slide);
        selling_offline_content.setVisibility(selling_offline_visible);
        offline_config_content.setVisibility(offline_config_visible);
        offline_sell_content.setVisibility(sell_offline_visible);
        offline_server_content.setVisibility(offline_server_visible);
    }

}
