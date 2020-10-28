; Problem 94 Game of Life
(fn [field]
  (let [wrapped-rows (#(concat (list %) field (list %)) (apply str (repeat (count (first field)) " ")))
        wrapped-field (map #(str " " % " ") wrapped-rows)
        partition-by3 (partial partition 3 1)
        update-table (fn [f-update table] (map (partial map f-update) table))
        partitioned-rows (update-table partition-by3 (partition-by3 wrapped-field))
        rows-of-areas (map (partial apply map list) partitioned-rows)
        rows-of-flattened (update-table flatten rows-of-areas)
        rows-of-maps (update-table 
                       #(hash-map :cell (= \# (nth % 4)) :neighbours 
                         ((fn [s] (count (filter (partial = \#) 
                           (concat (take 4 s) (take-last 4 s))))) %))
                       rows-of-flattened)
        get-new-cell (fn [area-map] (let [cell (:cell area-map) neighbours (:neighbours area-map)]
          (cond (or (< neighbours 2) (> neighbours 3)) \space
                (or (= neighbours 3) (= cell true)) \#
                :else \space)))
        new-cells (update-table get-new-cell rows-of-maps)]
    (vec (map #(apply str %) new-cells))))
