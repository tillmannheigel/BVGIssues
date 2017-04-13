package de.tillmannheigel.de.bvgissues;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;

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

    public Issue parseIssue(String jsonStr) {
        Issue issue = null;

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Issue> jsonAdapter = moshi.adapter(Issue.class);

        try {
            issue = jsonAdapter.fromJson(jsonStr);
        }  catch (EOFException eof){
            System.out.println("EOFException while parsing. Empty or incorrect Json String should end up in an empty issue. That's ok!");
        } catch (IOException io) {
            System.out.println("IOException while parsing. That's definitely not ok!\n" + io.getCause());
        }

        if (null == issue) {
            issue = issue.builder().build();
        }

        return issue;
    }
}
