(fn [fs ff & ss] (apply ff (map fs ss)))
(fn [s] (reduce #(apply conj % %2 (map (partial + %2) %)) #{} s))
(fn [f & r] (not= #{} (reduce #(set (filter % %2)) f r)))
