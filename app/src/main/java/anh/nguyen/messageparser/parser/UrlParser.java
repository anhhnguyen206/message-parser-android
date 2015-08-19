package anh.nguyen.messageparser.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class UrlParser implements Parser<List<String>> {
    private Pattern mUrlPattern = Pattern.compile("\\b([\\d\\w\\.\\/\\+\\-\\?\\:]*)((ht|f)tp(s|)\\:\\/\\/|[\\d\\d\\d|\\d\\d]\\.[\\d\\d\\d|\\d\\d]\\.|www\\.|\\.tv|\\.ac|\\.com|\\.edu|\\.gov|\\.int|\\.mil|\\.net|\\.org|\\.biz|\\.info|\\.name|\\.pro|\\.museum|\\.co)([\\d\\w\\.\\/\\%\\+\\-\\=\\&amp;\\?\\:\\\\\\&quot;\\'\\,\\|\\~\\;]*)\\b");

    /**
     * Return a list of urls given a chat message
     * @param message
     * @return List of urls
     */
    @Override
    public List<String> parse(String message) {
        List<String> urls = new ArrayList<>();
        Matcher matcher = mUrlPattern.matcher(message);

        while (matcher.find()) {
            urls.add(matcher.group());
        }

        return urls;
    }
}
