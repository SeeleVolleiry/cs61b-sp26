package main;

import browser.NgordnetQueryHandler;

public class HyponymsHandler extends NgordnetQueryHandler {
    @Override
    public String handle(browser.NgordnetQuery q) {
        return "Hello";
    }
}
