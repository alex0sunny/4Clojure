(fn [fsums ffilt & sets] (ffilt (map fsums sets)))
(fn [s] (reduce #(apply conj % %2 (map (partial + %2) %)) #{} s))
(fn [ss] (not= [] (reduce #(filter %2 %) ss)))
