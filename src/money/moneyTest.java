package money;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//TODO $5 + CHF = $10 (レートが２：１の場合)
//TODO $5*2 = $10
//TODO amountをprivateにする
//TODO Dollerの副作用をどうする
//TODO moneyの丸め処理をどうするか
//TODO nullとの等価性比較
//TODO 他のオブジェクト等価性比較

public class moneyTest {

	@Test
	public void testMultiplication() {
		Money five = Money.dollar(5);
		assertEquals(Money.dollar(10), five.times(2));
		assertEquals(Money.dollar(15), five.times(3));
	}

	@Test
	public void testEquality() {
		assertTrue(Money.dollar(5).equals(Money.dollar(5)));
		assertFalse(Money.dollar(5).equals(Money.dollar(6)));
		assertFalse(Money.franc(5).equals(Money.dollar(5)));
	}

	@Test
	public void testCurrency() {
		assertEquals("USD", Money.dollar(1).currency());
		assertEquals("CHF", Money.franc(1).currency());
	}

	@Test
	public void testSimpleAddition() {
		Money five = Money.dollar(5);
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Money reduced = bank.reduce(sum, "USD");
		assertEquals(Money.dollar(10), reduced);
	}
}
