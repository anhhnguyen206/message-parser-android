package anh.nguyen.messageparser.di;

import javax.inject.Singleton;

import anh.nguyen.messageparser.interactor.ExtractMetadataInteractor;
import anh.nguyen.messageparser.interactor.ExtractMetadataInteractorImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
@Module(
        library = true,
        complete = false,
        includes = ParserModule.class
)
public class InteractorsModule {
    @Provides
    @Singleton
    ExtractMetadataInteractor provideExtractMetadataInteractor(ExtractMetadataInteractorImpl extractMetadataInteractor) {
        return extractMetadataInteractor;
    }
}
