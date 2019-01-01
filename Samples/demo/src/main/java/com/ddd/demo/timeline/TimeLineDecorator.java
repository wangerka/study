package com.ddd.demo.timeline;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Map;

public class TimeLineDecorator extends RecyclerView.ItemDecoration {

    List<Map<String, String>> list;

    public TimeLineDecorator(List<Map<String, String>> list) {
        this.list = list;
    }

    int leftOffset = 250;
    int topOffset = 80;
    int radius = 10;

    int textLeftOffset = 10;
    int textTopOffset = 10;

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setAntiAlias(true);

        Paint p1 = new Paint();
        p1.setColor(Color.BLUE);
        p1.setTextSize(30);
        p1.setAntiAlias(true);

        //每一个item都要draw，所以肯定要遍历所有item
        int count = parent.getChildCount();
        for(int i=0;i<count;i++){
            View child = parent.getChildAt(i);
            //circle
            int circleX = child.getLeft()-(leftOffset / 4);
            int circleY = child.getTop()-topOffset+(topOffset+child.getHeight())/2;

            c.drawCircle(circleX, circleY, radius, p);

            //up line
            int upLineTopX = circleX;
            int upLIneTopY = child.getTop()-topOffset;

            int upLineBottomX = circleX;
            int upLineBottomY = circleY-radius/2;

            c.drawLine(upLineTopX, upLIneTopY, upLineBottomX, upLineBottomY, p);

            //down line
            int downLineTopx = circleX;
            int downLineTopy = circleY+radius/2;

            int downLineBottomx = circleX;
            int downLineBottomY = child.getTop()+child.getHeight();

            c.drawLine(downLineTopx, downLineTopy,downLineBottomx,downLineBottomY,p);

            //time text
            c.drawText(list.get(i).get(TimeLineActivity.KEY_TIME),textLeftOffset, downLineTopy, p1);
            c.drawText(list.get(i).get(TimeLineActivity.KEY_DATE),textLeftOffset, downLineTopy+30, p1);

        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(leftOffset, topOffset, 0, 0);
    }
}
