package de.tillmannheigel.de.bvgissues.model;

import java.util.logging.StreamHandler;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Builder;

/**
 * Created by ou on 12.04.17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Value
@Builder
public class Issue {

    String id;
    What what;
    When when;
    Why why;
    Where where;
    Details details;

}
