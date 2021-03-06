/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos;

import org.cactoos.text.UncheckedText;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsEqual;

/**
 * Matcher for the content.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.2
 */
public final class TextHasString extends TypeSafeMatcher<Text> {

    /**
     * Matcher of the text.
     */
    private final Matcher<String> matcher;

    /**
     * Ctor.
     * @param text The text to match against
     */
    public TextHasString(final String text) {
        this(new IsEqual<>(text));
    }

    /**
     * Ctor.
     * @param mtr Matcher of the text
     */
    public TextHasString(final Matcher<String> mtr) {
        super();
        this.matcher = mtr;
    }

    @Override
    public boolean matchesSafely(final Text item) {
        return this.matcher.matches(new UncheckedText(item).asString());
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("Text with ");
        description.appendDescriptionOf(this.matcher);
    }

}
