package anh.nguyen.messageparser.common;

import android.content.Context;
import android.content.res.TypedArray;

import anh.nguyen.messageparser.R;

public class ToolbarHelper {
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }
}
