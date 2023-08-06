package uk.co.littlestickyleaves.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 */
// TODO fill in Javadoc
public record FormatInfo(Format format,
                         String isbnOrAsin,
                         LocalDate releaseDate,
                         List<Price> prices) {
}
