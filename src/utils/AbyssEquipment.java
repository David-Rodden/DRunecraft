package utils;

import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;

public class AbyssEquipment {
    private final String head, chest, legs, feet, hands, offhand;

    public AbyssEquipment(final String head, final String chest, final String legs, final String feet, final String hands, final String offhand) {
        this.head = head;
        this.chest = chest;
        this.legs = legs;
        this.feet = feet;
        this.hands = hands;
        this.offhand = offhand;
    }

    private boolean hasBeenSet() {
        return head != null && chest != null && legs != null && feet != null && hands != null && offhand != null;
    }

    public boolean isWearingEquipment() {
        return !hasBeenSet() || Equipment.containsAll(head, chest, legs, feet, hands, offhand);
    }

    public boolean isEquipmentInInventory() {
        return !hasBeenSet() || Inventory.contains(head, chest, legs, feet, hands, offhand);
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
