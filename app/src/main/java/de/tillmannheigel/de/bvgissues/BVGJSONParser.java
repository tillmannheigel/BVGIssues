package de.tillmannheigel.de.bvgissues;

import org.json.JSONException;
import org.json.JSONObject;

import de.tillmannheigel.de.bvgissues.model.Details;
import de.tillmannheigel.de.bvgissues.model.Issue;
import de.tillmannheigel.de.bvgissues.model.What;
import de.tillmannheigel.de.bvgissues.model.When;
import de.tillmannheigel.de.bvgissues.model.Where;
import de.tillmannheigel.de.bvgissues.model.Why;

/**
 * Created by ou on 12.04.17.
 */

public class BVGJSONParser {

    private static final String TAG = "BVGJSONParser";

    public Issue parseIssue(String jsonStr){
        Issue.IssueBuilder issue = Issue.builder();



        return issue.build();
    }
}
