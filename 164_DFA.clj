(fn [{st :start ac :accepts mt :transitions}] 
  (let [fup0 (fn [qp mc mp] 
               (reduce (fn [mc [a q]] 
                         (assoc mc q (concat (get mc q)
                                       (map #(concat % [a]) (get mp qp)))))
                 mc (into [] (get mt qp))))
        fup (fn [mp] (reduce #(fup0 %2 % mp) {} (keys mp)))
        sm (take-while not-empty (rest (iterate fup (hash-map st [[]]))))]
    (map #(apply str %) (mapcat (fn [[q s]] (if (get ac q) s))
                          (mapcat #(into [] %) sm)))))
