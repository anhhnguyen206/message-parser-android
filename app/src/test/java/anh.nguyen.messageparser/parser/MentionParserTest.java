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
public class MentionParserTest {
    private static MentionParser mMentionParser;

    @BeforeClass
    public static void setUp() {
        mMentionParser = new MentionParser();
    }

    @AfterClass
    public static void tearDown() {
        mMentionParser = null;
    }

    @Test
    public void parse_NoMentionChatMessage_ShouldReturnNoMention() {
        String noMentionChatMessage = "Hello, is it me you're looking for?";
        List<String> expected = new ArrayList<>();
        List<String> actual = mMentionParser.parse(noMentionChatMessage);
        assertThat(actual, is(expected));
    }

    @Test
    public void parse_OneMentionChatMessage_ShouldReturnOneMention() {
        String oneMentionChatMessage = "@chris you around?";
        List<String> expected = Arrays.asList("chris");
        List<String> actual = mMentionParser.parse(oneMentionChatMessage);
        assertThat(actual, is(expected));
    }

    @Test
    public void parse_TwoMentionChatMessage_ShouldReturnTwoMetions() {
        String twoMentionChatMessage = "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";
        List<String> expected = Arrays.asList("bob", "john");
        List<String> actual = mMentionParser.parse(twoMentionChatMessage);
        assertThat(actual, is(expected));
    }
}
