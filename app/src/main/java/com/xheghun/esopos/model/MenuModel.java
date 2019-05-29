package com.xheghun.esopos.model;

import androidx.fragment.app.Fragment;

public class MenuModel {
	public String menuName;
	public boolean hasChildren;
	public boolean isGroup;
	public Fragment fragment;
	public int drawableStart;
	public int drawableEnd;

	public MenuModel(String menuName, boolean hasChildren, boolean isGroup, Fragment fragment, int drawableStart, int drawableEnd) {
		this.menuName = menuName;
		this.hasChildren = hasChildren;
		this.isGroup = isGroup;
		this.fragment = fragment;
		this.drawableStart = drawableStart;
		this.drawableEnd = drawableEnd;
	}
}