import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputSystem {

    private static String fileName = "users.txt";

    public InputSystem() {}

    //Регистрация нового пользователя
    //input par: объект User
    //output par: успешно пользователь зарегистрировался или нет
    public boolean registrationNewUser(User user){
        boolean result = false;
        File file = new File(fileName);
        if(file.exists()){ //если существует файл users.txt
            try {
                FileWriter writer = new FileWriter(fileName, true);
                String hashPass = Encryption.createEncryptionPass(user.getPassword()); //шифруем пароль
                String text = user.getUserName() + "=" + hashPass + "\r\n"; //формируем строку для записи
                if(!checking(user.getUserName(), hashPass)){ //если такого пользователя с таким паролем еще нет в системе
                    writer.write(text); // то записываем
                    result = true; //успешно
                }
                writer.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        else {
            System.out.println("Ошибка записи. Файл не существует.");
        }
        return result;
    }

    //Вход в систему
    //input par: объект User
    //output par: осуществлен вход в систему или нет
    public boolean input(User user){
        boolean result = false;
        File file = new File(fileName);
        if(file.exists()){ //если существует файл users.txt
            try {
                FileWriter writer = new FileWriter(fileName, true);
                String hashPass = Encryption.createEncryptionPass(user.getPassword()); //шифруем пароль
                String text = user.getUserName() + "=" + hashPass + "\r\n"; //формируем строку для записи
                if(checking(user.getUserName(), hashPass)){ //если такой пользователь с таким паролем есть в системе
                    result = true; //успешно
                }
                writer.close();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        else {
            System.out.println("Ошибка записи. Файл не существует.");
        }
        return result;
    }

    //Проверка: зарегистрирован ли пользователь в системе
    //input par: введенные данные пользователем
    //output par: true - такой пользователь уже есть, false - такого пользователя нет
    private static boolean checking(String userName, String hashPassword){
        boolean isConsist;
        ArrayList<String> linesFromFile = new ArrayList<String>(); //список из строк файла user.txt
        try{
            FileReader reader = new FileReader(fileName);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) { //пока есть следующая строка
                linesFromFile.add(scan.nextLine()); //добавляем строку в список
            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        for(int i=0; i<linesFromFile.size();i++){
            String[] subStr;
            subStr = linesFromFile.get(i).split("="); //делим строки по разделителю "=" из списка
            if (subStr[0].compareTo(userName)==0 && subStr[1].compareTo(hashPassword)==0){ //если веденные данные уже есть в файле users.txt
                return isConsist = true; //тогда true
            }
        }
        return isConsist = false; //иначе false
    }






}
