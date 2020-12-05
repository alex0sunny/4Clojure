; Game of Life
; Difficulty:	Hard
; Topics:	game


; The game of life is a cellular automaton devised by mathematician John Conway.

; The 'board' consists of both live (#) and dead ( ) cells. Each cell interacts with its eight
; neighbours (horizontal, vertical, diagonal), and its next state is dependent on the following rules:

; 1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
; 2) Any live cell with two or three live neighbours lives on to the next generation.
; 3) Any live cell with more than three live neighbours dies, as if by overcrowding.
; 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

; Write a function that accepts a board, and returns a board representing the next generation of cells.

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
