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
package org.cactoos.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.cactoos.func.StickyScalar;
import org.cactoos.func.UncheckedScalar;

/**
 * Tail of iterator.
 *
 * <p>There is no thread-safety guarantee.</p>
 *
 * @author Vladislav Cheparin (ivladislav969@gmail.com)
 * @version $Id$
 * @param <T> Element type
 * @since 0.11
 */
public final class TailOfIterator<T> implements Iterator<T> {

    /**
     * Decorated iterator.
     */
    private final UncheckedScalar<Iterator<T>> tail;

    /**
     * Ctor.
     * @param iterator Decorated iterator
     * @param skip Count skip elements from tail
     */
    public TailOfIterator(final Iterator<T> iterator, final int skip) {
        this.tail = new UncheckedScalar<>(
            new StickyScalar<>(
                () -> {
                    final Collection<T> temp = new LinkedList<>();
                    while (iterator.hasNext()) {
                        temp.add(iterator.next());
                    }
                    return new HeadOfIterator<>(
                        new LinkedList<>(temp).descendingIterator(),
                        skip
                    );
                }
            )
        );
    }

    @Override
    public boolean hasNext() {
        return this.tail.value().hasNext();
    }

    @Override
    public T next() {
        return this.tail.value().next();
    }
}
