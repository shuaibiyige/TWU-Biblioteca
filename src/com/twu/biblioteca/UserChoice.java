package com.twu.biblioteca;

public enum UserChoice
{
    BOOKLIST("a"), CHECKOUT("b"), RETURN("c"), QUIT("d");

    private String choice;

    UserChoice(String choice)
    {
        this.choice = choice;
    }

    public String getChoice() {
        return choice;
    }

    public static void main(String[] args)
    {
        //System.out.println(UserChoice.BOOKLIST.getChoice());
        UserChoice c = UserChoice.valueOf(UserChoice.class, "a");
        System.out.println(c);
    }
}
