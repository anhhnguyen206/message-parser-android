package anh.nguyen.messageparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nguyenhoanganh on 8/19/15.
 *
 */
public class EmoticonParser implements Parser<List<String>> {
    private static Pattern emoticonPattern = Pattern.compile("\\(([a-z]{1,15})\\)");

    /**
     * Return a list of emoticons from a chat message
     * @param chatMessage Input message
     * @return List of emoticons
     */
    public List<String> parse(String chatMessage) {
        Matcher emoticonMatcher = emoticonPattern.matcher(chatMessage);
        List<String> emoticons = new ArrayList<>();

        while(emoticonMatcher.find()) {
            emoticons.add(emoticonMatcher.group().replace("(", "").replace(")",""));
        }

        return emoticons;
    }
}
