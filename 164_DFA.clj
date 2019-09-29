(fn [{st :start ac :accepts mt :transitions}] 
  (let [fup0 (fn [qp mc mp] 
               (reduce (fn [mc [a q]] 
                         (assoc mc q (concat (get mc q)
                                       (map #(str % a) (get mp qp)))))
                 mc (into [] (get mt qp))))
        fup (fn [mp] (reduce #(fup0 %2 % mp) {} (keys mp)))
        sm (take-while not-empty (iterate fup (hash-map st [""])))]
    (mapcat (fn [[q s]] (if (get ac q) s)) (mapcat seq sm))))
