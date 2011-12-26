package uk.chrismay.springtest.dsl;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;
import static com.google.common.collect.Collections2.*;

import com.google.common.base.Predicate;
import com.meterware.httpunit.WebLink;

public class LinkCollection {

	private final Collection<WebLink> links;

	public LinkCollection(WebLink[] weblinks) {
		links = Arrays.asList(weblinks);
	}

	public void WithText(final String linkText) {
		for (WebLink link : links) {
			System.out.println("Link" + link.getText() +  ":" + link.getTarget());
		}
		Collection<WebLink> matching = filter(links, new Predicate<WebLink>() {
			public boolean apply(WebLink arg0) {
				return arg0.getText().equals(linkText);
			}
		});

		Assert.assertTrue("Couldn't find a link with the text '" + linkText
				+ "' on the page", matching.size() > 0);

	}

}
