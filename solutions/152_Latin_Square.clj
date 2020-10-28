;; General approach. Represent each alignment as a nil containing rectangle.
;; Get all possible squares from each alignment without nils.
;; Then filter Latin squares. From the result sequence make the set of the Latin squares.
;; From this set with 'count' and 'frequencies' get the required map.
;; l -- maximum row len
;; w -- column len for the rectangles to be constructed
;; fr -- returns all possible nil-wrappings for the original row 'r'
;; fre -- compliments partially contructed alignments 'vs' with possible next row 
;;        variants 'rs'
;; als -- possible alignments
;; flat -- checks whether the current square is Latin
;; fsqs -- from a given alignment 'al' returns all contained latin squares.
;;         For each possible i, j upper-left corner we get all possible squares. To
;;         avoid timeout error we first get maximum possible square with the set
;;         upper-left corner and then successively remove last column and last row.
;;         During this operation we filter out nil containing squares.
;; last line -- get Latin squares from all alignments into single set, 
;;              then from that set with 'count' and 'frequencies' get the final result.

(fn [v]
  (let [l (apply max (map count v)) w (count v)
        fr (fn [r] (let [lr (- l (count r)) sn (repeat nil)]
                     (map #(concat (take % sn) r (take (- lr %) sn)) (range (inc lr)))))
        fre (fn [vs rs] (mapcat (fn [r] (map #(concat % [r]) vs)) rs))
        als (#(reduce fre (map list (first %)) (rest %)) (map fr v))
        flat #(apply = (map sort (concat % (apply map list %) [(set (first %))])))
        fsqs (fn [al] (mapcat #(filter flat %)
                        (for [i (butlast (range w)) j (butlast (range l))]
                          (take-while #(> (count %) 1) 
                            (drop-while #(some nil? (flatten %))
                              (iterate #(butlast (map butlast %)) 
                                ( #(take % (map (partial take %)             
                                             (map (partial drop j) (drop i al))))
                                  (min (- l j) (- w i)) )))))))]
    (frequencies (map count (reduce into #{} (map fsqs als))))))

