package de.tillmannheigel.de.bvgissues.model;

import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Created by ou on 12.04.17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Value
public class Why {
    String url;
    String reason;
}
