package com.kastourik12.urlshortener.utils.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class URLValidator implements ConstraintValidator<URL, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!validUrl(s)){
                String[] payloadUrlStrings = s.split("\\.");
                if( payloadUrlStrings.length  > 1 && !payloadUrlStrings[0].isEmpty() && !payloadUrlStrings[1].isEmpty() )
                    s = ("https://" + s);
            return validUrl(s);
        }
        return true;
    }



    private boolean validUrl(String url)
    {
        try {
            new java.net.URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
