package edu.miu.cse.restfuldemo.exception.user;

public class UserNotFoundException extends RuntimeException{//unchecked exception
    public UserNotFoundException(String message) {
        super(message);//it sets message for our user defined exception
    }
}
