package uk.co.littlestickyleaves.domain;

import java.time.ZonedDateTime;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 */
// TODO fill in Javadoc
public record Price(Shop shop,
                    ZonedDateTime time,
                    double price) {
}
