package com.xheghun.esopos.fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xheghun.esopos.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreTimelineFragment extends Fragment {

	@BindView(R.id.timeline_info)
	TextView info;
	private AlertDialog alertDialog;

	public StoreTimelineFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		Objects.requireNonNull(getActivity()).setTitle(getString(R.string.store_timeline));
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_store_timeline, container, false);
		ButterKnife.bind(this,view);

		info.setOnClickListener(v -> {
			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			builder.setTitle("Note");
			builder.setMessage(getString(R.string.timeline_note));
			 alertDialog = builder.create();
			alertDialog.show();

		});
		return view;
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.profile_menu,menu);
	}
}
