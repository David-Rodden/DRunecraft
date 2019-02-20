package utils;

import org.rspeer.runetek.api.movement.path.HpaPath;
import org.rspeer.runetek.api.movement.pathfinding.hpa.graph.HpaEdge;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.Iterator;

public class Pathing {
    public static int getTrueDistance(final Position destination) {
        int distance = 0;
        final HpaPath hpaPath = HpaPath.build(destination);
        if (hpaPath == null) return Integer.MAX_VALUE;
        hpaPath.decache();
        final Iterator pathIterator = hpaPath.getPath().iterator();
        HpaEdge edge;
        while (pathIterator.hasNext()) {
            edge = (HpaEdge) pathIterator.next();
            distance += edge.getStart().getPosition().distance(edge.getEnd().getPosition());
        }
        return distance;
    }
}
