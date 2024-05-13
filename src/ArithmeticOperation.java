import java.util.function.BiFunction;

final class ArithmeticOperation <T extends Number> implements BiFunction<T, T, Float> {

    private final BiFunction<T, T, Float> arithmeticOperationMixin;

    ArithmeticOperation(BiFunction<T, T, Float> arithmeticOperationMixin) {
        this.arithmeticOperationMixin = arithmeticOperationMixin;
    }

    @Override
    public Float apply(T t, T t2) {
        return this.arithmeticOperationMixin.apply(t, t2);
    }

    public Float operation(T t, T t2) {
        return this.apply(t, t2);
    }

}
