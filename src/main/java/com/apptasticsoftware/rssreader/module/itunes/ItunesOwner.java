package com.apptasticsoftware.rssreader.module.itunes;

import java.util.Objects;
import java.util.Optional;

/**
 * Class representing the Itunes owner.
 */
public class ItunesOwner {
    private String name;
    private String email;


    /**
     * Get the name
     * @return name
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Set the name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItunesOwner that = (ItunesOwner) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail());
    }
}
