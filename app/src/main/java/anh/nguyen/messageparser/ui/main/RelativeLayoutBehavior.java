package anh.nguyen.messageparser.ui.main;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import anh.nguyen.messageparser.common.ToolbarHelper;

/**
 * Created by nguyenhoanganh on 8/22/15.
 */
public class RelativeLayoutBehavior extends CoordinatorLayout.Behavior<RelativeLayout> {
    private int mToolbarHeight;

    public RelativeLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mToolbarHeight = ToolbarHelper.getToolbarHeight(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RelativeLayout relativeLayout, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * Calculating how much of the AppBarLayout is scrolled off the screen and we are scrolling our LinearLayout accordingly
     *
     * @param parent
     * @param relativeLayout
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RelativeLayout relativeLayout, View dependency) {
        if (dependency instanceof AppBarLayout) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) relativeLayout.getLayoutParams();
            int relativeLayoutBottomMargin = lp.bottomMargin;
            int distanceToScroll = relativeLayout.getHeight() + relativeLayoutBottomMargin;
            float ratio = dependency.getY() / (float) mToolbarHeight;
            relativeLayout.setTranslationY(-distanceToScroll * ratio);
        }
        return true;
    }
}
