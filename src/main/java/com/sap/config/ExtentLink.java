package com.sap.config;

import com.aventstack.extentreports.markuputils.Markup;

public class ExtentLink  implements Markup {
    private String linkUrl;
    public String getLinkUrl() {
        return this.linkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public String getLinkText() {
        return this.linkText;
    }
    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }
    private String linkText;
    @Override
    public String getMarkup() {
        final String htmlTag = "+this.linkText+";
        return htmlTag;
    }
    @Override
    public String toString() {
        return this.linkText;
    }

}