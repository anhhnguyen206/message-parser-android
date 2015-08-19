package anh.nguyen.messageparser.common.base;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import anh.nguyen.messageparser.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * Base of every activities.
 * Automatic inject views using ButterKnife
 * Automatic inflate Toolbar if includes
 */
public class BaseActivity extends AppCompatActivity {
    @Bind(R.id.tool_bar)
    @Nullable
    Toolbar mToolbar;

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
        setUpActionBar();
    }

    private void setUpActionBar() {
        setSupportActionBar(mToolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }
}
