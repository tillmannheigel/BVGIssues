package de.tillmannheigel.de.bvgissues.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Created by ou on 12.04.17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Value
public class When {
    String begin;
    String end;
}
