package anh.nguyen.messageparser.ui.main;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import anh.nguyen.messageparser.common.ToolbarHelper;

/**
 * Created by nguyenhoanganh on 8/22/15.
 */
public class LinearLayoutBehavior extends CoordinatorLayout.Behavior<LinearLayout> {
    private int mToolbarHeight;
    public LinearLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mToolbarHeight = ToolbarHelper.getToolbarHeight(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout linearLayout, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * Calculating how much of the AppBarLayout is scrolled off the screen and we are scrolling our LinearLayout accordingly
     * @param parent
     * @param linearLayout
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout linearLayout, View dependency) {
        if (dependency instanceof AppBarLayout) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) linearLayout.getLayoutParams();
            int linearLayoutBottomMargin = lp.bottomMargin;
            int distanceToScroll = linearLayout.getHeight() + linearLayoutBottomMargin;
            float ratio = dependency.getY() / (float) mToolbarHeight;
            linearLayout.setTranslationY(-distanceToScroll * ratio);
        }
        return true;
    }
}
