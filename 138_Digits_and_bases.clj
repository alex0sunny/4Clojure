(fn f [n b] (if (< n b) [n] (concat (f (quot n b) b) [(mod n b)])))
