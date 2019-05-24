package com.xheghun.esopos.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xheghun.esopos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnlinePOSFragment extends Fragment {


    public OnlinePOSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.online_pos));
    }

    @BindView(R.id.point_of_sale_mode)
    TextView mode;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online_pos, container, false);
        ButterKnife.bind(this,view);

        mode.setText("Point of Sales (Online Selling)");
        mode.setBackgroundColor(getResources().getColor(R.color.colorGreen));
        return view;
    }
}
