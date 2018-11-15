import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputGUI extends JFrame {

    JFrame frame = new JFrame("Input system");
    JPanel panel = new JPanel();
    JLabel loginLabel = new JLabel("Login:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField loginTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton registrationButton = new JButton("Sign in");
    JButton inputButton = new JButton("Log in");

    public InputGUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(360, 260));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.setLayout(null);
        loginLabel.setBounds(50, 40, 60, 25);
        passwordLabel.setBounds(50,90,60,25);
        loginTextField.setBounds(150,40,160,25);
        passwordField.setBounds(150,90,160,25);
        passwordField.setEchoChar('*');
        registrationButton.setBounds(80, 140, 100,25);
        inputButton.setBounds(200, 140, 100, 25);

        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = loginTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if(userName.compareTo("")==0 || password.compareTo("") == 0){
                    JOptionPane.showMessageDialog(frame, "Ошибка! Заполнены не все поля!");
                }
                else{
                    User user = new User(userName,password);
                    InputSystem inputSystem = new InputSystem();
                    if (inputSystem.registrationNewUser(user)){
                        JOptionPane.showMessageDialog(frame, "Пользователь успешно зарегистрирован");
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Ошибка! Такой пользователь уже зарегистрирован!");
                        loginTextField.setText("");
                        passwordField.setText("");
                    }
                }
            }
        });

        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = loginTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if(userName.compareTo("")==0 || password.compareTo("") == 0){
                    JOptionPane.showMessageDialog(frame, "Ошибка! Заполнены не все поля!");
                }
                else{
                    User user = new User(userName,password);
                    InputSystem inputSystem = new InputSystem();
                    if(inputSystem.input(user)){
                        frame.dispose();
                        MainGUI main = new MainGUI();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "Ошибка! Такой пользователь не зарегистрирован!");
                        loginTextField.setText("");
                        passwordField.setText("");
                    }
                }
            }
        });

        panel.add(loginLabel);
        panel.add(passwordLabel);
        panel.add(loginTextField);
        panel.add(passwordField);
        panel.add(registrationButton);
        panel.add(inputButton);
        frame.add(panel);
    }


}
