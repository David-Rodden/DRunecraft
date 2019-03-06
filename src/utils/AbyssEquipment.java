package utils;

import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;

public class AbyssEquipment {
    private final String head;
    private final String chest;
    private final String legs;
    private final String feet;
    private final String hands;

    private final String offhand;

    public AbyssEquipment(final String head, final String chest, final String legs, final String feet, final String hands, final String offhand) {
        this.head = head;
        this.chest = chest;
        this.legs = legs;
        this.feet = feet;
        this.hands = hands;
        this.offhand = offhand;
    }

    public String getHead() {
        return head;
    }

    public String getChest() {
        return chest;
    }

    public String getLegs() {
        return legs;
    }

    public String getFeet() {
        return feet;
    }

    public String getHands() {
        return hands;
    }

    public String getOffhand() {
        return offhand;
    }

    private boolean hasntBeenSet() {
        return head.isEmpty() && chest.isEmpty() && legs.isEmpty() && feet.isEmpty() && hands.isEmpty() && offhand.isEmpty();
    }

    public boolean isWearingEquipment() {
        return hasntBeenSet() || Equipment.containsAll(head, chest, legs, feet, hands, offhand);
    }

    public boolean isEquipmentInInventory() {
        return hasntBeenSet() || Inventory.contains(head, chest, legs, feet, hands, offhand);
    }

    /**
     * @return String as equipment piece loaded
     */
    public String getMissingEquipment() {
        for (final String piece : new String[]{head, chest, legs, feet, hands, offhand})
            if (!piece.isEmpty() && !Equipment.contains(piece)) return piece;
        return null;
    }
}
