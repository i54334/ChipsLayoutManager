package com.beloo.widget.spanlayoutmanager.layouter;

import com.beloo.widget.spanlayoutmanager.SpanLayoutManager;

public class LayouterFactory {
    private SpanLayoutManager layoutManager;

    public LayouterFactory(SpanLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public ILayouter getUpLayouter(int anchorTop, int anchorLeft, int anchorBottom, int anchorRight, boolean isRTL) {
        return isRTL ?
                new RTLUpLayouter(layoutManager, anchorTop, anchorRight, anchorBottom) :
                //we shouldn't include anchor view here, so anchorLeft is a rightOffset
                new LTRUpLayouter(layoutManager, anchorTop, anchorBottom, anchorLeft);
    }

    public ILayouter getDownLayouter(int anchorTop, int anchorLeft, int anchorBottom, int anchorRight, boolean isRTL) {
        return isRTL ?
                //down layouting should start from left point of anchor view to left point of container
                new RTLDownLayouter(layoutManager, anchorTop, anchorBottom, anchorRight) :
                //down layouting should start from right point of anchor view to right point of container
                //we should include anchor view here, so anchorLeft is a leftOffset
                //todo not working removing zero item
                new LTRDownLayouter(layoutManager, anchorTop, anchorLeft, anchorBottom);
    }
}
