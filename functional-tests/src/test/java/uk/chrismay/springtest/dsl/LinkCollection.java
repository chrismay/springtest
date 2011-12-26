package uk.chrismay.springtest.dsl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.xml.sax.SAXException;

import uk.chrismay.springtest.dsl.http.WebConversationWrapper;

import junit.framework.Assert;
import static com.google.common.collect.Collections2.*;

import com.google.common.base.Predicate;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

public class LinkCollection {

	private final Collection<WebLink> links;

	public LinkCollection(WebLink[] weblinks) {
		links = Arrays.asList(weblinks);
	}

	public void WithText(final String linkText) {
		Collection<WebLink> matching = findLinksWithText(linkText);

		Assert.assertTrue("Couldn't find a link with the text '" + linkText
				+ "' on the page", matching.size() > 0);

	}

	private Collection<WebLink> findLinksWithText(final String linkText) {
		Collection<WebLink> matching = filter(links, new Predicate<WebLink>() {
			public boolean apply(WebLink arg0) {
				return arg0.getText().equals(linkText);
			}
		});
		return matching;
	}

	public WebResponse clickLink(String linkText,
			WebConversationWrapper conversation) {
		WebLink link = findLinksWithText(linkText).iterator().next();
		try {
			return link.click();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}

	}

}
