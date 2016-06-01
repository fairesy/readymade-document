package com.readymade;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nhncorp.lucy.security.xss.XssPreventer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DocumentApplication.class)
@WebAppConfiguration
public class DocumentApplicationTests {

	@Test
	public void testXssPreventer() {
	    String dirty = "\"><script>alert('xss');</script>";
	    String clean = XssPreventer.escape(dirty);

	    Assert.assertEquals(clean, "&quot;&gt;&lt;script&gt;alert(&#39;xss&#39;);&lt;/script&gt;");
	    Assert.assertEquals(dirty, XssPreventer.unescape(clean));
	}

}
