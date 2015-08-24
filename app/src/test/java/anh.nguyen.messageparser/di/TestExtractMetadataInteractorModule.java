package anh.nguyen.messageparser.di;

import javax.inject.Named;
import javax.inject.Singleton;

import anh.nguyen.messageparser.interactor.ExtractMetadataInteractor;
import anh.nguyen.messageparser.interactor.ExtractMetadataInteractorImpl;
import anh.nguyen.messageparser.interactor.ExtractMetadataInteractorImplTest;
import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
@Module(
        library = true,
        complete = false,
        includes = TestParsersModule.class,
        injects = ExtractMetadataInteractorImplTest.class
)
public class TestExtractMetadataInteractorModule {
    @Provides
    @Singleton
    ExtractMetadataInteractor provideExtractMetadataInteractor(ExtractMetadataInteractorImpl extractMetadataInteractor) {
        return extractMetadataInteractor;
    }

    @Provides
    @Singleton
    @Named("ui-scheduler")
    Scheduler provideUIScheduler() {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    @Named("io-scheduler")
    Scheduler provideIOScheduler() {
        return Schedulers.immediate();
    }
}
