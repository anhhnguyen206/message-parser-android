package anh.nguyen.messageparser.parser;

/**
 * Created by nguyenhoanganh on 8/19/15.
 * Contract interface for every message parser
 * T indicates the return type of metadata
 */
public interface Parser<T> {
    T parse(String message);
}
