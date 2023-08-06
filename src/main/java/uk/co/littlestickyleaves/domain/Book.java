package uk.co.littlestickyleaves.domain;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

/**
 * A record for information about a book.
 * The highest level of data
 */
public record Book(String uniqueId,
                   ZonedDateTime created,
                   ZonedDateTime modified,
                   String title,
                   List<Author> authorList,
                   Map<Format, FormatInfo> formats,
                   String comment) {


}
