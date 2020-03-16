(fn [fs ff & ss] (apply ff (map fs ss)))
(partial reduce #(into (conj % %2) (map + % (repeat %2))) #{})
(fn [f & r] (not= #{} (reduce #(set (filter % %2)) f r)))
