package uk.co.littlestickyleaves.domain.builders;

import uk.co.littlestickyleaves.domain.Author;
import uk.co.littlestickyleaves.domain.Book;
import uk.co.littlestickyleaves.domain.Format;
import uk.co.littlestickyleaves.domain.FormatInfo;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

/**
 * Builder for making Books more easily
 */
public class BookBuilder {

    private String uniqueId;
    private final ZonedDateTime created;
    private ZonedDateTime modified;
    private String title;
    private List<Author> authorList;
    private Map<Format, FormatInfo> formats;
    private String comment;

    public BookBuilder(ZonedDateTime created) {
        this.created = created;
    }

    public BookBuilder withUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public BookBuilder withModified(ZonedDateTime modified) {
        this.modified = modified;
        return this;
    }

    public BookBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder withAuthors(List<Author> authorList) {
        this.authorList = authorList;
        return this;
    }

    public BookBuilder withAuthor(Author author) {
        if (authorList == null) {
            authorList = new ArrayList<>();
        }
        authorList.add(author);
        return this;
    }

    public BookBuilder withFormatsInfo(Map<Format, FormatInfo> formatsInfo) {
        this.formats = formatsInfo;
        return this;
    }

    public BookBuilder withFormatsInfo(List<FormatInfo> formatsInfo) {
        this.formats = formatsInfo.stream()
                .collect(Collectors.toMap(FormatInfo::format,
                        identity()));
        return this;
    }

    public BookBuilder withFormatInfo(FormatInfo formatInfo) {
        if (formats == null) {
            formats = new HashMap<>();
        }
        formats.put(formatInfo.format(), formatInfo);
        return this;
    }

    public BookBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Book build() {
        if (authorList == null) {
            authorList = new ArrayList<>();
        }
        if (formats == null) {
            formats = new HashMap<>();
        }
        if (modified == null) {
            modified = created;
        }

        return new Book(
                uniqueId,
                created,
                modified,
                title,
                authorList,
                formats,
                comment
        );
    }
}
