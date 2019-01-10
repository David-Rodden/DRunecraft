package methods;

import task_structure.TreeScript;
import task_structure.TreeTask;

public abstract class CraftMethod {
    private final TreeScript handler;
    private TreeTask head;

    CraftMethod(final TreeScript handler) {
        this.handler = handler;
    }

    void setHead(final TreeTask head) {
        this.head = head;
    }

    public TreeTask getHead() {
        return head;
    }

}
