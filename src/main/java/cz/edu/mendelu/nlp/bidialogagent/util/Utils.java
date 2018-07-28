package cz.edu.mendelu.nlp.bidialogagent.util;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Utils {

	public static Stream convertIteratorToStream(Iterator sourceIterator){
		Iterable iterable = () -> sourceIterator;
		return StreamSupport.stream(iterable.spliterator(), false);
	}
}
