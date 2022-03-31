import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTriangle {

	@Test
	Public void test0() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(0,4,2));
	}
	@Test
	Public void test1() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(7,4,3));
	}
	@Test
	Public void test2() {
		assertEquals(Triangle.Type.SCALENE, Triangle.classify(6,4,4));
	}
	@Test
	Public void test3() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(6,9,1));
	}
	@Test
	Public void test4() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(1,1,7));
	}
	@Test
	Public void test5() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(2,9,5));
	}
	@Test
	Public void test6() {
		assertEquals(Triangle.Type.SCALENE, Triangle.classify(4,7,5));
	}
	@Test
	Public void test7() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(2,9,3));
	}
	@Test
	Public void test8() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(2,4,10));
	}
	@Test
	Public void test9() {
		assertEquals(Triangle.Type.SCALENE, Triangle.classify(7,4,4));
	}
	@Test
	Public void test10() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(7,9,0));
	}
	@Test
	Public void test11() {
		assertEquals(Triangle.Type.EQUILATERAL, Triangle.classify(2,2,2));
	}
	@Test
	Public void test12() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(1,10,1));
	}
	@Test
	Public void test13() {
		assertEquals(Triangle.Type.ISOSCELES, Triangle.classify(2,4,4));
	}
}