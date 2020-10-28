(fn [fsums ffilt & sets] (not= [] (ffilt (map fsums sets))))
(partial reduce #(apply conj % %2 (map + % (repeat %2))) #{})
(partial reduce #(filter %2 %))
