package nsr.loui.util;

import java.util.StringJoiner;

/**
 * Utility class print variable arguments.
 */
public class PyLikePrinter {
    /**
     * Default sep is a space character.
     */
    public PyLikePrinter() {
        this(" ");
    }

    /**
     * @param sep py-like sep.
     */
    public PyLikePrinter(String sep) {
        joiner_ = new StringJoiner(sep);
    }

    /**
     * Print ARGS joined by SEP, finally print new line.
     * @param args what to print.
     */
    public void print(Object... args) {
        for(Object arg: args) {
            joiner_.add(arg.toString());
        }
        System.out.println(joiner_);
    }

    //
    // Fields
    private final StringJoiner joiner_;
}
