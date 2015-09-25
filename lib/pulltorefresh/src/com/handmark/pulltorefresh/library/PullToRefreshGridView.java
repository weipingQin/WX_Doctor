package com.handmark.pulltorefresh.library;

import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.GridView;

public class PullToRefreshGridView extends PullToRefreshAdapterViewBase<GridView> {
	private int value;

	class InternalGridView extends GridView implements EmptyViewMethodAccessor {

		public InternalGridView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		public void setEmptyView(View emptyView) {
			PullToRefreshGridView.this.setEmptyView(emptyView);
		}

		@Override
		public void setEmptyViewInternal(View emptyView) {
			super.setEmptyView(emptyView);
		}

		@Override
		public ContextMenuInfo getContextMenuInfo() {
			return super.getContextMenuInfo();
		}
	}

	public PullToRefreshGridView(Context context) {
		super(context);
	}

	public PullToRefreshGridView(Context context, int mode) {
		super(context, mode);
	}

	public PullToRefreshGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected final GridView createRefreshableView(Context context, AttributeSet attrs) {
		GridView gv = new InternalGridView(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullToRefresh);

		// Use Generated ID (from res/values/ids.xml)
		if (a.hasValue(R.styleable.PullToRefresh_ptrHeaderOffset)) {
			value = a.getInteger(R.styleable.PullToRefresh_ptrHeaderOffset, 0);
		}
		gv.setPadding(0, -value, 0, 0);
		gv.setId(R.id.gridview);
		return gv;
	}

	@Override
	public ContextMenuInfo getContextMenuInfo() {
		return ((InternalGridView) getRefreshableView()).getContextMenuInfo();
	}

	@Override
	public int setHeaderOffset() {
		// TODO Auto-generated method stub
		return 0;
	}
}
