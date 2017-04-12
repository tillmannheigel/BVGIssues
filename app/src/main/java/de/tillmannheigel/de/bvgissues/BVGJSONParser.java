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
        //Commented out, until issues with PowerMock.mock are resolved
        //Log.e(TAG, "Response from url: " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                if (null != jsonObj) {
                    issue = issue.id(jsonObj.getString("id"));

                    JSONObject detailsObject = jsonObj.getJSONObject("details");
                    issue = issue.details(new Details(detailsObject.getString("barrierfree"), detailsObject.getString("direction"), detailsObject.getString("description")));

                    JSONObject whatObject = jsonObj.getJSONObject("what");
                    issue = issue.what(new What(whatObject.getString("line"), whatObject.getString("medium")));

                    JSONObject whyObject = jsonObj.getJSONObject("why");
                    issue = issue.why(new Why(whyObject.getString("url"), whyObject.getString("reason")));

                    JSONObject whereObject = jsonObj.getJSONObject("where");
                    issue = issue.where(new Where(whereObject.getString("start"), whereObject.getString("end")));

                    JSONObject whenObject = jsonObj.getJSONObject("when");
                    issue = issue.when(new When(whenObject.getString("begin"), whenObject.getString("end")));
                }
            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            //Log.e(TAG, "Couldn't get json from server.");
        }

        return issue.build();
    }
}
