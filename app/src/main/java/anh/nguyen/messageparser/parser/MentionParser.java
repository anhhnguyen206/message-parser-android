package anh.nguyen.messageparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nguyenhoanganh on 8/19/15.
 *
 */
public class MentionParser implements Parser<List<String>> {
    private static Pattern mentionPattern = Pattern.compile("@([a-zA-Z])\\w*");

    /**
     *
     * @param chatMessage Input message
     * @return A list of mentioned names.
     */
    public List<String> parse(String chatMessage) {
        Matcher mentionMatcher = mentionPattern.matcher(chatMessage);
        List<String> mentionMatches = new ArrayList<>();

        while (mentionMatcher.find()) {
            mentionMatches.add(mentionMatcher.group().replace("@", ""));
        }

        return mentionMatches;
    }
}
