package anh.nguyen.messageparser;

import android.app.Application;

import java.util.List;

import anh.nguyen.messageparser.di.RootModule;
import dagger.ObjectGraph;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class App extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
    }

    private void injectDependencies() {
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }
}
