package components.deque;

/**
 * Layered implementations of secondary methods for {@code Deque}.
 *
 * @param <T>
 *            type of {@code Deque} entries
 */
public abstract class DequeSecondary<T> implements Deque<T> {

    /**
     * Constructor.
     */
    public DequeSecondary() {
    }

    /*
     * Secondary methods ------------------------------------
     */

    @Override
    public void flip() {
        if (this.length() > 0) {
            //pop front element, flip deque, push to back
            T front = this.popFront();
            this.flip();
            this.pushBack(front);
        }
    }

    @Override
    public T front() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T front = this.popFront();
        this.pushFront(front);
        return front;
    }

    @Override
    public T back() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T back = this.popBack();
        this.pushBack(back);
        return back;
    }

    @Override
    public T replaceFront(T x) {
        assert this.length() > 0 : "Violation of: this /= <>";

        //Pop old front, push x to new front, return old front
        T oldFront = this.popFront();
        this.pushFront(x);
        return oldFront;
    }

    @Override
    public T replaceBack(T x) {
        assert this.length() > 0 : "Violation of: this /= <>";

        //Pop old back, push x to new back, return old back
        T oldBack = this.popBack();
        this.pushBack(x);
        return oldBack;
    }

    /*
     * Object methods ----------------------------------------------
     */

    @Override
    public int hashCode() {
        int hash = 0;

        /*
         * Alternate adding and subtracting hash values of all elements in Deque
         * times its position
         */
        int i = 0;
        for (T current : this) {
            if (i % 2 == 0) {
                hash += (i++ * current.hashCode());
            } else {
                hash -= (i++ * current.hashCode());
            }
        }

        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        boolean isEqual = false;

        if (obj == this) {
            //Objects are equal if both are same reference
            return true;
        } else if (obj == null) {
            //Objects cannot be equal is obj is null (this can never be null)
            return false;
        } else if (!(obj instanceof Deque<?>)) {
            //Objects cannot be equal if obj isn't a deque
            return false;
        } else {
            //Now we know obj is a deque so we can safely cast
            @SuppressWarnings("unchecked")
            Deque<T> objDeque = (Deque<T>) obj;

            /*
             * To optimize execution, we know that objects can only be equal if
             * they have the same hash code and same length
             */
            if (this.hashCode() == objDeque.hashCode()
                    && this.length() == objDeque.length()) {
                if (this.length() == 0) {
                    isEqual = true;
                } else {
                    /*
                     * Objects are equal if first element is equal and rest of
                     * deques are equal
                     */
                    T thisFront = this.popFront();
                    T objFront = objDeque.popFront();

                    isEqual = thisFront.equals(objFront)
                            && this.equals(objDeque);

                    //Restore
                    this.pushFront(thisFront);
                    objDeque.pushFront(objFront);
                }
            }
        }

        return isEqual;
    }

    @Override
    public final String toString() {
        StringBuilder thisStr = new StringBuilder("<");

        if (this.length() > 0) {
            T front = this.popFront();
            thisStr.append(front);

            //Iterate through rest of deque and add each element to string
            for (T x : this) {
                thisStr.append("," + x);
            }

            //Restore and finish
            this.pushFront(front);
        }
        thisStr.append(">");

        return thisStr.toString();
    }

}
