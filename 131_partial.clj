(fn [fsums ffilt & sets] (apply ffilt (map fsums sets)))
(partial reduce #(apply conj % %2 (map + % (repeat %2))) #{})
(fn [& ss] (not= [] (reduce #(filter %2 %) ss)))
