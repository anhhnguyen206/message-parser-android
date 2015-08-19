package anh.nguyen.messageparser.di;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import anh.nguyen.messageparser.App;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
@Module(
        injects = App.class,
        library = true,
        complete = false
)
public class RootModule {
    private final Context mContext;

    public RootModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(mContext);
    }
}
