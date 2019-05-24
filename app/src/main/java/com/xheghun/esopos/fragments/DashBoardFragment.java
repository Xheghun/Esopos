package com.xheghun.esopos.fragments;


import android.content.Intent;
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
public class DashBoardFragment extends Fragment {

    public DashBoardFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.num_expenses_btn)
    TextView textView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(getString(R.string.dashboard));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if (getActivity() != null && getActivity().getActionBar() != null)
            getActivity().getActionBar().setTitle("Dashboard");

        View view =  inflater.inflate(R.layout.fragment_dash_board, container, false);
        ButterKnife.bind(this,view);
        textView.setOnClickListener(v -> gotoDetails(false,true,true,false,false));

        return  view;
    }

    /*
        The parameter passed in this function will determine if certain view/operation will be functional
        in the next activity.
     */
    void gotoDetails(boolean showDashBoard,boolean addExpense ,boolean excelFunction, boolean marketSales,boolean offlineSales) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("showDashboard",showDashBoard);
        intent.putExtra("addExpense",addExpense);
        intent.putExtra("excelFunction",excelFunction);
        intent.putExtra("marketSales",marketSales);
        intent.putExtra("offlineSales",offlineSales);
        startActivity(intent);
    }
}
