package uk.chrismay.springtest.dsl;

import static com.google.common.collect.Collections2.filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.Assert;

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

	public WebResponse clickLink(String linkText, Session session) {
		Iterator<WebLink> matchingLinks = findLinksWithText(linkText).iterator();
		if (matchingLinks.hasNext()){
			return session.click(matchingLinks.next());
		}else{
			Assert.fail(String.format("Can't find a link with text '%s' on page at %s",linkText, session.currentPage().getUrl()));
			return null;
		}
	}

}
