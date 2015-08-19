package anh.nguyen.messageparser.interactor;

import anh.nguyen.messageparser.model.MessageMetadata;
import rx.Observable;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * Contract interface for ExtractMetadataAsJsonString operation
 */
public interface ExtractMetadataInteractor {
    /**
     * Return an rx.Observable of type MessageMetadata
     * @param message
     * @return Observable
     */
    Observable<MessageMetadata> execute(String message);
}
