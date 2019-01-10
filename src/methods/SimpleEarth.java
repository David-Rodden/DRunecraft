package methods;

import org.rspeer.runetek.api.movement.position.Position;
import task_structure.TreeScript;

public class SimpleEarth extends DefaultWalkMethod {
    SimpleEarth(final TreeScript handler) {
        super(handler, 557, "Earth");
        // TODO: 1/10/2019 Get correct coordinates for varrock west bank, and outside/inside altar
        handler.addNotedPosition("bank", new Position(3012, 3355));
        handler.addNotedPosition("outside altar", new Position(2986, 3294));
        handler.addNotedPosition("inside altar", new Position(2841, 4830));
    }
}
