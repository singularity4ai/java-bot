package com.singularai.watty.utils;

import java.lang.management.ThreadMXBean;

/**
 * Created by vamsi on 13/03/17.
 */
public class WattyException extends Throwable {
    public WattyException(String message, Throwable cause) {
        super(message, cause);
    }
}
