package de.tillmannheigel.de.bvgissues.model;

import java.util.logging.StreamHandler;

import lombok.Value;

/**
 * Created by ou on 12.04.17.
 */
@Value
public class Issue {

    int id;

    What what;
    When when;
    Why why;
    Details details;

}
