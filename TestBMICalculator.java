import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBMICalculator {

	@Test
	Public void test0() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(145,5,4));
	}
	@Test
	Public void test1() {
		assertEquals(BMICalculator.Type.UNDERWEIGHT, BMICalculator.calculate(89,5,4));
	}
	@Test
	Public void test2() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(140,5,4));
	}
	@Test
	Public void test3() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(128,5,4));
	}
	@Test
	Public void test4() {
		assertEquals(BMICalculator.Type.OBESE, BMICalculator.calculate(204,5,4));
	}
	@Test
	Public void test5() {
		assertEquals(BMICalculator.Type.OBESE, BMICalculator.calculate(221,5,4));
	}
	@Test
	Public void test6() {
		assertEquals(BMICalculator.Type.OVERWEIGHT, BMICalculator.calculate(161,5,4));
	}
	@Test
	Public void test7() {
		assertEquals(BMICalculator.Type.OVERWEIGHT, BMICalculator.calculate(161,5,4));
	}
	@Test
	Public void test8() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(132,5,4));
	}
	@Test
	Public void test9() {
		assertEquals(BMICalculator.Type.UNDERWEIGHT, BMICalculator.calculate(88,5,4));
	}
}import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBMICalculator {

	@Test
	Public void test0() {
		assertEquals(BMICalculator.Type.OBESE, BMICalculator.calculate(228,5,4));
	}
	@Test
	Public void test1() {
		assertEquals(BMICalculator.Type.UNDERWEIGHT, BMICalculator.calculate(94,5,4));
	}
	@Test
	Public void test2() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(109,5,4));
	}
	@Test
	Public void test3() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(120,5,4));
	}
	@Test
	Public void test4() {
		assertEquals(BMICalculator.Type.UNDERWEIGHT, BMICalculator.calculate(106,5,4));
	}
	@Test
	Public void test5() {
		assertEquals(BMICalculator.Type.OBESE, BMICalculator.calculate(201,5,4));
	}
	@Test
	Public void test6() {
		assertEquals(BMICalculator.Type.OVERWEIGHT, BMICalculator.calculate(148,5,4));
	}
	@Test
	Public void test7() {
		assertEquals(BMICalculator.Type.OVERWEIGHT, BMICalculator.calculate(166,5,4));
	}
	@Test
	Public void test8() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(125,5,4));
	}
	@Test
	Public void test9() {
		assertEquals(BMICalculator.Type.UNDERWEIGHT, BMICalculator.calculate(95,5,4));
	}
}