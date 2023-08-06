package uk.co.littlestickyleaves.domain.builders;

import uk.co.littlestickyleaves.domain.Format;
import uk.co.littlestickyleaves.domain.FormatInfo;
import uk.co.littlestickyleaves.domain.Price;
import uk.co.littlestickyleaves.domain.Shop;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 */
// TODO fill in Javadoc
public class FormatInfoBuilder {

    private final Format format;

    private String isbnOrAsin;

    private LocalDate releaseDate;

    private List<Price> prices;

    public FormatInfoBuilder(Format format) {
        this.format = format;
    }

    public static FormatInfoBuilder formatInfoBuilder(Format format) {
        return new FormatInfoBuilder(format);
    }

    public FormatInfoBuilder withIsbnOrAsin(String isbnOrAsin) {
        this.isbnOrAsin = isbnOrAsin;
        return this;
    }

    public FormatInfoBuilder withReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public FormatInfoBuilder withReleaseDate(int year, int month, int day) {
        this.releaseDate = LocalDate.of(year, month, day);
        return this;
    }

    public FormatInfoBuilder withPrices(List<Price> prices) {
        this.prices = prices;
        return this;
    }

    public FormatInfoBuilder withPrice(Price price) {
        if (prices == null) {
            prices = new ArrayList<>();
        }
        prices.add(price);
        return this;
    }

    public FormatInfoBuilder withPrice(Shop shop, ZonedDateTime time, double cost) {
        if (prices == null) {
            prices = new ArrayList<>();
        }
        prices.add(new Price(shop, time, cost));
        return this;
    }

    public FormatInfoBuilder withAmazonPrice(ZonedDateTime time, double cost) {
        if (prices == null) {
            prices = new ArrayList<>();
        }
        prices.add(new Price(Shop.AMAZON, time, cost));
        return this;
    }

    public FormatInfo build() {
        if (prices == null) {
            prices = new ArrayList<>();
        }

        if (format.equals(Format.KINDLE)) {
            checkForAsin(isbnOrAsin);
        } else {
            checkForIsbn13(isbnOrAsin);
        }

        return new FormatInfo(format,
                isbnOrAsin,
                releaseDate,
                prices);
    }

    private void checkForIsbn13(String isbnOrAsin) {
        if (isbnOrAsin.length() != 13) {
            throw new RuntimeException("An ISBN-13 should be ten characters");
        }
        // todo -- add conversion from ISBN-10 to ISBN-13, with check sums
    }

    private void checkForAsin(String isbnOrAsin) {
        if (isbnOrAsin.length() != 10) {
            throw new RuntimeException("An ASIN should be ten characters");
        }
    }
}
