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

    public void back(int xp, int yp) {
        x = this.xp * 64;
        y = this.yp * 64;
        return;
    }

    public void kill() {
        ps.remove(this);
    }

    public boolean ValidateMoveP1(int x1, int y1, int x2, int y2) {
        int x3;
        int y3;
        x3 = Math.abs(x2 - x1);
        y3 = (y2 - y1);
        System.out.println(x3+" "+y3);
        if (x3 < 18 && y3 > -90&&y3<9) {
            return true;
        }else if(y3>-18 && x3<90&& y3<9){
            return true;
        } else {
            return false;
        }
    }
    public boolean ValidateMoveP2(int x1, int y1, int x2, int y2) {
        int x3;
        int y3;
        x3 = Math.abs(x2 - x1);
        y3 = (y2 - y1);
        System.out.println(x3+" "+y3+"malo");
        if (x3 < 18 && y3 < 90&&y3>-9) {
            return true;
        }else if(y3<18 && x3<90&& y3>-9){
            return true;
        } else {
            return false;
        }
    }
}
