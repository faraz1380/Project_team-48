package controller;


import models.*;
import view.Page;

public class LoginRegister {

    public static Account createAccount(String type, String username) throws Exception {
        if (Account.existsUsername(username)) {
            throw new ExistUsernameException("username exists");
        }
        if (type.equals("manager")) {
            if (! Manager.getAllManagers().isEmpty()){
                throw new ExistManagerException("manager exist");
            }
            return new Manager(username);
        } else if (type.equals("seller")) {
            Seller seller = new Seller(username);
            new Request(seller).addSeller(seller);
            return seller;
        } else {
            return new Buyer(username);
        }
    }
    public static void login(Account account, Page page){
        if (page.getName().equalsIgnoreCase("login/register Page")){
            Page.setAccount(account);
            account.getAccountPage(page).execute();
        }else {
            Page.setAccount(account);
            page.execute();
        }

    }

    public static class ExistManagerException extends Exception{
        public ExistManagerException(String message) {
            super(message);
        }
    }

    public static class ExistUsernameException extends Exception{
        public ExistUsernameException(String message) {
            super(message);
        }
    }

}