package anh.nguyen.messageparser.interactor;

import rx.Observable;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * Contract interface for ExtractMetadataAsJsonString operation
 */
public interface ExtractMetadataAsJsonStringInteractor {
    /**
     * Return an rx.Observable of type String
     * @param message
     * @return Observable
     */
    Observable<String> execute(String message);
}
