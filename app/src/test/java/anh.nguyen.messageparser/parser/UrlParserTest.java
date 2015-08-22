package anh.nguyen.messageparser.parser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nguyenhoanganh on 8/19/15.
 */
public class UrlParserTest {
    private static UrlParser mUrlParser;

    @BeforeClass
    public static void setUp() {
        mUrlParser = new UrlParser();
    }

    @AfterClass
    public static void tearDown() {
        mUrlParser = null;
    }

    @Test
    public void parse_NoUrlChatMessage_ShouldReturnNoLink() {
        String noUrlChatMessage = "Hello, world!";
        List<String> expected = new ArrayList<String>();
        List<String> actual = mUrlParser.parse(noUrlChatMessage);
        assertThat(actual, is(expected));
    }

    @Test
    public void parse_OneUrlChatMessage_ShouldReturnTwoUrls() {
        String oneUrlChatMessage = "Olympics are starting soon; http://www.nbcolympics.com";
        List<String> expected = Arrays.asList("http://www.nbcolympics.com");
        List<String> actual = mUrlParser.parse(oneUrlChatMessage);
        assertThat(actual, is(expected));
    }

    @Test
    public void parse_TwoUrlsChatMessage_ShouldReturnTwoUrls() {
        String twoUrlsChatMessage = "Olympics are starting soon; http://www.nbcolympics.com.  You can go on this site to check the score: https://www.espn.com";
        List<String> expected = Arrays.asList("http://www.nbcolympics.com", "https://www.espn.com");
        List<String> actual = mUrlParser.parse(twoUrlsChatMessage);
        assertThat(actual, is(expected));
    }

    @Test
    public void parse_ThreeUrlsChatMessage_ShouldReturnThreeUrls() {
        String twoUrlsChatMessage = "@bob @john (success) https://www.espn.com, http://www.facebook.com; such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";
        List<String> expected = Arrays.asList("https://www.espn.com", "http://www.facebook.com", "https://twitter.com/jdorfman/status/430511497475670016");
        List<String> actual = mUrlParser.parse(twoUrlsChatMessage);
        assertThat(actual, is(expected));
    }
}
