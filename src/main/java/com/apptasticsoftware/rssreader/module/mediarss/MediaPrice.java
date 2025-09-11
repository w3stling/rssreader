package com.apptasticsoftware.rssreader.module.mediarss;

import java.util.Objects;
import java.util.Optional;

/**
 * Pricing information about a media object. If this tag is not present, the media object is supposed to be free.
 * One media object can have multiple instances of this tag for including different pricing structures.
 *
 * Examples:
 * {@code
 * <!-- Rental price in Euros -->
 * <media:price type="rent" price="19.99" currency="EUR" />
 *
 * <!-- Package pricing with additional information URL -->
 * <media:price type="package"
 *             info="http://www.example.com/package_info.html"
 *             price="19.99"
 *             currency="EUR" />
 *
 * <!-- Subscription pricing with information link -->
 * <media:price type="subscription"
 *             info="http://www.example.com/subscription_info"
 *             price="9.99"
 *             currency="USD" />
 * }
 *
 * Type values:
 * - "rent": For rental pricing
 * - "purchase": For one-time purchase
 * - "package": For package deals (requires info URL)
 * - "subscription": For subscription pricing (requires info URL)
 *
 * Note: Currency codes follow ISO 4217 standard (e.g., USD, EUR, JPY)
 */
public class MediaPrice {
    private String type;
    private String info;
    private Double price;
    private String currency;

    /**
     * Valid values are "rent", "purchase", "package" or "subscription".
     * If nothing is specified, then the media is free.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Valid values are "rent", "purchase", "package" or "subscription".
     * If nothing is specified, then the media is free.
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Information if the type is "package" or "subscription",
     * then info is a URL pointing to package or subscription information.
     * @return info
     */
    public Optional<String> getInfo() {
        return Optional.ofNullable(info);
    }

    /**
     * Information if the type is "package" or "subscription",
     * then info is a URL pointing to package or subscription information.
     * @param info info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * The price of the media object.
     * @return price
     */
    public Optional<Double> getPrice() {
        return Optional.ofNullable(price);
    }

    /**
     * The price of the media object.
     * @param price price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * The currency. ISO 4217 for currency codes.
     * @return currency
     */
    public Optional<String> getCurrency() {
        return Optional.ofNullable(currency);
    }

    /**
     * The currency. ISO 4217 for currency codes.
     * @param currency currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaPrice that = (MediaPrice) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getInfo(), that.getInfo()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getInfo(), getPrice(), getCurrency());
    }
}
