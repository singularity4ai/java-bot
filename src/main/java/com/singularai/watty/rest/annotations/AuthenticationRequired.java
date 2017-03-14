package com.singularai.watty.rest.annotations;

/**
 * Created by vamsi on 13/03/17.
 */
import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NameBinding
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface AuthenticationRequired {

    /**
     * Some methods need to validate the data only if there is a user present, if not ignore some steps.
     * @return true. if the method can be accessed even if the user is Authenticated or not. default false.
     */
    boolean optional() default false;

    boolean allowInactive() default false;
}

