package org.hibernate.validator.constraints.impl;

import java.util.regex.Matcher;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
 
/**
 * Checks that a given string is a well-formed email address.
 * <p>
 * The specification of a valid email can be found in
 * <a href="http://www.faqs.org/rfcs/rfc2822.html">RFC 2822</a>
 * and one can come up with a regular expression matching <a href="http://www.ex-parrot.com/~pdw/Mail-RFC822-Address.html">
 * all valid email addresses</a> as per specification. However, as this
 * <a href="http://www.regular-expressions.info/email.html">article</a> discusses it is not necessarily practical to
 * implement a 100% compliant email validator. This implementation is a trade-off trying to match most email while ignoring
 * for example emails with double quotes or comments.
 * </p>
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {
    private static String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static String DOMAIN = "(" + ATOM + "+(\\." + ATOM + "+)*";
    private static String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
 
  
    public void initialize(NotEmpty annotation) {
    }
 
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	boolean result = value!=null&&!value.trim().equals("");
		System.out.println("result:"+result);
//		if(1==1)
//		throw new ValidationException("123");
		return result;
    }
}
