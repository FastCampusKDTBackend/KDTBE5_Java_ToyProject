package me.smartstore.exception;

import me.smartstore.util.Message;

/**
 * super()를 호출하는 이유
 *
 * util.Message 인터페이스에서 ERR_MSG_INPUT_END 상수 선언
 * 이 클래스의 생성자가 호출되면 RuntimeException 클래스의 생성자를 호출하게 됨.
 * 이때 super(Message.ERR_MSG_INPUT_END)가 실행 > Message 인터페이스의 ERR_MSG_INPUT_END 상수 값을 인자로 전달
 * 이후 RuntimeException의 생성자가 Message.ERR_MSG_INPUT_END 값을 포함한 예외 객체를 생성하여 반환함.
 */
public class InputEndException extends RuntimeException {
    public InputEndException() {
        super(Message.ERR_MSG_INPUT_END);
    }
}
