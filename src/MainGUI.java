import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainGUI {

    JFrame frame = new JFrame("Calculator");
    JPanel panel = new JPanel();
    JTextField calcTextField = new JTextField();
    JTextField resultTextField = new JTextField();
    ArrayList<JButton> listButtons = new ArrayList<>();
    Font font = new Font("TimesRoman", Font.BOLD, 30);
    final String[] ELEMENTS = {"c","/","*","<=","7","8","9","-","4","5","6","+","1","2","3","=",".","0", };

    public MainGUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(385, 610));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.setLayout(null);

        calcTextField.setBounds(10,10,350,40);
        calcTextField.setFont(font);
        calcTextField.setEnabled(false); //нет доступа
        //calcTextField.setEditable(false); //запрет на редактирование
        resultTextField.setBounds(10,60,350,40);
        resultTextField.setFont(font);
        resultTextField.setEnabled(false); //нет доступа
        //resultTextField.setEditable(false); //запрет на редактирование


        for(int i=0; i<ELEMENTS.length; i++){
            JButton button = new JButton();
            button.setText(ELEMENTS[i]);
            button.setFont(font);
            if(ELEMENTS[i] == "c"){
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calcTextField.setText("");
                        resultTextField.setText("");
                    }
                });
            }
            else if (ELEMENTS[i] == "<="){
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = calcTextField.getText();
                        if (text.compareTo("") != 0){
                            String newText = text.substring(0, text.length()-1);
                            calcTextField.setText(newText);
                        }
                    }
                });
            }
            else if (ELEMENTS[i] == "="){
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resultTextField.setText("Здесь будет результат");
                    }
                });
            }
            else {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = calcTextField.getText();
                        text += button.getText();
                        calcTextField.setText(text);
                    }
                });
            }
            listButtons.add(button);
        }

        int countRow = 5;
        int countColumn = 4;
        int incr1 = 0;
        int nextLevel = 0;
        for(int j=0; j<countRow; j++){
            int incr2 =0;
            for(int k=0; k<countColumn; k++){
                int num = k + nextLevel;
                if(ELEMENTS.length - 1 >= num) {
                    if(ELEMENTS[num] == "=") {
                        listButtons.get(num).setBounds(10 + incr2,110 + incr1,80,170);
                        panel.add(listButtons.get(num));
                        incr2 += 90;
                    }
                    else if (ELEMENTS[num] == "0"){
                        listButtons.get(num).setBounds(10 + incr2,110 + incr1,170,80);
                        panel.add(listButtons.get(num));
                        incr2 += 90;
                    }
                    else {
                        listButtons.get(num).setBounds(10 + incr2,110 + incr1,80,80);
                        panel.add(listButtons.get(num));
                        incr2 += 90;
                    }
                }
            }
            incr1 += 90;
            nextLevel += 4;
        }
        panel.add(calcTextField);
        panel.add(resultTextField);
        frame.add(panel);
    }

}
