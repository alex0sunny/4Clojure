(fn [fsums ffilt & sets] (apply ffilt (map fsums sets)))
(fn [s] (reduce #(apply conj % %2 (map (partial + %2) %)) #{} s))
(fn [f & r] (not= #{} (reduce #(set (filter % %2)) f r)))
