(fn fdfa [{states :states alphabet :alphabet start :start
             accepts :accepts transitions :transitions}] 
  (let [f-update-map0 
          (fn [q-prev cur-map prev-map] 
            (reduce 
              (fn [cur-map [a q]] 
                (assoc cur-map q 
                  (set (concat (get cur-map q)
                         (map #(concat % [a]) (get prev-map q-prev))))))
              cur-map (into [] (get transitions q-prev))))
        f-update-map 
          (fn [prev-map]
            (reduce #(f-update-map0 %2 % prev-map) {} (keys prev-map)))
        maps-seq (take-while #(not= {} %) 
                   (rest (iterate f-update-map (hash-map start [[]]))))]
    (map #(apply str %) (mapcat (fn [[q s]] (if (get accepts q) (seq s)))
                          (mapcat #(into [] %) maps-seq)))))
