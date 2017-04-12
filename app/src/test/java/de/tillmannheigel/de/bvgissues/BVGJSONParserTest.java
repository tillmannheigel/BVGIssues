package de.tillmannheigel.de.bvgissues;

import android.util.Log;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import de.tillmannheigel.de.bvgissues.model.Details;
import de.tillmannheigel.de.bvgissues.model.Issue;
import de.tillmannheigel.de.bvgissues.model.What;
import de.tillmannheigel.de.bvgissues.model.When;
import de.tillmannheigel.de.bvgissues.model.Where;
import de.tillmannheigel.de.bvgissues.model.Why;

import static org.junit.Assert.assertEquals;

/**
 * Created by ou on 12.04.17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class BVGJSONParserTest {

    private BVGJSONParser parser;

    @Before
    public void before() {
        parser = new BVGJSONParser();
    }

    @Test
    public void testJSONParserReturnsAnEmptyIssueForAnEmptyString() {
        Issue parsedIssue = parser.parseIssue("");
        assertEquals(parsedIssue, Issue.builder().build());
    }

    @Test
    public void testEasyJSONString() {
        String validJson = "{\"id\": \"29603\"}";
        Issue parsedIssue = parser.parseIssue(validJson);
        Issue testIssue = Issue.builder()
                .id("29603")
                .build();
        assertEquals(parsedIssue, testIssue);
    }

    @Test
    public void testJSONParserReturnsCorrectIssueForValidString() {
        String validJson = " {" +
                "            \"what\": {" +
                "                \"line\": \"M19\", " +
                "                \"medium\": \"Bus M-Linien M19\"" +
                "            }, " +
                "            \"when\": {" +
                "                \"begin\": \"2017-04-10T08:00:00\", " +
                "                \"end\": \"2017-04-11T08:00:00\"" +
                "            }, " +
                "            \"why\": {" +
                "                \"url\": \"http://www.bvg.de/de/Fahrinfo/Verkehrsmeldungen/de/Fahrinfo/Verkehrsmeldungen/Verkehrsmeldung-Detail?id=29603\", " +
                "                \"reason\": \"Sperrung wegen Bauarbeiten der Wasserbetriebe\"" +
                "            }, " +
                "            \"details\": {" +
                "                \"barrierfree\": \"10.04.2017 08:00\", " +
                "                \"direction\": \"Hagenplatz\", " +
                "                \"description\": \"Haltestellenaufhebung: Die Haltestelle Hagenplatz wird ersatzlos aufgehoben.\"" +
                "            }, " +
                "            \"where\": {" +
                "                \"start\": \"Taubertstr.\", " +
                "                \"end\": \"Hagenplatz\"" +
                "            }, " +
                "            \"id\": \"29603\"" +
                "        }";

        Issue parsedIssue = parser.parseIssue(validJson);

        What testWhat = new What("M19", "Bus M-Linien M19");
        When testWhen = new When("2017-04-10T08:00:00", "2017-04-11T08:00:00");
        Details testDetails = new Details("10.04.2017 08:00", "Hagenplatz", "Haltestellenaufhebung: Die Haltestelle Hagenplatz wird ersatzlos aufgehoben.");
        Why testWhy = new Why("http://www.bvg.de/de/Fahrinfo/Verkehrsmeldungen/de/Fahrinfo/Verkehrsmeldungen/Verkehrsmeldung-Detail?id=29603", "Sperrung wegen Bauarbeiten der Wasserbetriebe");
        Where testWhere = new Where("Taubertstr.", "Hagenplatz");
        Issue testIssue = Issue.builder()
                .id("29603")
                .what(testWhat)
                .when(testWhen)
                .why(testWhy)
                .where(testWhere)
                .details(testDetails)
                .build();

        assertEquals(parsedIssue, testIssue);
    }
}
