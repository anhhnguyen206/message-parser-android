package anh.nguyen.messageparser.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mockito.Mockito;

import javax.inject.Named;
import javax.inject.Singleton;

import anh.nguyen.messageparser.common.MessageMetadataConverter;
import anh.nguyen.messageparser.interactor.ExtractMetadataInteractor;
import anh.nguyen.messageparser.ui.main.observer.ExtractMetadataObserver;
import anh.nguyen.messageparser.ui.main.presenter.MainPresenter;
import anh.nguyen.messageparser.ui.main.presenter.MainPresenterImpl;
import anh.nguyen.messageparser.ui.main.presenter.MainPresenterImplTest;
import anh.nguyen.messageparser.ui.main.view.MainView;
import dagger.Module;
import dagger.Provides;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
@Module(
        library = true,
        complete = false,
        injects = MainPresenterImplTest.class
)
public class TestMainPresenterImplModule {
    @Provides
    @Singleton
    MainView provideMainView() {
        return Mockito.mock(MainView.class);
    }

    @Provides
    @Singleton
    ExtractMetadataInteractor provideExtractMetadataAsJsonStringInteractor() {
        return Mockito.mock(ExtractMetadataInteractor.class);
    }

    @Provides
    @Singleton
    MessageMetadataConverter provideMessageMetadataConverter() {
        return Mockito.mock(MessageMetadataConverter.class);
    }

    @Provides
    @Singleton
    ExtractMetadataObserver provideExtractMetadataObserver(MainView mainView, Gson gson, MessageMetadataConverter messageMetadataConverter) {
        return new ExtractMetadataObserver(mainView, gson, messageMetadataConverter);
    }

    @Provides
    @Singleton
    MainPresenter provideMainPresenter(MainView mainView, ExtractMetadataObserver extractMetadataObserver,
                                       ExtractMetadataInteractor extractMetadataInteractor,
                                       @Named("ui-scheduler") TestScheduler observeOnScheduler,
                                       @Named("io-scheduler") TestScheduler subscribeOnScheduler) {
        return new MainPresenterImpl(mainView, extractMetadataInteractor, extractMetadataObserver, subscribeOnScheduler, observeOnScheduler);
    }

    @Provides
    @Singleton
    @Named("ui-scheduler")
    TestScheduler provideUIScheduler() {
        return Schedulers.test();
    }

    @Provides
    @Singleton
    @Named("io-scheduler")
    TestScheduler provideIOScheduler() {
        return Schedulers.test();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
