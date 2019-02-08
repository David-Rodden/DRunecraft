package utils;

public enum Pouches {
    SMALL(5509), MEDIUM(5510), LARGE(5511), GIANT(5512);

    private final int id;

    Pouches(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase() + " pouch";
    }
}
