package utils;

public enum Pouches {
    // TODO: 2/9/2019 Unsure about GIANT pouch bitsize
    SMALL(5509, 1, 3), MEDIUM(5510, 2, 6), LARGE(5512, 4, 9), GIANT(5514, 6, 12);

    private final int id, bitSize, storageSize;

    Pouches(final int id, final int bitSize, final int storageSize) {
        this.id = id;
        this.bitSize = bitSize;
        this.storageSize = storageSize;
    }

    public int getId() {
        return id;
    }

    public int getBitSize() {
        return bitSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase() + " pouch";
    }
}
