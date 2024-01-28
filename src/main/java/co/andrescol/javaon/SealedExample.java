package co.andrescol.javaon;

/**
 * Sealed only permits certain classes to inherit it.
 * sealed is like a new kind of visibility
 */
sealed interface SealedExample permits SuperSealed, SuperSealed2 {
}

final class SuperSealed implements SealedExample {}
final class SuperSealed2 implements SealedExample {}