(fn [b e]
  (let [s (apply str (take-while #(<= % e) (iterate #(* % %) b)))
        [l] (drop-while #(< (* % %) (count s)) (range))
        els (#(butlast (interleave % %)) (range 1 (inc l)))
        xs (->> (map list els (cycle [[1 1] [1 -1] [-1 -1] [-1 1]]))
                (reductions 
                  (fn [xs [el d]] 
                    (#(take el (rest (iterate (partial map + d) %))) 
                      (last xs))) '((0 0)))
                (#(butlast (mapcat identity %))))
        m (into {} (map vector xs (concat s (repeat "*"))))
        yxs (map #(map % xs) [first second])
        [[ymi xmi] [yma xma]] (map #(map (partial apply %) yxs)
                                [min max])]
    (vec (for [y (range ymi (inc yma))]
           (apply str (for [x (range xmi (inc xma))] 
                        (get m [y x] " ")))))))
