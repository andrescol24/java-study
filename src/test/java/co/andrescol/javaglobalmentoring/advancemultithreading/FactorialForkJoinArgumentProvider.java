package co.andrescol.javaglobalmentoring.advancemultithreading;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

public class FactorialForkJoinArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(5, new BigInteger("120")),
                Arguments.of(0, BigInteger.ONE),
                Arguments.of(22, new BigInteger("1124000727777607680000")),
                Arguments.arguments(100, new BigInteger("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"))
        );
    }
}
