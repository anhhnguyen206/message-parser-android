package anh.nguyen.messageparser.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nguyenhoanganh on 8/20/15.
 */
@Module(
        library = true,
        complete = false
)
public class GsonModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
}
