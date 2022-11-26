
package Home;
import java.util.LinkedList;
public class Piece {
    int xp;
    int yp;

    int x;
    int y;
    boolean Player1;
    LinkedList<Piece> ps;
    String name;

    public Piece(int xp, int yp, boolean Player1, String n, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.Player1 = Player1;
        this.ps = ps;
        name = n;
        ps.add(this);
    }

    public void move(int xp, int yp) {
        if (GhostGame.getPiece(xp * 64, yp * 64) != null) {
            if (GhostGame.getPiece(xp * 64, yp * 64).Player1 != Player1) {
                GhostGame.getPiece(xp * 64, yp * 64).kill();
            } else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
    }

    public void kill() {
        ps.remove(this);
    }
}
