package com.handmark.pulltorefresh.library;

import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ExpandableListView;

public class PullToRefreshExpandableListView extends PullToRefreshAdapterViewBase<ExpandableListView> {
	private int value;

	class InternalExpandableListView extends ExpandableListView implements EmptyViewMethodAccessor {

		public InternalExpandableListView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		public void setEmptyView(View emptyView) {
			PullToRefreshExpandableListView.this.setEmptyView(emptyView);
		}

		@Override
		public void setEmptyViewInternal(View emptyView) {
			super.setEmptyView(emptyView);
		}

		public ContextMenuInfo getContextMenuInfo() {
			return super.getContextMenuInfo();
		}
	}

	public PullToRefreshExpandableListView(Context context) {
		super(context);
	}

	public PullToRefreshExpandableListView(Context context, int mode) {
		super(context, mode);
	}

	public PullToRefreshExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected final ExpandableListView createRefreshableView(Context context, AttributeSet attrs) {
		ExpandableListView lv = new InternalExpandableListView(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullToRefresh);

		// Set it to this so it can be used in ListActivity/ListFragment
		if (a.hasValue(R.styleable.PullToRefresh_ptrHeaderOffset)) {
			value = a.getInteger(R.styleable.PullToRefresh_ptrHeaderOffset, 0);
		}
		lv.setPadding(0, -value, 0, 0);
		lv.setId(android.R.id.list);
		return lv;
	}

	@Override
	public ContextMenuInfo getContextMenuInfo() {
		return ((InternalExpandableListView) getRefreshableView()).getContextMenuInfo();
	}

	@Override
	public int setHeaderOffset() {
		return value;
	}
}
