package no.kristoffer.ipa1socketbots.client;

public final class Bot {

    private Bot() {
    }

    public static String alice(String action) {
        return String.format("I think %s sounds great!", action + "ing");
    }

    public static String bob(String action) {
        return String.format("I don't bother %s right now!", action + "ing");
    }
}
