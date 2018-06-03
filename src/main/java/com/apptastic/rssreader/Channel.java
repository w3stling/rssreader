/*
 * MIT License
 *
 * Copyright (c) 2018, Apptastic Software
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.apptastic.rssreader;


/**
 * Class representing the RSS channel.
 */
public class Channel {
    private String title;
    private String description;
    private String language;
    private String link;
    private String copyright;
    private String generator;
    private String lastBuildDate;

    /**
     * Get the name of the channel. It's how people refer to your service. If you have an HTML website that contains the same information as your RSS file, the title of your channel should be the same as the title of your website.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the name of the channel. It's how people refer to your service. If you have an HTML website that contains the same information as your RSS file, the title of your channel should be the same as the title of your website.
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get phrase or sentence describing the channel.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set phrase or sentence describing the channel.
     * @param description channel description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the language the channel is written in.
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the language the channel is written in.
     * @param language language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get the URL to the HTML website corresponding to the channel.
     * @return link
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the URL to the HTML website corresponding to the channel.
     * @param link URL
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Get gopyright notice for content in the channel.
     * @return URL
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Set copyright notice for content in the channel.
     * @param copyright copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Get a string indicating the program used to generate the channel.
     * @return generator
     */
    public String getGenerator() {
        return generator;
    }

    /**
     * Set a string indicating the program used to generate the channel.
     * @param generator generator
     */
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    /**
     * Get the last time the content of the channel changed.
     * @return last build date
     */
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * Set the last time the content of the channel changed.
     * @param lastBuildDate last build date
     */
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
}
