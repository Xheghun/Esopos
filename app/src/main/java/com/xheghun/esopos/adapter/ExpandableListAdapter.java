package com.xheghun.esopos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.xheghun.esopos.R;
import com.xheghun.esopos.model.MenuModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by anupamchugh on 22/12/17.
 */


public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Context context;
	private List<MenuModel> listDataHeader;
	private HashMap<MenuModel, List<MenuModel>> listDataChild;

	public ExpandableListAdapter(Context context, List<MenuModel> listDataHeader,
								 HashMap<MenuModel, List<MenuModel>> listChildData) {
		this.context = context;
		this.listDataHeader = listDataHeader;
		this.listDataChild = listChildData;
	}

	@Override
	public MenuModel getChild(int groupPosition, int childPosititon) {
		return this.listDataChild.get(this.listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {

		MenuModel child = getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_child, null);
		}

		TextView txtListChild = convertView
				.findViewById(R.id.lblListItem);

		txtListChild.setText(child.menuName);
		txtListChild.setCompoundDrawablePadding(10)
		;
		txtListChild.setCompoundDrawablesWithIntrinsicBounds(child.drawableStart, 0, 0, 0);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {

		if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
			return 0;
		else
			return this.listDataChild.get(this.listDataHeader.get(groupPosition))
					.size();
	}

	@Override
	public MenuModel getGroup(int groupPosition) {
		return this.listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listDataHeader.size();

	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}


	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {
		MenuModel header = getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group_header, null);
		}

		TextView lblListHeader = convertView.findViewById(R.id.list_group_header);
		//lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(header.menuName);
		lblListHeader.setCompoundDrawablePadding(10);

		if (isExpanded && header.hasChildren)
			lblListHeader.setCompoundDrawablesWithIntrinsicBounds(header.drawableStart, 0, R.drawable.ic_keyboard_arrow_down, 0);
		else
			lblListHeader.setCompoundDrawablesWithIntrinsicBounds(header.drawableStart, 0, header.drawableEnd, 0);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}