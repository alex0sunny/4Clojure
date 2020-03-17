(fn [fsums ffilt & sets] (apply ffilt (map fsums sets)))
(partial reduce #(apply conj % %2 (map + % (repeat %2))) #{})
(fn [f & r] (not= #{} (reduce #(set (filter % %2)) f r)))
