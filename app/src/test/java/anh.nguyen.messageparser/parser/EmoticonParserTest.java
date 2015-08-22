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
public class EmoticonParserTest {
    private static EmoticonParser mEmoticonParser;

    @BeforeClass
    public static void setUp() {
        mEmoticonParser = new EmoticonParser();
    }

    @AfterClass
    public static void tearDown() {
        mEmoticonParser = null;
    }

    @Test
    public void parse_NoEmoticon_ShouldReturnNoEmoticon() {
        String noEmoticonChatMessage = "Good morning!";
        List<String> expected = new ArrayList<>();
        List<String> actual = mEmoticonParser.parse(noEmoticonChatMessage);
        assertThat(actual, is(expected));
    }

    @Test
    public void parse_OneEmoticon_ShouldReturnOneEmoticon() {
        String oneEmoticonChatMessage = "Good morning! (megusta)";
        List<String> expected = Arrays.asList("megusta");
        List<String> actual = mEmoticonParser.parse(oneEmoticonChatMessage);
        assertThat(actual, is(expected));
    }

    @Test
    public void parse_TwoEmoticons_ShouldReturnTwoEmoticons() {
        String twoEmoticonsChatMessage = "Good morning! (megusta) (coffee)";
        List<String> expected = Arrays.asList("megusta", "coffee");
        List<String> actual = mEmoticonParser.parse(twoEmoticonsChatMessage);
        assertThat(actual, is(expected));
    }
}
