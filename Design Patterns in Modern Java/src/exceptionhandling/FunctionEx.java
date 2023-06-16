package exceptionhandling;

public interface FunctionEx<T, R> {
    R apply(T t) throws Exception;
}
