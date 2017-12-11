package series;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SerieTest {

	@Test
	void testGetDureeRestanteString() {
		Serie s = new Serie("yo", 1,50,0);
		assertEquals("0 jours 0 h 50 m", s.getDureeRestanteString());
		s.setDureeAvg(120);
		assertEquals("0 jours 2 h 0 m", s.getDureeRestanteString());
		s.setDureeAvg(1528);
		assertEquals("1 jours 1 h 28 m", s.getDureeRestanteString());
	}

}
