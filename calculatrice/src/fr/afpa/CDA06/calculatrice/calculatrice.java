package fr.afpa.CDA06.calculatrice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculatrice extends JFrame {
    private JPanel container = new JPanel();

    String[] tableau = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0","=",
            "effacer", "+", "-", "*", "/"};

    JButton[] tableau_buton = new JButton[16];
    private JLabel screen = new JLabel();

    private double digit1;
    private boolean clicOperator = false, update = false;
    private String operator = "";

    public calculatrice(){
        this.setSize(300,250);
        this.setTitle("Calculette");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        intInterface();
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void intInterface(){

        Font police = new Font("Arial", Font.BOLD, 20);
        screen = new JLabel("0");
        screen.setFont(police);

        screen.setHorizontalAlignment(JLabel.RIGHT);
        screen.setPreferredSize(new Dimension(220, 20));
        JPanel operator = new JPanel();
        operator.setPreferredSize(new Dimension(55, 225));
        JPanel digit = new JPanel();
        digit.setPreferredSize(new Dimension(165, 225));
        JPanel panScreen = new JPanel();
        panScreen.setPreferredSize(new Dimension(220, 30));

        //On parcourt le tableau initialisé
        //afin de créer nos boutons
        for(int i = 0; i < 16; i++){
            tableau_buton[i] = new JButton(tableau[i]);

            switch(i){
                //Pour chaque élément situé à la fin du tableau
                //et qui n'est pas un chiffre
                //on définit le comportement à avoir grâce à un listener
                case 10 :
                    tableau_buton[i].addActionListener(new Egality());
                    digit.add(tableau_buton[i]);
                    break;
                case 11 :
                    tableau_buton[i].setForeground(Color.red);
                    tableau_buton[i].addActionListener(new Annulation());
                    operator.add(tableau_buton[i]);
                    break;
                case 12 :
                    tableau_buton[i].addActionListener(new Addition());

                    operator.add(tableau_buton[i]);
                    break;
                case 13 :
                    tableau_buton[i].addActionListener(new Soustraction());

                    operator.add(tableau_buton[i]);
                    break;
                case 14 :
                    tableau_buton[i].addActionListener(new Multiplication());

                    operator.add(tableau_buton[i]);
                    break;
                case 15 :
                    tableau_buton[i].addActionListener(new Division());

                    operator.add(tableau_buton[i]);
                    break;
                default :
                    //Par défaut, ce sont les premiers éléments du tableau
                    //donc des chiffres, on affecte alors le bon listener
                    digit.add(tableau_buton[i]);
                    tableau_buton[i].addActionListener(new PrintDigit());
                    break;
            }
        }
        panScreen.add(screen);
        panScreen.setBorder(BorderFactory.createLineBorder(Color.black));
        panScreen.setSize(10,10);
        container.add(panScreen, BorderLayout.CENTER);
        container.add(digit, BorderLayout.NORTH);
        container.add(operator, BorderLayout.EAST);
    }

    //Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
    private void calcul(){
        if(operator.equals("+")){
            digit1 = digit1 +
                    Double.valueOf(screen.getText()).doubleValue();
            screen.setText(String.valueOf(digit1));
        }
        if(operator.equals("-")){
            digit1 = digit1 -
                    Double.valueOf(screen.getText()).doubleValue();
            screen.setText(String.valueOf(digit1));
        }
        if(operator.equals("*")){
            digit1 = digit1 *
                    Double.valueOf(screen.getText()).doubleValue();
            screen.setText(String.valueOf(digit1));
        }
        if(operator.equals("/")){
            try{
                digit1 = digit1 /
                        Double.valueOf(screen.getText()).doubleValue();
                screen.setText(String.valueOf(digit1));
            } catch(ArithmeticException e) {
                screen.setText("0");
            }
        }
    }


    //Listener utilisé pour les chiffres
    //Permet de stocker les chiffres et de les afficher
    class PrintDigit implements ActionListener {
        public void actionPerformed(ActionEvent e){
            //On affiche le chiffre additionnel dans le label
            String str = ((JButton)e.getSource()).getText();
            if(update){
                update = false;
            }
            else{
                if(!screen.getText().equals("0"))
                    str = screen.getText() + str;
            }
            screen.setText(str);
        }
    }

    //Listener affecté au bouton =
    class Egality implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            calcul();
            update = true;
            clicOperator = false;
        }
    }

    //Listener affecté au bouton +
    class Addition implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperator){
                calcul();
                screen.setText(String.valueOf(digit1));
            }
            else{
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                clicOperator = true;
            }
            operator = "+";
            update = true;
        }
    }

    //Listener affecté au bouton -
    class Soustraction implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperator){
                calcul();
                screen.setText(String.valueOf(digit1));
            }
            else{
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                clicOperator = true;
            }
            operator = "-";
            update = true;
        }
    }

    //Listener affecté au bouton *
    class Multiplication implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperator){
                calcul();
                screen.setText(String.valueOf(digit1));
            }
            else{
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                clicOperator = true;
            }
            operator = "*";
            update = true;
        }
    }

    //Listener affecté au bouton /
    class Division implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperator){
                calcul();
                screen.setText(String.valueOf(digit1));
            }
            else{
                digit1 = Double.valueOf(screen.getText()).doubleValue();
                clicOperator = true;
            }
            operator = "/";
            update = true;
        }
    }

    //Listener affecté au bouton de remise à zéro
    class Annulation implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            clicOperator = false;
            update = true;
            digit1 = 0;
            operator = "";
            screen.setText("");
        }
    }
}