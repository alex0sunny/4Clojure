(fn [{st :start ac :accepts mt :transitions}] 
  (let [fup0 (fn [qp mc mp] 
               (reduce (fn [mc [a q]] 
                         (assoc mc q (concat (q mc)
                                       (map #(str % a) (qp mp)))))
                 mc (seq (qp mt))))
        fup (fn [mp] (reduce #(fup0 %2 % mp) {} (keys mp)))
        sm (take-while not-empty (iterate fup {st [""]}))]
    (mapcat (fn [[q s]] (if (q ac) s)) (mapcat seq sm))))
