package utils;

public enum Pouches {
    // TODO: 2/9/2019 Unsure about GIANT pouch bitsize
    SMALL(5509, 1), MEDIUM(5510, 2), LARGE(5512, 4), GIANT(5514, 6);

    private final int id, bitSize;

    Pouches(final int id, final int bitSize) {
        this.id = id;
        this.bitSize = bitSize;
    }

    public int getId() {
        return id;
    }

    public int getBitSize() {
        return bitSize;
    }

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase() + " pouch";
    }
}
