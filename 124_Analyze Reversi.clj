(fn [brd wb]
  (let [bw (wb '{b w w b}) 
        rg (range 4)
        r (map #(map list rg (repeat %)) rg) 
        l (apply map list r)
        d (map #(map list rg %) (mapcat #(list % (reverse %))
                                        [(range -1 3) rg (range 1 5)]))
        c (mapcat #(list % (rest %) (butlast %)) (concat r l d))
        fw (fn [bd] (mapcat #(rest (butlast %))
                            (filter #(#{[wb bw wb] [wb bw bw wb]}
                                        (map (partial get-in bd) %)) c)))
        prs (filter #(= 'e (get-in brd %)) (apply concat r))]
    (reduce (fn [m p] ( #(if (empty? %) m (assoc m p (set %))) 
                    	  (fw (assoc-in brd p wb)) )) {} prs)))
