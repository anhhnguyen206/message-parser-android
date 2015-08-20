package anh.nguyen.messageparser.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.mockito.Mockito;

import javax.inject.Singleton;

import anh.nguyen.messageparser.common.MessageMetadataConverter;
import anh.nguyen.messageparser.ui.observer.ExtractMetadataObserver;
import anh.nguyen.messageparser.ui.observer.ExtractMetadataObserverTest;
import anh.nguyen.messageparser.ui.view.MainView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
@Module(
        library = true,
        complete = false,
        injects = ExtractMetadataObserverTest.class
)
public class TestExtractMetadataObserverModule {
    @Provides
    @Singleton
    MainView provideMainView() {
        return Mockito.mock(MainView.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setPrettyPrinting().create();
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
}
