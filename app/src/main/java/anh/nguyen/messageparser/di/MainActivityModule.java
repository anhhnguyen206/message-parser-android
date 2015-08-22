package anh.nguyen.messageparser.di;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import anh.nguyen.messageparser.common.MessageMetadataConverter;
import anh.nguyen.messageparser.ui.main.presenter.MainPresenter;
import anh.nguyen.messageparser.ui.main.presenter.MainPresenterImpl;
import anh.nguyen.messageparser.ui.main.view.MainActivity;
import anh.nguyen.messageparser.ui.main.view.MainView;
import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by nguyenhoanganh on 8/21/15.
 */

@Module(
        injects = MainActivity.class,
        includes = InteractorsModule.class,
        library = true,
        complete = false
)
public class MainActivityModule {
    private MainView mMainView;

    public MainActivityModule(MainView mainView) {
        mMainView = mainView;
    }

    @Provides
    @Singleton
    MainView provideMainView() {
        return mMainView;
    }

    @Provides
    @Singleton
    MainPresenter provideMainPresenter(MainPresenterImpl mainPresenter) {
        return mainPresenter;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return (Context) mMainView;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson;
    }

    @Provides
    @Singleton
    MessageMetadataConverter provideMessageMetadataConverter() {
        return new MessageMetadataConverter();
    }

    @Provides
    @Singleton
    @Named("io-scheduler")
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ui-scheduler")
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
