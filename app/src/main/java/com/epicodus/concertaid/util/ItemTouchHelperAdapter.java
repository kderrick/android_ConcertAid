package com.epicodus.concertaid.util;

/**
 * Created by kylederrick on 5/15/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
