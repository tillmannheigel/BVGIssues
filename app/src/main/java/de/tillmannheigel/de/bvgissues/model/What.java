package de.tillmannheigel.de.bvgissues.model;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Created by ou on 12.04.17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Value
public class What {
    String line;
    String medium;
}
