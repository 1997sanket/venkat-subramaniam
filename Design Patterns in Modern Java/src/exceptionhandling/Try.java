package exceptionhandling;

import java.util.function.Function;

public interface Try<T, R> {

    R getData();

    static <T,R> Try tryPlease(T t, FunctionEx<T,R> function) {

        try {
            R r = function.apply(t);
            return new Success(r);
        } catch (Exception e) {
            return new Failure(e);
        }
    }

}

class Success<R> implements Try {

    private R r;
    public Success(R r) {
        this.r = r;
    }

    @Override
    public R getData() {
        return r;
    }
}

class Failure<R extends Exception> implements Try {
    private R r;

    public Failure(R r) {
        this.r = r;
    }

    @Override
    public R getData() {
        return r;
    }
}
