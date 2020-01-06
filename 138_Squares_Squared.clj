(fn [b e]
  (let [s (apply str (take-while #(<= % e) (iterate #(* % %) b)))
        [l] (drop-while #(< (* % %) (count s)) (range))
        xs (->> (map list (#(interleave % %) (rest (range))) 
                          (cycle [[1 1] [1 -1] [-1 -1] [-1 1]]))
                (mapcat (fn [[ll d]] (take ll (repeat d))))
                (reductions #(map + % %2) '(0 0))
                (take (* l l)))
        m (into {} (map vector xs (concat s (repeat "*"))))
        yxs (map #(map % xs) [first second])
        [[ymi xmi] [yma xma]] (map #(map (partial apply %) yxs)
                                [min max])]
    (vec (for [y (range ymi (inc yma))]
           (apply str (for [x (range xmi (inc xma))] 
                        (get m [y x] " ")))))))
