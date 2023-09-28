import exeptions.WrongLoginException;
import exeptions.exeptions.WrongPasswordException;

import java.util.regex.Pattern;

public class Main {
    public final static String REQUIREMENTS = "Логин и пароль  содержит в себе только латинские буквы, цифры и" +
            " знак подчеркивания";
    public static void main(String[] args) {
        String login = "login";
        String password = "password";
        String confirmPassword = "password";

        try {
            checkLoginAndPassword(login, password,confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println(e.getMessage());
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Проверка логина и пароля выполнена.");
        }

    }

    public static void checkLoginAndPassword(String login, String password, String confirmPassword)
            throws WrongLoginException, WrongPasswordException {
        checkLogin(login);
        checkPassword(password, confirmPassword);

    }

    private static boolean checkLogin(String login) throws WrongLoginException {

        int maxLoginLength = 20;

        if (login.length() > maxLoginLength) {
            throw new WrongLoginException(String.format("Логин должен быть короче %s символов", maxLoginLength));
        }

        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{1,20}$");
        if(!p.matcher(login).matches()){
            throw new WrongLoginException(String.format("Логин %s не подходит под требования: %s",login,REQUIREMENTS));
        }
        return true;

    }
    private static boolean checkPassword(String password, String confirmPassword) throws WrongPasswordException,
            WrongLoginException {

        int maxPasswordLength =20;

        if(password.length()> maxPasswordLength){
            throw new WrongPasswordException(String.format("Пароль должен быть короче %s символов", maxPasswordLength));
        }
        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{1,20}$");
        if(!p.matcher(password).matches()){
            throw new WrongPasswordException(String.format("Пароль %s не подходит под требования: %s",
                    password,REQUIREMENTS));
        }
        if (!password.equals(confirmPassword)){
            throw new WrongPasswordException("Пароли не совпадают");
        }
        return true;

    }
}