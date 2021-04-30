package Academy;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolutionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void firstTest() throws IOException {
		BufferedReader nxt = new BufferedReader(new FileReader("src/Academy/tests/0.out"));
		String line = nxt.readLine();
		ArrayList<String> expected = new ArrayList<>();
		while(line != null) {
			expected.add(line);
			line = nxt.readLine();
		}
		ArrayList<String> recieved = Solution.names("src/Academy/tests/0.in");
		
		assertEquals(expected, recieved);
		
	}

}
