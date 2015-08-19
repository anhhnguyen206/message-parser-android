package anh.nguyen.messageparser.common.base;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

import anh.nguyen.messageparser.App;
import dagger.ObjectGraph;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * Base class for all activities that need Dagger injection
 */
public abstract class InjectableActivity extends BaseActivity {

    private ObjectGraph activityScopeGraph;

    protected abstract List<Object> getModules();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    public void inject(Object object) {
        activityScopeGraph.inject(object);
    }

    private void injectDependencies() {
        App atlasApplication = (App) getApplication();
        List<Object> activityScopeModules = new LinkedList<>();
        activityScopeModules.addAll(getModules());
        activityScopeGraph = atlasApplication.plus(activityScopeModules);
        inject(this);
    }
}
