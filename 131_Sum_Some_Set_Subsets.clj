(fn [fr fset & ss] (apply fset (map fr ss)))
(fn [s] (reduce #(into (conj % %2) (map + % (repeat %2))) #{} s))
(fn [f & r] (not= #{} (reduce #(set (filter % %2)) f r)))
