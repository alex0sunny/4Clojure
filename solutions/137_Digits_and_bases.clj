(fn f [n b] (if (< n b) [n] (conj (f (quot n b) b) (mod n b))))
