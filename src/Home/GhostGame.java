package Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GhostGame extends JFrame {

    public GhostGame() {

    }

    public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;

    public static void main(String[] args) throws IOException {

        BufferedImage all = ImageIO.read(new File("chess.png"));
        Image imgs[] = new Image[4];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 420; x += 210) {

                imgs[ind] = all.getSubimage(x, y, 210, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }

        Piece P2bueno1 = new Piece(1, 0, false, "bueno", ps);
        Piece P2bueno2 = new Piece(2, 0, false, "bueno", ps);
        Piece P2bueno3 = new Piece(3, 0, false, "bueno", ps);
        Piece P2bueno4 = new Piece(4, 0, false, "bueno", ps);

        Piece P2malo1 = new Piece(1, 1, false, "malo", ps);
        Piece P2malo2 = new Piece(2, 1, false, "malo", ps);
        Piece P2malo3 = new Piece(3, 1, false, "malo", ps);
        Piece P2malo4 = new Piece(4, 1, false, "malo", ps);

        Piece P1bueno1 = new Piece(1, 5, true, "bueno", ps);
        Piece P1bueno2 = new Piece(2, 5, true, "bueno", ps);
        Piece P1bueno3 = new Piece(3, 5, true, "bueno", ps);
        Piece P1bueno4 = new Piece(4, 5, true, "bueno", ps);
        Piece P1malo1 = new Piece(1, 4, true, "malo", ps);
        Piece P1malo2 = new Piece(2, 4, true, "malo", ps);
        Piece P1malo3 = new Piece(3, 4, true, "malo", ps);
        Piece P1malo4 = new Piece(4, 4, true, "malo", ps);

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 384, 384);
       frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean moradito = true;
                for (int y = 0; y < 6; y++) {
                    for (int x = 0; x < 6; x++) {
                        if (moradito) {
                            g.setColor(new Color(53, 0, 61));
                        } else {
                            g.setColor(new Color(40, 40, 40));

                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        moradito = !moradito;
                    }
                    moradito = !moradito;
                }
                for (Piece p : ps) {
                    int ind = 0;
                    if (p.name.equalsIgnoreCase("malo")) {
                        ind = 0;
                    }
                    if (p.name.equalsIgnoreCase("bueno")) {
                        ind = 1;
                    }
                    if (!p.Player1) {
                        ind += 2;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }

        };
        frame.add(pn);

        frame.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

                if (selectedPiece != null) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            int posx1 = 0;
            int posx2 = 0;
            int posy1 = 0;
            int posy2 = 0;
            boolean turno1 = true;

            @Override
            public void mousePressed(MouseEvent e) {
                posx1 = e.getX();
                posy1 = e.getY();
                selectedPiece = getPiece(e.getX(), e.getY());

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPiece != null) {
                    if (turno1) {
                        if (selectedPiece.Player1) {
                            posx2 = e.getX();
                            posy2 = e.getY();
                            if (selectedPiece.ValidateMoveP1(posx1, posy1, posx2, posy2)) {
                                selectedPiece.move(e.getX() / 64, e.getY() / 64);
                                frame.repaint();
                                turno1 = !turno1;
                            } else {
                                selectedPiece.back(e.getX() / 64, e.getY() / 64);
                                frame.repaint();
                            }
                        } else {
                            selectedPiece.back(e.getX() / 64, e.getY() / 64);
                            frame.repaint();
                            JOptionPane.showMessageDialog(pn, "TURNO JUGADOR 1");
                        }
                    } else {
                        if (selectedPiece.Player1 == false) {
                            posx2 = e.getX();
                            posy2 = e.getY();
                            if (selectedPiece.ValidateMoveP2(posx1, posy1, posx2, posy2)) {
                                selectedPiece.move(e.getX() / 64, e.getY() / 64);
                                frame.repaint();
                                turno1 = !turno1;
                            } else {
                                selectedPiece.back(e.getX() / 64, e.getY() / 64);
                                frame.repaint();
                            }
                        } else {
                            selectedPiece.back(e.getX() / 64, e.getY() / 64);
                            frame.repaint();
                            JOptionPane.showMessageDialog(pn, "TURNO JUGADOR 2");
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    public static Piece getPiece(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        for (Piece p : ps) {
            if (p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
