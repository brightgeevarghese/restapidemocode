package edu.miu.cse.restfuldemo.exception.user;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);//it sets message for our user defined exception
    }
}
