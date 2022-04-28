package hello.jdbc.repository.ex.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UnCheckedTest {



    @Test
    void unchecked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throw() {
        Service service = new Service();
        Assertions.assertThatThrownBy(() -> service.callThrow()).isInstanceOf(MyUnCheckedException.class);

    }

    static class MyUnCheckedException extends RuntimeException{
        public MyUnCheckedException(String message) {
            super(message);
        }
    }


    /**
     * unchecked 예외는
     * 예외를 잡거나 던지지 않아도 된다
     * 예외를 잡지 않으면 자동으로 밖으로 던진다.
     */
    static class Service {



        public void callCatch() {
            Repository repository = new Repository();

            /**
             * 필요한 경우 예외를 잡아서 처리 해도됀다
             */
            try {
                repository.call();
            } catch (MyUnCheckedException e) {
                log.info("예외처리, message = {}" , e.getMessage(), e);
            }
        }

        /**
         * 예외를 잡지 않아도 된다 자연스럽게 상위로 넘어간다
         * 체크 예외와 다르게 throws 예외 선언을 하지 않아도 된다.
         */
        public void callThrow() {
            Repository repository = new Repository();
            repository.call();
        }
    }

    static class Repository {
        public void call() {
            throw new MyUnCheckedException("ex");
        }
    }
}
