package pl.essay.tst.itemDao;

import org.junit.Assert;
import org.junit.Test;

public class Trivial {
	@Test
	public void testSimpleStuff() {
		String name = "ProS pringHi  bernate";
		Assert.assertEquals("ProSpringHibernate", name);
	}
}
