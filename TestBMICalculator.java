import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBMICalculator {

	@Test
	Public void test0() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(108,5,4));
	}
	@Test
	Public void test1() {
		assertEquals(BMICalculator.Type.UNDERWEIGHT, BMICalculator.calculate(105,5,4));
	}
	@Test
	Public void test2() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(140,5,4));
	}
	@Test
	Public void test3() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(110,5,4));
	}
	@Test
	Public void test4() {
		assertEquals(BMICalculator.Type.OBESE, BMICalculator.calculate(223,5,4));
	}
	@Test
	Public void test5() {
		assertEquals(BMICalculator.Type.OBESE, BMICalculator.calculate(224,5,4));
	}
	@Test
	Public void test6() {
		assertEquals(BMICalculator.Type.OVERWEIGHT, BMICalculator.calculate(162,5,4));
	}
	@Test
	Public void test7() {
		assertEquals(BMICalculator.Type.OVERWEIGHT, BMICalculator.calculate(158,5,4));
	}
	@Test
	Public void test8() {
		assertEquals(BMICalculator.Type.UNDERWEIGHT, BMICalculator.calculate(96,5,4));
	}
	@Test
	Public void test9() {
		assertEquals(BMICalculator.Type.NORMAL, BMICalculator.calculate(135,5,4));
	}
}