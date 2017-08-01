; Problem 82 Word Chains
(fn [wset] (let [chained? (fn chained? [s1 s2] (or (every? #(>= 1 (count %)) (vector s1 s2))
								(if (= (first s1) (first s2)) (chained? (rest s1) (rest s2)) 
        	               		(if (= (last s1) (last s2)) (chained? (butlast s1) (butlast s2)) false)) ))
                 filter-set (fn [s el] (set (filter #(not (= % el)) s)))
                 chained-seq? (fn chained-seq? 
                              ([s el] (if (empty? (rest s)) (chained? el (first s))
                                          (some #(and (chained? el %) (chained-seq? (filter-set s %) %)) s)))
                              ([s] (some identity (map #(chained-seq? (filter-set s %) %) s))))]
             (= true (chained-seq? wset))))
