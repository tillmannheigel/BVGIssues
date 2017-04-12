package de.tillmannheigel.de.bvgissues;

import org.junit.Before;
import org.junit.Test;

import de.tillmannheigel.de.bvgissues.model.Issue;

/**
 * Created by ou on 12.04.17.
 */
public class JSONParserTest {

    private JSONParser parser;

    @Before
    public void before(){
        parser = new JSONParser();
    }

    @Test
    public void testJSONParserReturnsAnEmptyIssueForAnEmptyString(){
        Issue parsedIssue = parser.parseIssue("");
        parsedIssue.equals(new Issue());
    }
}
