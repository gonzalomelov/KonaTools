package io.teamkona.konatools.ui;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.timehop.stickyheadersrecyclerview.HeaderPositionCalculator;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.caching.HeaderProvider;
import com.timehop.stickyheadersrecyclerview.caching.HeaderViewCache;
import com.timehop.stickyheadersrecyclerview.calculation.DimensionCalculator;
import com.timehop.stickyheadersrecyclerview.rendering.HeaderRenderer;
import com.timehop.stickyheadersrecyclerview.util.LinearLayoutOrientationProvider;
import com.timehop.stickyheadersrecyclerview.util.OrientationProvider;

/**
 * Created by gonzalomelov on 12/8/15.
 **/
public class StickyRecyclerGridHeadersDecoration extends RecyclerView.ItemDecoration {
  private final StickyRecyclerHeadersAdapter mAdapter;
  private final SparseArray<Rect> mHeaderRects;
  private final HeaderProvider mHeaderProvider;
  private final OrientationProvider mOrientationProvider;
  private final HeaderPositionCalculator mHeaderPositionCalculator;
  private final HeaderRenderer mRenderer;
  private final DimensionCalculator mDimensionCalculator;
  private final int colCount;

  public StickyRecyclerGridHeadersDecoration(StickyRecyclerHeadersAdapter adapter, int colCount) {
    this(adapter, new LinearLayoutOrientationProvider(), new DimensionCalculator(), colCount);
  }

  private StickyRecyclerGridHeadersDecoration(StickyRecyclerHeadersAdapter adapter, OrientationProvider orientationProvider,
      DimensionCalculator dimensionCalculator, int colCount) {
    this(adapter, orientationProvider, dimensionCalculator, new HeaderRenderer(orientationProvider),
        new HeaderViewCache(adapter, orientationProvider), colCount);
  }

  private StickyRecyclerGridHeadersDecoration(StickyRecyclerHeadersAdapter adapter, OrientationProvider orientationProvider,
      DimensionCalculator dimensionCalculator, HeaderRenderer headerRenderer, HeaderProvider headerProvider, int colCount) {
    this(adapter, headerRenderer, orientationProvider, dimensionCalculator, headerProvider,
        new HeaderPositionCalculator(adapter, headerProvider, orientationProvider, dimensionCalculator), colCount);
  }

  private StickyRecyclerGridHeadersDecoration(StickyRecyclerHeadersAdapter adapter, HeaderRenderer headerRenderer,
      OrientationProvider orientationProvider, DimensionCalculator dimensionCalculator, HeaderProvider headerProvider,
      HeaderPositionCalculator headerPositionCalculator, int colCount) {
    this.colCount = colCount;
    this.mHeaderRects = new SparseArray<>();
    this.mAdapter = adapter;
    this.mHeaderProvider = headerProvider;
    this.mOrientationProvider = orientationProvider;
    this.mRenderer = headerRenderer;
    this.mDimensionCalculator = dimensionCalculator;
    this.mHeaderPositionCalculator = headerPositionCalculator;
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    int itemPosition = parent.getChildAdapterPosition(view);
    for (int i = 0; i <= colCount - 1; i++) {
      if (mHeaderPositionCalculator.hasNewHeader(itemPosition - i, false)) {
        View header = getHeaderView(parent, itemPosition - i);
        setItemOffsetsForHeader(outRect, header, mOrientationProvider.getOrientation(parent));
      }
    }
  }

  private void setItemOffsetsForHeader(Rect itemOffsets, View header, int orientation) {
    final Rect headerMargins = this.mDimensionCalculator.getMargins(header);
    if (orientation == LinearLayoutManager.VERTICAL) {
      itemOffsets.top = header.getHeight() + headerMargins.top + headerMargins.bottom;
    } else {
      itemOffsets.left = header.getWidth() + headerMargins.left + headerMargins.right;
    }
  }

  @Override public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
    super.onDrawOver(canvas, parent, state);
    this.mHeaderRects.clear();
    if (parent.getChildCount() > 0 && this.mAdapter.getItemCount() > 0) {
      for (int i = 0; i < parent.getChildCount(); ++i) {
        View itemView = parent.getChildAt(i);
        int position = parent.getChildPosition(itemView);
        if (this.hasStickyHeader(i, position) || this.mHeaderPositionCalculator.hasNewHeader(position, false)) {
          View header = this.mHeaderProvider.getHeader(parent, position);
          Rect headerOffset = this.mHeaderPositionCalculator.getHeaderBounds(parent, header, itemView, this.hasStickyHeader(i, position));
          this.mRenderer.drawHeader(parent, canvas, header, headerOffset);
          this.mHeaderRects.put(position, headerOffset);
        }
      }
    }
  }

  private boolean hasStickyHeader(int listChildPosition, int indexInList) {
    return listChildPosition <= 0 && this.mAdapter.getHeaderId(indexInList) >= 0;
  }

  public View getHeaderView(RecyclerView parent, int position) {
    return this.mHeaderProvider.getHeader(parent, position);
  }
}