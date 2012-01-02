package uk.chrismay.springtest.dao.jpa;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import uk.chrismay.springtest.domain.Ride;

public class RideMatcher extends TypeSafeMatcher<Ride> {

	private final Ride matchTo;

	public RideMatcher(Ride matchTo){
		this.matchTo = matchTo;
		
	}
	@Override
	public void describeTo(Description description) {
		description.appendText(matchTo.toString());

	}

	@Override
	public boolean matchesSafely(Ride item) {
		boolean commentsMatch = false;
		if ((matchTo.getComments() == null) && item.getComments() == null){
			commentsMatch = true;
		}else if ((matchTo.getComments()!= null) && (item.getComments() != null ) && item.getComments().equals(matchTo.getComments() )){
			commentsMatch = true;
		}
		
		return commentsMatch &&
				item.getRoute().getName().equals(matchTo.getRoute().getName());
	}

}
