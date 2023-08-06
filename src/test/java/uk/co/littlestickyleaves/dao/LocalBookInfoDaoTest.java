package uk.co.littlestickyleaves.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.littlestickyleaves.domain.*;
import uk.co.littlestickyleaves.domain.builders.BookBuilderFactory;
import uk.co.littlestickyleaves.domain.builders.FormatInfoBuilder;
import uk.co.littlestickyleaves.helper.ConfiguredObjectMapperSupplier;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalBookInfoDaoTest {

    private static final Instant TEST_TIME = Instant.parse("2023-08-06T12:05:30.00Z");
    private static final Clock CLOCK = Clock.fixed(TEST_TIME, ZoneOffset.UTC);

    private static final ZonedDateTime BEFORE_TEST_TIME = ZonedDateTime.ofInstant(TEST_TIME, ZoneOffset.UTC).minusDays(1);

    private ObjectMapper objectMapper;

    private BookBuilderFactory bookBuilderFactory;

    private LocalBookInfoDao testObject;

    @BeforeEach
    void setUp() {
        bookBuilderFactory = new BookBuilderFactory(CLOCK);

        objectMapper = new ConfiguredObjectMapperSupplier().get();

    }

    @Test
    void fetchBooks() {
        // arrange
        testObject = new LocalBookInfoDao("C:\\Workarea\\book-wishlist\\src\\test\\resources\\books-sample.json", objectMapper);

        // act
        List<Book> result = testObject.fetchBooks();

        // assert
        assertEquals(2, result.size());
    }

    @Test
    void storeBooks() {
        // arrange
        testObject = new LocalBookInfoDao("test.json", objectMapper);
        Book longDivision = bookBuilderFactory.createBookBuilder()
                .withUniqueId("7d45ba00-e311-4929-9622-91651bcbf86c")
                .withTitle("Long Division: A Novel")
                .withAuthor(new Author("Laymon", "Kiese"))
                .withFormatInfo(FormatInfoBuilder.formatInfoBuilder(Format.PAPERBACK)
                        .withIsbnOrAsin("9781982174828")
                        .withReleaseDate(2021, 7, 22)
                        .withAmazonPrice(BEFORE_TEST_TIME, 6.08)
                        .withPrice(Shop.WATERSTONES, BEFORE_TEST_TIME, 9.99)
                        .build())
                .withFormatInfo(FormatInfoBuilder.formatInfoBuilder(Format.KINDLE)
                        .withIsbnOrAsin("B08LDZ7HRP")
                        .withReleaseDate(2021, 6, 1)
                        .withAmazonPrice(BEFORE_TEST_TIME, 6.99)
                        .build())
                .withComment("Graun July 23, sounded readable and interesting")
                .build();
        Book lostOnMe = bookBuilderFactory.createBookBuilder()
                .withUniqueId("7b4546d0-348d-4a13-bb09-64b7ef68fcda")
                .withTitle("Lost on Me")
                .withAuthors(List.of(new Author("Raimo", "Veronica"),
                        new Author("Janeczko", "Leah")))
                .withFormatInfo(FormatInfoBuilder.formatInfoBuilder(Format.HARDBACK)
                        .withIsbnOrAsin("9780349017662")
                        .withReleaseDate(2023, 8, 3)
                        .withAmazonPrice(BEFORE_TEST_TIME, 13.20)
                        .withPrice(Shop.WATERSTONES, BEFORE_TEST_TIME, 16.99)
                        .withPrice(Shop.HIVE, BEFORE_TEST_TIME, 13.85)
                        .build())
                .withFormatInfo(FormatInfoBuilder.formatInfoBuilder(Format.KINDLE)
                        .withIsbnOrAsin("B0BQ6M7ZMJ")
                        .withReleaseDate(2023, 6, 29)
                        .withAmazonPrice(BEFORE_TEST_TIME, 9.99)
                        .build())
                .build();
        List<Book> books = List.of(longDivision, lostOnMe);


        // act
        testObject.storeBooks(books);


        // assert
    }
}