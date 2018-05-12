package util;

import java.util.StringJoiner;

public class PyLikePrinter {
    public PyLikePrinter() {
        this(" ");
    }
    public PyLikePrinter(String sep) {
        joiner_ = new StringJoiner(sep);
    }

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
