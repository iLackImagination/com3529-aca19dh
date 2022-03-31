import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTriangle {

	@Test
	Public void test0() {
		assertEquals(Triangle.Type.SCALENE, Triangle.classify(5,8,10));
	}
	@Test
	Public void test1() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(6,2,3));
	}
	@Test
	Public void test2() {
		assertEquals(Triangle.Type.ISOSCELES, Triangle.classify(8,2,8));
	}
	@Test
	Public void test3() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(7,4,0));
	}
	@Test
	Public void test4() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(1,3,4));
	}
	@Test
	Public void test5() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(10,1,4));
	}
	@Test
	Public void test6() {
		assertEquals(Triangle.Type.SCALENE, Triangle.classify(5,5,9));
	}
	@Test
	Public void test7() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(7,1,9));
	}
	@Test
	Public void test8() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(0,3,1));
	}
	@Test
	Public void test9() {
		assertEquals(Triangle.Type.SCALENE, Triangle.classify(4,4,5));
	}
	@Test
	Public void test10() {
		assertEquals(Triangle.Type.SCALENE, Triangle.classify(2,6,7));
	}
	@Test
	Public void test11() {
		assertEquals(Triangle.Type.EQUILATERAL, Triangle.classify(8,8,8));
	}
	@Test
	Public void test12() {
		assertEquals(Triangle.Type.INVALID, Triangle.classify(3,1,9));
	}
	@Test
	Public void test13() {
		assertEquals(Triangle.Type.ISOSCELES, Triangle.classify(9,9,4));
	}
}