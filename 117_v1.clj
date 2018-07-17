;; In this solution we build field of 3x3 blocks ('bks'), in the center of each one is the cell of the original board.
;; In order to do so we firstly wrap the original with \# symbols at each edge and get the wrapped board 'be'.

;; Then we flatten each block and get the center at index 4 and neighbors at positions 1, 3, 5, 7 (range 1 8 2).

;; Then we update the center of each block if it has \M neighbor and is not \# and return updated board until it has \C cell
;; or cannot be updated anymore.

(fn [b] 
  (let [fe	#(concat [%2] % [%2])
        be0	(map #(fe % \#) b)
        be	(#(fe % (take (count (first %)) (repeat \#))) be0)
        fp	#(partition 3 1 %)
        bks	(map #(apply map list (map fp %)) (fp be))
        fsm	#(hash-map  :c (nth % 4)
                        :s (set (map (partial nth %) (range 1 8 2))))
        fmc	(fn [{c :c s :s}] (if (and (not= c \#) (get s \M)) \M c))
        nb	(map #(map (comp fmc fsm flatten) %) bks)]
      (if (= b nb) false 
          (if (every? #(not= \C %) (flatten nb)) true (recur nb)))))
