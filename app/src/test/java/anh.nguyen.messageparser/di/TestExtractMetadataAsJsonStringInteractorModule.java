package anh.nguyen.messageparser.di;

import javax.inject.Singleton;

import anh.nguyen.messageparser.interactor.ExtractMetadataAsJsonStringInteractor;
import anh.nguyen.messageparser.interactor.ExtractMetadataAsJsonStringInteractorImpl;
import anh.nguyen.messageparser.interactor.ExtractMetadataAsJsonStringInteractorImplTest;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
@Module(
        library = true,
        complete = false,
        includes = TestParsersModule.class,
        injects = ExtractMetadataAsJsonStringInteractorImplTest.class
)
public class TestExtractMetadataAsJsonStringInteractorModule {
    @Provides
    @Singleton
    ExtractMetadataAsJsonStringInteractor provideExtractMetadataInteractor(ExtractMetadataAsJsonStringInteractorImpl extractMetadataInteractor) {
        return extractMetadataInteractor;
    }
}
