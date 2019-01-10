package methods;

import task_structure.TreeScript;
import task_structure.TreeTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class CraftMethod {
    private static final int PURE_ESSENCE_ID = 7936;
    private final TreeScript handler;
    private TreeTask head;
    private final int essencePrice, runePrice;

    CraftMethod(final TreeScript handler, final int id) {
        this.handler = handler;
        essencePrice = findPrice(PURE_ESSENCE_ID);
        this.runePrice = findPrice(id);
    }

    void setHead(final TreeTask head) {
        this.head = head;
    }

    public TreeTask getHead() {
        return head;
    }

    private int findPrice(final int id) {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + id).openStream()))) {
            final String rawValue = reader.readLine().replace(",", "").replace("\"", "").split("price:")[1].split("}")[0];
            return Integer.parseInt(rawValue);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getEssencePrice() {
        return essencePrice;
    }

    public int getRunePrice() {
        return runePrice;
    }
}
