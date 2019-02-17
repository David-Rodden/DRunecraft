package tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Shop;
import org.rspeer.runetek.api.scene.Npcs;
import task_structure.TreeTask;

public class TradeJiminua extends TreeTask {
    public TradeJiminua() {
        super(true);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public int execute() {
        final Npc jiminua = Npcs.getNearest("Jiminua");
        if (jiminua == null) return super.execute();
        jiminua.interact("Trade");
        Time.sleepUntil(Shop::isOpen, 5000);
        return super.execute();
    }

    @Override
    public String toString() {
        return "Trading Jiminua";
    }
}
