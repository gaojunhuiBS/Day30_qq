package utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Administrator on 2016/7/1.
 */
public class PullTorefreshByRecycler extends PullToRefreshBase<RecyclerView> {
    public PullTorefreshByRecycler(Context context) {
        super(context);
    }

    public PullTorefreshByRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullTorefreshByRecycler(Context context, Mode mode) {
        super(context, mode);
    }

    public PullTorefreshByRecycler(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView view = new RecyclerView(context, attrs);
        return view;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        View view = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
        if (null != view) {
            return getRefreshableView().getBottom() >= view.getBottom();
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        View view = getRefreshableView().getChildAt(0);

        if (view != null) {
            return view.getTop() >= getRefreshableView().getTop();
        }
        return false;
    }
}
