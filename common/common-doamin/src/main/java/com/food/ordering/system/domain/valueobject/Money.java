package com.food.ordering.system.domain.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isGreaterThanZero() {
        return BigDecimal.ZERO.compareTo(this.amount) < 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount!=null && amount.compareTo(money.amount) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(amount.add(money.amount)));
    }

    public Money substract(Money money) {
        return new Money(setScale(amount.subtract(money.amount)));
    }

    public Money multiply(int value) {
        return new Money(setScale(amount.multiply(BigDecimal.valueOf(value))));
    }


    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    private BigDecimal setScale(BigDecimal input){
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}
