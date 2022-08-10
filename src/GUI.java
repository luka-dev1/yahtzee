
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener {

    Engine engine = new Engine();

    JPanel header;
    JPanel left;
    JPanel right;
    JPanel footer;
    JPanel center;
    int counter = 0;
    int throwCount = 1;
    int result = 0;
    int highScore = 0;
    String highScoreTxt = "";
    Container container;

    // Image backgroundImage;

    JButton btnNewGame;
    JButton btnThrowDice;
    JLabel lblThrowCount;
    JLabel lblResult;
    JLabel lblHighScore;
    GameButton[][] buttons = new GameButton[17][6];
    GameButton[][] btns = new GameButton[17][6];

    public GUI() {
        super("Yahtzee");
        setBounds(0, 0, 1200, 1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        container = getContentPane();
        container.setBackground(Color.GRAY);

        setHeader();
        setLeft();
        setRight();
        setFooter();
        setCenter();
        setResizable(false);
        pack();
    }

    public void setHeader() {
        header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnNewGame = new JButton("New Game");
        lblHighScore = new JLabel("High Score: " + highScore + "       Player: " + highScoreTxt);
        btnNewGame.addActionListener(this);
        header.add(btnNewGame);
        header.add(lblHighScore);
        container.add(header, BorderLayout.NORTH);
    }

    public void setFooter() {
        footer = new JPanel();
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.WHITE);
        container.add(footer, BorderLayout.SOUTH);
    }

    public void setLeft() {
        left = new JPanel();
        left.setLayout(new GridLayout(17, 6, 0, 0));
        left.setBackground(Color.WHITE);
        container.add(left, BorderLayout.WEST);

        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new GameButton(i, j);
                if (j == 0) {
                    buttons[i][j].setPreferredSize(new Dimension(100, 30));
                } else {
                    buttons[i][j].setPreferredSize(new Dimension(70, 30));
                }
                if (i == 0 || j == 0 || i == 7 || i == 10 || i == 16) {
                    buttons[i][j].setEnabled(false);
                }
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setForeground(Color.BLACK);
                left.add(buttons[i][j]);
            }
        }

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 5; j++) {
                if (i != 7 && i != 10) buttons[i][j].addActionListener(this);
            }
        }

        for (int i = 1; i < 6; i++) {
            buttons[7][i].setBackground(Color.GRAY);
        }
        for (int i = 1; i < 6; i++) {
            buttons[10][i].setBackground(Color.GRAY);
        }
        for (int i = 1; i < 6; i++) {
            buttons[16][i].setBackground(Color.GRAY);
        }
        for (int i = 0; i < 6; i++) {
            buttons[0][i].setBackground(Color.BLACK);
        }
        for (int i = 0; i < 17; i++) {
            buttons[i][0].setBackground(Color.BLACK);
        }

        for (int i = 0; i < 17; i++) {
            buttons[i][5].setVisible(false);
        }
        buttons[0][0].setVisible(false);
        buttons[7][5].setVisible(true);
        buttons[10][5].setVisible(true);
        buttons[16][5].setVisible(true);

        // marks--------------------
        for (int i = 1; i < 7; i++) {
            buttons[i][0].setText(String.valueOf(i));
            buttons[i][0].setForeground(Color.BLACK);
        }
        buttons[7][0].setIcon(new ImageIcon("assets/Images/sum.png"));
        buttons[10][0].setIcon(new ImageIcon("assets/Images/sum.png"));
        buttons[16][0].setIcon(new ImageIcon("assets/Images/sum.png"));
        buttons[7][0].setBackground(Color.WHITE);
        buttons[10][0].setBackground(Color.WHITE);
        buttons[16][0].setBackground(Color.WHITE);
        buttons[8][0].setText("MAX");
        buttons[9][0].setText("MIN");
        buttons[8][0].setForeground(Color.BLACK);
        buttons[9][0].setForeground(Color.BLACK);
        buttons[11][0].setText("Straight");
        buttons[11][0].setForeground(Color.BLACK);
        buttons[12][0].setText("3 of a Kind");
        buttons[12][0].setForeground(Color.BLACK);
        buttons[13][0].setText("Full House");
        buttons[13][0].setForeground(Color.BLACK);
        buttons[14][0].setText("4 of a Kind");
        buttons[14][0].setForeground(Color.BLACK);
        buttons[15][0].setText("Yahtzee");
        buttons[15][0].setForeground(Color.BLACK);
        buttons[0][1].setText("DOWN");
        buttons[0][1].setForeground(Color.BLACK);
        buttons[0][2].setText("FREE");
        buttons[0][2].setForeground(Color.BLACK);
        buttons[0][3].setText("UP");
        buttons[0][3].setForeground(Color.BLACK);
        buttons[0][4].setText("ANNOUNCE");
        buttons[0][4].setForeground(Color.BLACK);
    }

    public void setRight() {
        right = new JPanel();
        right.setLayout(new BorderLayout());
        right.setBackground(Color.WHITE);


        JPanel rightCenter = new JPanel();
        JPanel rightDown = new JPanel();
        JPanel rightUp = new JPanel();

        rightUp.setBackground(Color.WHITE);
        rightUp.setPreferredSize(new Dimension(300, 250));
        rightCenter.setLayout(new GridLayout(2, 6, 5, 26));
        rightCenter.setBackground(Color.WHITE);
        rightDown.setLayout(new BorderLayout());
        rightDown.setPreferredSize(new Dimension(300, 150));
        rightDown.setBackground(Color.WHITE);


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                btns[i][j] = new GameButton(i, j);
                btns[i][j].setPreferredSize(new Dimension(40, 35));
                btns[i][j].setBackground(Color.WHITE);
                btns[i][j].addActionListener(this);
                rightCenter.add(btns[i][j]);
            }
        }
        btns[0][5].setVisible(false);

        lblThrowCount = new JLabel("Throw Count: " + throwCount);
        btnThrowDice = new JButton("Throw Dice!");
        btnThrowDice.setPreferredSize(new Dimension(200, 100));
        btnThrowDice.setForeground(Color.BLACK);
        btnThrowDice.addActionListener(this);
        rightDown.add(btnThrowDice, BorderLayout.SOUTH);
        rightDown.add(lblThrowCount, BorderLayout.CENTER);

        lblResult = new JLabel("Result: " + result);
        lblResult.setPreferredSize(new Dimension(200, 200));

        lblResult.setFont(lblResult.getFont().deriveFont(20.0f));
        rightUp.add(lblResult, BorderLayout.SOUTH);

        right.add(rightDown, BorderLayout.SOUTH);
        right.add(rightCenter, BorderLayout.CENTER);
        right.add(rightUp, BorderLayout.NORTH);
        container.add(right, BorderLayout.EAST);

    }

    public void setCenter() {
        center = new JPanel();
        center.setBackground(Color.WHITE);
        center.setBounds(0, 0, 60, 700);

        container.add(center, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof GameButton b) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    if (btns[i][j] == b) {
                        if (i == 1 && counter < 5) {
                            btns[1][j].setFlag(0);
                            btns[1][j].setEnabled(false);
                            int vr = btns[1][j].getValue();
                            for (int k = 0; k < 5; k++) {
                                if (btns[0][k].getFlag() == 0) {
                                    btns[0][k].setValue(vr);
                                    btns[0][k].setFlag(1);
                                    //butt[0][k].setText(vr+"");
                                    if (vr == 1) {
                                        btns[0][k].setIcon(new ImageIcon("assets/Images/45px-Dice-1.png"));
                                    } else if (vr == 2) {
                                        btns[0][k].setIcon(new ImageIcon("assets/Images/45px-Dice-2.png"));
                                    } else if (vr == 3) {
                                        btns[0][k].setIcon(new ImageIcon("assets/Images/45px-Dice-3.png"));
                                    } else if (vr == 4) {
                                        btns[0][k].setIcon(new ImageIcon("assets/Images/45px-Dice-4.png"));
                                    } else if (vr == 5) {
                                        btns[0][k].setIcon(new ImageIcon("assets/Images/45px-Dice-5.png"));
                                    } else if (vr == 6) {
                                        btns[0][k].setIcon(new ImageIcon("assets/Images/45px-Dice-6.png"));
                                    }
                                    break;
                                }
                            }
                            counter++;
                        } else if (i == 0 && counter > 0) {
                            btns[0][j].setIcon(null);
                            btns[0][j].setText("");
                            btns[0][j].setFlag(0);
                            int vr = btns[0][j].getValue();
                            btns[0][j].setValue(0);
                            for (int k = 0; k < 6; k++) {
                                if (vr == btns[1][k].getValue()
                                        && btns[1][k].getFlag() == 0) {
                                    btns[1][k].setEnabled(true);
                                    btns[1][k].setFlag(1);
                                    break;
                                }
                            }
                            counter--;
                        }
                    }
                }
            }

            for (int i = 1; i < 17; i++)
                for (int j = 1; j < 6; j++) {
                    if (b == buttons[i][j]) {
                        int n = 0;
                        for (int k = 0; k < 5; k++) {
                            if (btns[0][k].getFlag() == 1) {
                                ++n;
                            }
                        }
                        int[] dice = new int[n];
                        int index = 0;
                        for (int k = 0; k < 5; k++) {
                            if (btns[0][k].getFlag() == 1) {
                                dice[index++] = btns[0][k].value;
                            }
                        }
                        if (j == 4) {
                            if (!engine.announcementInProgress && throwCount == 2 && engine.fields[i][j].getIsFillable()) {
                                engine.announcementInProgress = true;
                                engine.announcedRow = i;
                                buttons[i][j].setBackground(Color.BLUE);
                            } else if (engine.announcementInProgress) {
                                if (i == engine.announcedRow && (i == 8 || i == 9) && dice.length < 5) {
                                    JOptionPane.showMessageDialog(this, "5 dice needed.");
                                } else if (i == engine.announcedRow) {
                                    Fill(i, j);
                                    Clear();
                                    engine.announcementInProgress = false;
                                    engine.announcedRow = 0;
                                    buttons[i][j].setBackground(Color.WHITE);
                                }
                            }
                        }

                        if ((!((i == 8 || i == 9) && dice.length < 5))
                                && (engine.fields[i][j].getIsFillable())
                                && (j != 4) && !engine.announcementInProgress) {
                            Fill(i, j);
                            Clear();
                        }
                    }
                }
        } else if (e.getSource() instanceof JButton b) {
            if (b == btnThrowDice) {
                if (throwCount <= 3) {
                    playSound();
                    int[] array = engine.randomize(6);
                    for (int i = 0; i < 6; i++) {
                        if (btns[1][i].getFlag() == 1 ||
                                (btns[1][i].getFlag() == 0 && throwCount == 1)) {
                            if (array[i] == 1) {
                                btns[1][i].setIcon(new ImageIcon("assets/Images/45px-Dice-1.png"));
                            } else if (array[i] == 2) {
                                btns[1][i].setIcon(new ImageIcon("assets/Images/45px-Dice-2.png"));
                            } else if (array[i] == 3) {
                                btns[1][i].setIcon(new ImageIcon("assets/Images/45px-Dice-3.png"));
                            } else if (array[i] == 4) {
                                btns[1][i].setIcon(new ImageIcon("assets/Images/45px-Dice-4.png"));
                            } else if (array[i] == 5) {
                                btns[1][i].setIcon(new ImageIcon("assets/Images/45px-Dice-5.png"));
                            } else if (array[i] == 6) {
                                btns[1][i].setIcon(new ImageIcon("assets/Images/45px-Dice-6.png"));
                            }
                            btns[1][i].setFlag(1);
                            btns[1][i].setValue(array[i]);
                        }
                    }
                    if (throwCount == 3) {
                        btnThrowDice.setEnabled(false);
                    }
                } else {
                    throwCount = 1;
                }
                throwCount++;
                if (throwCount == 4) {
                    throwCount = 1;
                }
                lblThrowCount.setText("Throw Count: " + throwCount);
            } else if (b == btnNewGame) {
                if (engine.announcementInProgress) {
                    buttons[engine.announcedRow][4].setBackground(Color.WHITE);
                }
                engine.newGame();
                Clear();
                throwCount = 1;
                lblThrowCount.setText("Throw Count: " + throwCount);
                result = engine.sum;
                lblResult.setText("Result: " + result);
                for (int i = 1; i < 17; i++)
                    for (int j = 1; j < 6; j++) {
                        buttons[i][j].setText("");
                    }

            } else {
                dispose();
            }
        }
    }


    public void Clear() {
        throwCount = 1;
        counter = 0;
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 6; j++) {
                btns[i][j].setIcon(null);
                btns[i][j].setText("");
                btns[i][j].setFlag(0);
                btns[i][j].setValue(0);
                btns[i][j].setEnabled(true);
            }
        btnThrowDice.setEnabled(true);
    }

    public void Fill(int i, int j) {
        int n = 0;
        for (int k = 0; k < 5; k++) {
            if (btns[0][k].getFlag() == 1) {
                ++n;
            }
        }
        int[] dice = new int[n];
        int index = 0;
        for (int k = 0; k < 5; k++) {
            if (btns[0][k].getFlag() == 1) {
                dice[index++] = btns[0][k].value;
            }
        }
        if ((i == 8 || i == 9) && dice.length < 5) {
            JOptionPane.showMessageDialog(this, "5 dice needed.");
        } else {
            engine.fields[i][j].calcFieldValue(dice, throwCount);
            engine.updateSums();
            result = engine.sum;
            lblResult.setText("Result: " + result);


            if (!((i == 8 || i == 9) && dice.length < 5))
                buttons[i][j].setText(engine.fields[i][j].value + "");

            buttons[7][5].setText(engine.fields[7][5].value + "");
            buttons[10][5].setText(engine.fields[10][5].value + "");
            buttons[16][5].setText(engine.fields[16][5].value + "");

            for (int k = 1; k <= 4; k++) {
                buttons[7][k].setText(engine.fields[7][k].value + "");
                buttons[10][k].setText(engine.fields[10][k].value + "");
                buttons[16][k].setText(engine.fields[16][k].value + "");
            }

            engine.fields[i][j].setIsFillable(false);
            if ((j == 1 && i == 6) || (j == 1 && i == 9)) {
                engine.fields[i + 2][j].setIsFillable(true);
            } else if (j == 1) engine.fields[i + 1][j].setIsFillable(true);

            if ((j == 3 && i == 11) || (j == 3 && i == 8)) {
                engine.fields[i - 2][j].setIsFillable(true);
            } else if (j == 3) engine.fields[i - 1][j].setIsFillable(true);
            else if (j == 3 && i == 1) {
                engine.fields[i - 1][j].setIsFillable(true);
            }
            throwCount = 1;
            lblThrowCount.setText("Throw Count: " + throwCount);
            if (engine.gameOver()) {
                JOptionPane.showMessageDialog(this, "Game Over!"
                        + "\n" + lblResult.getText());
                if (highScore < result) {
                    highScoreTxt = JOptionPane.showInputDialog(this, "Congrats!"
                            + "\nNew High score!");
                    highScore = result;
                    lblHighScore.setText("High Score: " + highScore + "       Player: " + highScoreTxt);
                }
                int option = JOptionPane.showConfirmDialog(this, "Start new Game?", "Game Over", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    engine.newGame();
                    Clear();
                    throwCount = 1;
                    lblThrowCount.setText("Throw Count: " + throwCount);
                    lblResult.setText("Result: " + engine.sum);
                    for (int k1 = 1; k1 < 17; k1++)
                        for (int k2 = 1; k2 < 6; k2++) {
                            buttons[k1][k2].setText("");
                        }
                } else {
                    this.dispose();
                }
            }
        }
    }

    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/Sounds/dieThrow3.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


