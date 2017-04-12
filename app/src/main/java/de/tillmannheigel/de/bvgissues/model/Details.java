package de.tillmannheigel.de.bvgissues.model;

import java.util.Date;

import lombok.Value;

/**
 * Created by ou on 12.04.17.
 */
@Value
public class Details {

    Date barrierfree;
    String direction;
    String description;
}
