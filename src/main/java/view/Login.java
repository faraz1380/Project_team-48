package view;

import controller.LoginRegister;
import models.Account;

import java.util.Scanner;

public class Login extends Page{
    public Login(Page parentPage){
        super(parentPage);
        this.name = "Login";
        subPages.put("back", new Back(this));
    }

    @Override
    public void execute() {
        Account account;
        if ((account = Account.getUserByName(parentPage.getMatcher().group(1)) )== null){
            System.out.println("username does not exist");
            new Back(this).execute();
        }else{
            System.out.println("password:");
            if(account.getPassword().equals(Page.scanner.nextLine())){
                LoginRegister.login(account,this.parentPage);
            }else {
                System.out.println("password is wrong");
                new Back(this).execute();
            }
        }
    }
}
