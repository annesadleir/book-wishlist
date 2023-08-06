package uk.co.littlestickyleaves.domain.builders;

import uk.co.littlestickyleaves.domain.builders.BookBuilder;

import java.time.Clock;
import java.time.ZonedDateTime;

/**
 * A BookBuilder needs to know the time, so I need a BookBuilderFactory
 */
public class BookBuilderFactory {

    private final Clock clock;

    public BookBuilderFactory(Clock clock) {
        this.clock = clock;
    }

    public BookBuilder createBookBuilder() {
        return new BookBuilder(ZonedDateTime.now(clock));
    }
}
