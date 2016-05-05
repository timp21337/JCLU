package net.pizey.test.rdd;

public class Html {
    static String otag(String tag) {
        return "<" + tag + ">" + "\n";
    }

    static String ctag(String tag) {
        return "</" + tag + ">" + "\n";
    }

    static String tag(String tag, String contents) {
        return otag(tag)
                + contents + "\n"
                + ctag(tag);
    }

    static String html(String s) {
        return tag("head", s);
    }

    static String head(String s) {
        return tag("head", s);
    }

    static String title(String s) {
        return tag("title", s);
    }

    static String body(String s) {
        return tag("body", s);
    }

    static String h1(String s) {
        return tag("h1", s);
    }

    static String h2(String s) {
        return tag("h2", s);
    }

    static String h3(String s) {
        return tag("h3", s);
    }

    static String li(String s) {
        return tag("li", s);
    }
}